package com.sata.LinkedList;

/**
 * LC 24
 */
public class SwapPairs {
    /**
     * 递归版本
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        //递归版本
        if(head == null || head.next == null) return head;
        ListNode next = head.next;
        ListNode newNode = swapPairs(next.next);
        next.next = head;
        head.next = newNode;
        return next;
    }

    /**
     * 非递归
     */
    public ListNode swapPairsII(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy.next;
        ListNode next = cur.next;
        ListNode pre = dummy;
        while(cur != null && next != null) {
            ListNode jump = next.next;
            pre.next = next;
            next.next = cur;
            cur.next = jump;
            pre = cur;
            cur = jump;
            if(jump != null) next = jump.next;
            else next = null;
        }
        return dummy.next;
    }

}
