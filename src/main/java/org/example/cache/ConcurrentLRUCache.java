package org.example.cache;
import java.util.concurrent.*;

public class ConcurrentLRUCache<K, V> {
    private final int capacity;
    private final ConcurrentHashMap<K, V> map;
    private final ConcurrentLinkedDeque<K> accessOrder;

    public ConcurrentLRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new ConcurrentHashMap<>();
        this.accessOrder = new ConcurrentLinkedDeque<>();
    }

    public V get(K key) {
        V value = map.get(key);
        if (value != null) {
            // Move key to end (most recently used)
            accessOrder.remove(key);
            accessOrder.addLast(key);
        }
        return value;
    }

    public void put(K key, V value) {
        // If new key, add to queue
        if (!map.containsKey(key)) {
            // Evict if over capacity
            if (map.size() >= capacity) {
                evictLRU();
            }
        } else {
            // Existing key, remove from queue to re-insert at tail
            accessOrder.remove(key);
        }

        map.put(key, value);
        accessOrder.addLast(key);
    }

    private void evictLRU() {
        K lruKey = accessOrder.pollFirst(); // Least recently used
        if (lruKey != null) {
            map.remove(lruKey);
        }
    }

    public int size() {
        return map.size();
    }

    public void printCacheState() {
        System.out.println("Access Order: " + accessOrder);
        System.out.println("Map: " + map);
    }
}


/**
 *
 * | Aspect        | Implementation Detail                        |
 * | ------------- | -------------------------------------------- |
 * | Lookup        | `ConcurrentHashMap.get()` — O(1)             |
 * | Insert/Update | `ConcurrentHashMap.put() + queue ops` — O(1) |
 * | LRU Tracking  | `ConcurrentLinkedDeque`                      |
 * | Thread Safety | All components are non-blocking, concurrent  |
 * | Eviction      | Manual, when `size >= capacity`              |
 */

/**
 *
 * Let me walk you through the possible race conditions.
 *
 * 1️⃣ Race in Eviction (Over-capacity by 1+)
 *
 * Scenario:
 *
 * Cache has capacity = 3, and currently 3 entries.
 *
 * Thread A calls put(4, "D").
 *
 * Checks map.size() >= capacity → true.
 *
 * Calls evictLRU() → removes some key from queue and map.
 *
 * Inserts (4, "D").
 *
 * Thread B calls put(5, "E") at the same time.
 *
 * Before Thread A actually evicts, Thread B also checks map.size() >= capacity → still true, but hasn’t yet seen Thread A’s eviction.
 *
 * Both threads insert new entries before either finishes.
 *
 * Result: Cache might temporarily have capacity + 1 (or more) entries until later evictions happen.
 *
 * 2️⃣ Race in Access Order Update
 *
 * When we do:
 *
 * accessOrder.remove(key);
 * accessOrder.addLast(key);
 *
 *
 * This is two separate operations:
 *
 * Thread A removes a key
 *
 * Thread B tries to remove/add the same key
 *
 * The deque may momentarily have duplicate keys or be missing the key from the ordering perspective (though map still has correct value).
 *
 * Impact:
 *
 * Eviction order might not be perfectly LRU in a heavily concurrent scenario, because order updates are not atomic.
 *
 * 3️⃣ Race in get and Eviction
 *
 * If eviction happens while a thread is reading:
 *
 * Thread A calls get(1)
 *
 * Removes 1 from deque
 *
 * Adds it back to the end (most recently used)
 *
 * Thread B calls evictLRU() at the same time and removes 1 from the front
 *
 * Thread A finishes and adds 1 back to deque but not to map
 * → Now deque has a key that’s no longer in map.
 *
 * Impact:
 *
 * accessOrder and map can be slightly out of sync.
 *
 * Next eviction will clean it up.
 *
 * 📌 Why This Happens
 *
 * ConcurrentHashMap and ConcurrentLinkedDeque are individually thread-safe.
 *
 * But LRU ordering is a compound operation (remove, insert, check size, evict) → we never made it atomic.
 *
 * Without a lock or compare-and-set around the whole put+evict sequence, multiple threads can interleave their work.
 *
 * 🔧 How to Fix (If Strict LRU Is Required)
 *
 * Two main ways:
 *
 * Option A — Lock the Entire put() Sequence
 * public synchronized void put(K key, V value) {
 *     ...
 * }
 *
 *
 * Slower under heavy load.
 *
 * Guarantees perfect LRU and strict capacity.
 *
 * Option B — Use Caffeine
 *
 * Caffeine (com.github.benmanes.caffeine) is a high-performance caching library.
 *
 * It uses lock-striping + atomic order maintenance internally to avoid these race issues.
 *
 * Production-grade choice.
 */