package com.sata.LinkedList;

/**
 * LC 234
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        //不用数组，用链表反转做
        //快慢指针
        ListNode pre = head;
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        pre.next = null;
        //从slow开始，反转剩下的链表
        ListNode node2 = reverse(slow);
        //compare head和node2
        while(head != null){
            if(head.val != node2.val) return false;
            head = head.next;
            node2 = node2.next;
        }
        return true;
    }
    //链表反转
    private ListNode reverse(ListNode slow) {
        ListNode pre = null;
        ListNode cur = slow;
        ListNode temp = null;
        while(cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

}
