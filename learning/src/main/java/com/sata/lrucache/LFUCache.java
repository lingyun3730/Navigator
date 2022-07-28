package com.sata.lrucache;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    class Node {
        Node pre, next;
        int key, value;
        int freq;
        Node(){
            this(-1, -1, 0);
        }
        Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }
    class DoubleLinkedList {
        Node head, tail;
        int size;  // must have this field
        DoubleLinkedList () {
            this.head = new Node();
            this.tail = new Node();
            this.head.next = tail;
            this.tail.pre = head;
            size = 0;
        }
        void addFirst(Node node) {
            node.next = head.next;
            head.next.pre = node;
            node.pre = head;
            head.next = node;
            size ++;
        }
        void remove(Node node) {
            node.next.pre = node.pre;
            node.pre.next = node.next;
            size --;
        }
    }

    // double linked list  +  2 hashmap
    int minFreq;
    int cap;
    Map<Integer, Node> nodeMp;    // content <key, value> map
    Map<Integer, DoubleLinkedList> freqMp;   // frequency <frequency, double linked list> map

    /*
     * @param capacity: An integer
     */public LFUCache(int capacity) {
        // do intialization if necessary
        this.minFreq = 0;
        this.cap = capacity;
        nodeMp = new HashMap<>();
        freqMp = new HashMap<>();
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // write your code here
        if(! nodeMp.containsKey(key)) {
            if(nodeMp.size() >= this.cap) {
                //exceed the capacity, remove the least frequent node
                Node toDelete = freqMp.get(minFreq).tail.pre;
                freqMp.get(minFreq).remove(toDelete);
                nodeMp.remove(toDelete.key);
                if(freqMp.get(minFreq).size == 0) {
                    freqMp.remove(minFreq);
                }
            }
            minFreq = 1;
            Node node = new Node(key, value, 1);
            nodeMp.put(key, node);
            DoubleLinkedList list = freqMp.getOrDefault(1, new DoubleLinkedList());
            list.addFirst(node);
            freqMp.put(1, list);
        } else {
            //process the same as get method, can be regarded as a update operation.
            Node node = nodeMp.get(key);
            freqMp.get(node.freq).remove(node);
            //update minFreq
            if(freqMp.get(node.freq).size == 0) {
                freqMp.remove(node.freq);
                if(node.freq == minFreq)
                    minFreq += 1;
            }
            DoubleLinkedList newList = freqMp.getOrDefault(node.freq + 1, new DoubleLinkedList());
            Node newNode = new Node(key, value, node.freq + 1);
            newList.addFirst(newNode);
            freqMp.put(newNode.freq, newList);
            nodeMp.put(key, newNode);
        }
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
        Node node = nodeMp.get(key);
        if(node == null) return -1;
        freqMp.get(node.freq).remove(node);
        //update minFreq
        if(freqMp.get(node.freq).size == 0) {
            freqMp.remove(node.freq);
            if(node.freq == minFreq)
                minFreq += 1; // minFreq must be updated.
        }
        DoubleLinkedList newList = freqMp.getOrDefault(node.freq + 1, new DoubleLinkedList());
        Node newNode = new Node(key, node.value, node.freq + 1);
        newList.addFirst(newNode);
        freqMp.put(newNode.freq, newList);
        nodeMp.put(key, newNode);
        return newNode.value;
    }
}