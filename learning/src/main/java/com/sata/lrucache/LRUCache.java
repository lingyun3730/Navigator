package com.sata.lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 146
 */
class LRUCache {
    //双向链表 + hashmap
    class Node {
        int key;
        int val;
        Node pre;
        Node next;
    }
    int cap;
    Node head;
    Node tail;
    Map<Integer, Node> mp;

    public LRUCache(int capacity) {
        this.cap = capacity;
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.pre = head;
        mp = new HashMap<>();
    }

    public int get(int key) {
        if(! mp.containsKey(key)) {
            return -1;
        }
        Node cur = mp.get(key);
        removeNode(cur);
        addNode(cur);
        return cur.val;
    }

    public void put(int key, int value) {
        if(mp.containsKey(key)) {
            //just update value
            Node node = mp.get(key);
            node.val = value;
            mp.put(key, node);
            removeNode(node);
            addNode(node);
        }else{
            if(mp.size() >= cap){
                mp.remove(tail.pre.key);
                removeNode(tail.pre);
            }
            Node newNode = new Node();
            newNode.key = key;
            newNode.val = value;
            addNode(newNode);
            mp.put(key, newNode);
        }
    }

    private void removeNode(Node cur) {
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;
    }

    private void addNode(Node cur) {
        cur.next = head.next;
        head.next.pre = cur;
        head.next = cur;
        cur.pre = head;
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
