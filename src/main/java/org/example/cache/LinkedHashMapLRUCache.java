package org.example.cache;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LinkedHashMapLRUCache<K, V> {
    private final int capacity;
    private final LinkedHashMap<K, V> map;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LinkedHashMapLRUCache(int capacity) {
        this.capacity = capacity;
        // accessOrder = true → maintains access order (LRU)
        this.map = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LinkedHashMapLRUCache.this.capacity;
            }
        };
    }

    // Read operation → multiple threads can read simultaneously
    public V get(K key) {
        lock.readLock().lock();
        try {
            return map.get(key);  // LinkedHashMap updates order automatically
        } finally {
            lock.readLock().unlock();
        }
    }

    // Write operation → only one thread can write at a time
    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            map.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        lock.readLock().lock();
        try {
            return map.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public String toString() {
        lock.readLock().lock();
        try {
            return map.toString();
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        LinkedHashMapLRUCache<Integer, String> cache = new LinkedHashMapLRUCache<>(3);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        System.out.println(cache); // {1=One, 2=Two, 3=Three}

        cache.get(1); // access 1 → becomes MRU
        System.out.println(cache);
        cache.put(4, "Four"); // evicts 2 (LRU)

        System.out.println(cache); // {3=Three, 1=One, 4=Four}
        System.out.println(cache);
    }
}

