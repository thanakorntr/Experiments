package LeetCode;

import java.util.*;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
 otherwise return -1.

 set(key, value) - Set or insert the value if the key is not already present.
 When the cache reached its capacity, it should invalidate the least recently used item
 before inserting a new item.
 *
 * Created by Thanakorn on 2/10/16.
 */
public class LRUCache {

    class LRUDoublyLinkedList {

        public LRUDoublyLinkedList prev;
        public LRUDoublyLinkedList next;
        public int key;
        public int value;

        public LRUDoublyLinkedList() {

        }

        public LRUDoublyLinkedList(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, LRUDoublyLinkedList> keyMap;
    private LRUDoublyLinkedList head;
    private LRUDoublyLinkedList tail;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new LRUDoublyLinkedList();
        tail = new LRUDoublyLinkedList();
        head.prev = null;
        head.next = tail;
        tail.prev = head;
        tail.next = null;
        keyMap = new HashMap<>();
    }

    public int get(int key) {
        if (!keyMap.containsKey(key)) {
            return -1;
        }
        LRUDoublyLinkedList node = keyMap.get(key);
        moveToFront(node);
        return node.value;
    }

    public void set(int key, int value) {
        if (keyMap.containsKey(key)) {
            LRUDoublyLinkedList node = keyMap.get(key);
            node.value = value;
            moveToFront(node);
        } else {
            if (keyMap.size() == capacity) {
                removeTail();
            }
            addToFront(new LRUDoublyLinkedList(key, value));
        }
    }

    private void moveToFront(LRUDoublyLinkedList lruNode) {
        removeNode(lruNode);
        addToFront(lruNode);
    }

    private void addToFront(LRUDoublyLinkedList lruNode) {
        head.next.prev = lruNode;
        lruNode.next = head.next;
        lruNode.prev = head;
        head.next = lruNode;
        keyMap.put(lruNode.key, lruNode);
    }

    private void removeTail() {
        removeNode(tail.prev);
    }

    private void removeNode(LRUDoublyLinkedList lruNode) {
        lruNode.prev.next = lruNode.next;
        lruNode.next.prev = lruNode.prev;
        keyMap.remove(lruNode.key);
    }
}
