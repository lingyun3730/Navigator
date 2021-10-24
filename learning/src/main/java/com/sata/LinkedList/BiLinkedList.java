package com.sata.LinkedList;

/**
 * LC 707 定义双向链表
 */
public class BiLinkedList {
    //bi linked list 双向链表, 前后都用dummy node来confine
    class BiListNode {
        int val;
        BiListNode next;
        BiListNode pre;
        BiListNode(int val) {
            this.val = val;
            this.next = null;
            this.pre = null;
        }
    }

    private int size;
    private BiListNode dummy;
    private BiListNode tail;

    public BiLinkedList() {
        this.size = 0;
        this.dummy = new BiListNode(-1);
        this.tail = new BiListNode(-1);
        dummy.next = tail;
        tail.pre = dummy;
    }

    public int get(int index) {
        if(index < 0 || index >= size) {
            return -1;
        }
        BiListNode cur = dummy;
        for(int i = 0; i <= index; i ++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if(index < 0) {
            index = 0;
        }else if (index > size) {
            index = size;
        }
        BiListNode pre = dummy;
        for(int i = 0; i < index; i++) {
            pre = pre.next;
        }
        BiListNode node = new BiListNode(val);
        node.next = pre.next;
        pre.next.pre = node;
        node.pre = pre;
        pre.next = node;
        size ++;
    }

    public void deleteAtIndex(int index) {
        if(index >= size || index < 0){ return;}
        BiListNode pre = dummy;
        for(int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        pre.next.pre = pre;
        size--;
    }
}
