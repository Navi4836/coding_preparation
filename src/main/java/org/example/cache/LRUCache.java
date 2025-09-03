package org.example.cache;

import java.util.*;

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> map;
    private final DoublyLinkedList<K, V> dll;

    // For concurrency
    private final Object lock = new Object();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.dll = new DoublyLinkedList<>();
    }

    public V get(K key) {
        synchronized (lock) {
            if (!map.containsKey(key)) return null;
            Node<K, V> node = map.get(key);
            dll.moveToFront(node);
            return node.value;
        }
    }

    public void put(K key, V value) {
        synchronized (lock) {
            if (map.containsKey(key)) {
                Node<K, V> node = map.get(key);
                node.value = value;
                dll.moveToFront(node);
            } else {
                if (map.size() == capacity) {
                    Node<K, V> lru = dll.removeLast();
                    map.remove(lru.key);
                }
                Node<K, V> newNode = new Node<>(key, value);
                dll.addFront(newNode);
                map.put(key, newNode);
            }
        }
    }

    // Doubly Linked List
    private static class DoublyLinkedList<K, V> {
        private final Node<K, V> head, tail;

        DoublyLinkedList() {
            head = new Node<>(null, null);
            tail = new Node<>(null, null);
            head.next = tail;
            tail.prev = head;
        }

        void addFront(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        void moveToFront(Node<K, V> node) {
            remove(node);
            addFront(node);
        }

        Node<K, V> removeLast() {
            if (tail.prev == head) return null;
            Node<K, V> node = tail.prev;
            remove(node);
            return node;
        }

        void remove(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev, next;

        Node(K k, V v) { key = k; value = v; }
    }
}
