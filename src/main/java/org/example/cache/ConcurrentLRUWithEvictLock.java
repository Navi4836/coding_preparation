package org.example.cache;


import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLRUWithEvictLock<K, V> {
    private final int capacity;
    private final ConcurrentHashMap<K, V> map;
    private final ConcurrentLinkedDeque<K> accessOrder;
    private final ReentrantLock evictionLock = new ReentrantLock();

    public ConcurrentLRUWithEvictLock(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity > 0 required");
        this.capacity = capacity;
        this.map = new ConcurrentHashMap<>(capacity);
        this.accessOrder = new ConcurrentLinkedDeque<>();
    }

    /**
     * Non-blocking read (no lock). We append an access record.
     */
    public V get(K key) {
        V value = map.get(key);
        if (value != null) {
            // Append an access record. We DON'T remove existing occurrences (cheap).
            accessOrder.addLast(key);
        }
        return value;
    }

    /**
     * Put is mostly non-blocking. After inserting, try to evict if needed.
     * Eviction is done under a lock to ensure capacity isn't overshot.
     */
    public void put(K key, V value) {
        map.put(key, value);
        accessOrder.addLast(key);

        // If size is over capacity, perform eviction under lock
        if (map.size() > capacity) {
            evictIfNeeded();
        }
    }

    /**
     * Atomically evict least-recently-used keys until size <= capacity.
     * Uses a small lock only for eviction work.
     */
    private void evictIfNeeded() {
        if (!evictionLock.tryLock()) {
            // Another thread is evicting; avoid blocking here — it will finish eviction.
            return;
        }
        try {
            while (map.size() > capacity) {
                K candidate = accessOrder.pollFirst(); // least-recently-used candidate
                if (candidate == null) {
                    // No more recorded accesses; break to avoid busy loop
                    break;
                }
                // Attempt to remove candidate from the map.
                // If candidate absent (null), skip; that handles duplicates or prior removals.
                map.remove(candidate);
                // loop continues until size <= capacity
            }
        } finally {
            evictionLock.unlock();
        }
    }

    public int size() {
        return map.size();
    }

    // Optional helper for tests / debugging — not strictly accurate under concurrency
    public String debugAccessOrderSnapshot() {
        return accessOrder.toString();
    }
}

