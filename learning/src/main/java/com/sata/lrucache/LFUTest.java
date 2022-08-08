package com.sata.lrucache;

import java.util.HashMap;
import java.util.Map;

class LFUTest {

    class Node {
        Node next;
        Node pre;
        int key;
        int val;
        int freq;
        Node(int k, int v, int f) {
            key = k;
            val = v;
            freq = f;
        }
        Node() {
            this(-1, -1, 0);
        }
    }

    class DoublyLinkedList {
        Node head = new Node();
        Node tail = new Node();
        int size = 0;
        DoublyLinkedList () {
            head.next = tail;
            tail.pre = head;
        }
        void addFirst(Node node) {
            node.next = head.next;
            head.next = node;
            node.next.pre = node;
            node.pre = head;
            size ++;
        }
        void removeNode(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size --;
        }
    }

    private int cap;

    private Map<Integer, Node> mp; // <key, node>

    private Map<Integer, DoublyLinkedList> freMp; //<frequency, node list>

    private int minFreq;

    public LFUTest(int capacity) {
        minFreq = 0;
        freMp = new HashMap<>();
        cap = capacity;
        mp = new HashMap<>();
    }

    public int get(int key) {
        if(! mp.containsKey(key)) return -1;
        Node node = mp.get(key);
        DoublyLinkedList l = freMp.get(node.freq);
        l.removeNode(node);
        if(l.size == 0) {
            freMp.remove(node.freq);
            if(minFreq == node.freq) {
                minFreq  += 1;
            }
        }
        Node newNode = new Node(key, node.val, node.freq + 1);
        DoublyLinkedList nl = freMp.getOrDefault(node.freq + 1, new DoublyLinkedList());
        nl.addFirst(newNode);
        mp.put(key, newNode);
        freMp.put(newNode.freq, nl);
        return newNode.val;
    }

    public void put(int key, int value) {
        if(cap <= 0) return;
        if(! mp.containsKey(key)) {
            if(mp.size() >= cap) {
                DoublyLinkedList l = freMp.get(minFreq);
                Node toDelete = l.tail.pre;
                l.removeNode(toDelete);
                mp.remove(toDelete.key);
                if(l.size == 0) {
                    freMp.remove(minFreq);
                }
            }
            minFreq = 1;
            Node newNode = new Node(key, value, 1);
            DoublyLinkedList nl = freMp.getOrDefault(1, new DoublyLinkedList());
            nl.addFirst(newNode);
            mp.put(key, newNode);
            freMp.put(1, nl);
        } else {
            Node node = mp.get(key);
            DoublyLinkedList l = freMp.get(node.freq);
            l.removeNode(node);
            if(l.size == 0) {
                freMp.remove(node.freq);
                if(minFreq == node.freq) {
                    minFreq  += 1;
                }
            }
            Node newNode = new Node(key, value, node.freq + 1);
            DoublyLinkedList nl = freMp.getOrDefault(node.freq + 1, new DoublyLinkedList());
            nl.addFirst(newNode);
            mp.put(key, newNode);
            freMp.put(newNode.freq, nl);
        }
    }
    public static void main(String[] args) {
        LFUTest test = new LFUTest(0);
        test.put(0, 0);
        int v = test.get(0);
        System.out.println(v);
    }
}