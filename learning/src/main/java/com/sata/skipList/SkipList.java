package com.sata.skipList;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * LC 1206
 */
class SkipList {
    class Node {
        int val;
        Node next;
        Node down;
        Node(int val, Node next, Node down) {
            this.val = val;
            this.next = next;
            this.down = down;
        }
    }

    Node head;
    public SkipList() {
        head = new Node(-1, null, null); //dummy node as head;
    }

    public boolean search(int target) {
        Node cur = head;
        while(cur != null) {
            while(cur.next != null && cur.next.val < target) cur = cur.next; //找到小于target的第一个节点
            if(cur.next != null && cur.next.val == target) return true; //找到该节点，直接返回
            cur = cur.down; //没找到继续下层找
        }
        return false;
    }

    public void add(int num) {
        Stack<Node> st = new Stack<>();
        Node cur = head;
        Node down = null;
        while(cur != null) {
            while(cur.next != null && cur.next.val < num) cur = cur.next;
            st.push(cur);
            cur = cur.down;
        }
        boolean insert = true; //the lowest layer have to be inserted.
        while(insert && ! st.isEmpty()) {
            cur = st.pop();
            cur.next = new Node(num, cur.next, down);
            down = cur.next; //down设置为被插入的node,等到了上一层之后，就是新一层被插入节点的down。
            insert = (((int) (Math.random() * 100) & 1) == 0); //下一层插入的时候，是随机插入的。
        }
        if(insert) head = new Node(-1, null, head); //如果insert还是true, 则在上层建一个空层,head指向这个空层。
    }

    public boolean erase(int num) {
        Node cur = head;
        boolean erase = false;
        while(cur != null) {
            while(cur.next != null && cur.next.val < num) cur = cur.next;
            if(cur.next != null && cur.next.val == num) {
                erase = true;  //节点存在，所以是能够删除的
                cur.next = cur.next.next; //删除节点
            }
            cur = cur.down; //要把下层的所有该节点全部删掉
        }
        return erase;
    }

    public void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.add(1);
        skipList.add(2);
        skipList.add(4);
        skipList.erase(3);
    }
}
