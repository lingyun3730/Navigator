package com.sata.LinkedList;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LC 143
 */
public class ReorderList {
    //三种方法
    //方法一： 将链表平分，后一半反转，然后两个数组进行合并
    //方法二： 用数组进行模拟
    //方法三： 用双向队列进行模拟。
    //现在用方法1和3

    //方法一：
    public void reorderListI(ListNode head) {
        if(head == null || head.next == null) return;
        ListNode pre = head;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        slow = reverse(slow);
        mergeList(head, slow);
    }
    private ListNode reverse(ListNode slow) {
        ListNode pre = null;
        ListNode temp = null;
        ListNode cur = slow;
        while(cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
    private void mergeList(ListNode head, ListNode slow) {
        ListNode l1 = head;
        ListNode l2 = slow;
        ListNode temp1 = null;
        ListNode temp2 = null;
        while(l1 != null && l2 != null) {
            temp1 = l1.next;
            temp2 = l2.next;
            l1.next = l2;
            if(temp1 != null) l2.next = temp1;
            l1 = temp1;
            l2 = temp2;
        }
    }

    //方法3：双向队列模拟

    public void reorderList(ListNode head) {
        Deque<ListNode> dq = new LinkedList<>(); //deque的初始化要记住
        ListNode cur = head;
        while(cur != null) {
            dq.addLast(cur);
            cur = cur.next;
        }
        cur = dq.pollFirst();
        int count = 1;
        while(!dq.isEmpty()) {
            if(count % 2 == 1) {
                cur.next = dq.pollLast();
            }else{
                cur.next = dq.pollFirst();
            }
            cur = cur.next;
            count ++;
        }
        cur.next = null;
    }

}
