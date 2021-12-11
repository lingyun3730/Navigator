package com.sata.lrucache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    //biLinkedList+ hashmap
    class BiLinkedNode { //bilinked list
        int key;
        int value;
        BiLinkedNode next;
        BiLinkedNode pre;
    }

    private int capacity;
    /**
     * we need two dummy nodes: head and tail to confine the data.
     */
    private BiLinkedNode head = new BiLinkedNode(); //dummy node
    private BiLinkedNode tail = new BiLinkedNode(); //dummy node
    private Map<Integer, BiLinkedNode> mp; //hash map

    LRUCache(int capacity) {
        this.capacity = capacity;
        this.mp = new HashMap<>();
        head.next = tail;
        tail.next = head;
    }

    int get(int key) {
        if (!mp.containsKey(key)) {
            return -1;
        } else {
            BiLinkedNode node = mp.get(key);
            removeNode(node); //remove from original position
            addNode(node); // add to front position
            return node.value;
        }
    }

    void put(int key, int value) {
        if (!mp.containsKey(key)) {
            if (mp.size() >= capacity) {
                mp.remove(tail.pre.key); // remove from map
                removeNode(tail.pre); // remove the last node from linked list
            }
            BiLinkedNode node = new BiLinkedNode();
            node.key = key;
            node.value = value;
            addNode(node);
            mp.put(key, node);
        } else {
            BiLinkedNode node = mp.get(key);
            removeNode(node);
            node.value = value;
            mp.put(key, node);
            addNode(node);
        }

    }

    /**
     * two operations: remove node and add node.
     * @param node
     */
    void removeNode(BiLinkedNode node) { //remove any node
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    void addNode(BiLinkedNode node) { // add to front
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
