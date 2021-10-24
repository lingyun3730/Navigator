package com.sata.LinkedList;

public class CycleLinkedList {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        //快慢指针法
        ListNode fast = head;
        ListNode slow = head;
        boolean loop = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                //成环了
                loop = true;
                break;
            }
        }
        if (!loop) return null;
        else {
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
    }

}
