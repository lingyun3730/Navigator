package com.sata.LinkedList;

/**
 * LC 206， 四个指针转一圈，返回prev, cur带头向前进
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        ListNode temp = null;
        ListNode prev = null;
        while (cur != null) {
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }
}
