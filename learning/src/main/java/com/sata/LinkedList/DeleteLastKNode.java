package com.sata.LinkedList;

/**
 * LC 19 为了删除head节点方便一点，添加一个dummy 节点，双指针
 */
public class DeleteLastKNode {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //需要添加一个dummy 节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while(n-- > 0) {
            fast = fast.next;
        }
        ListNode prev = slow;
        while(fast != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = slow.next;
        return dummy.next; //一定是删除dummy.next，而不是head, 因为head本身也会被删除
    }
}
