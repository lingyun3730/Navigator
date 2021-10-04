package com.sata.others.learning;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class KListsMerge {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        if(lists.length == 1)  return lists[0];

        ListNode res = mergeTwoLists(lists[0], lists[1]);
        for(int i = 2; i< lists.length; i++) {
            res = mergeTwoLists(res, lists[i]);
        }
        return res;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode tmp = null;
        if(l1.val < l2.val) {
            tmp = l1;
            l1 = l1.next;
        }else{
            tmp = l2;
            l2 = l2.next;
        }
        ListNode result = tmp;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                tmp.next = l1;
                l1 = l1.next;
            }else{
                tmp.next = l2;
                l2 = l2.next;
            }
            tmp = tmp.next;
        }
        if(l1 == null) tmp.next = l2;
        if(l2 == null) tmp.next = l1;
        return result;
    }

    static class ListNode{
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //[[1,4,5],[1,3,4],[2,6]]
    public static void main(String[] args) {
        KListsMerge KListsMerge = new KListsMerge();
        int[] ll1 = {2};
        int[] ll2 = {};
        int[] ll3 = {-1};
        ListNode l1 = createList(ll1);
        ListNode l2 = createList(ll2);
        ListNode l3 = createList(ll3);
        ListNode [] lists = {l1, l2, l3};
        ListNode res = KListsMerge.mergeKLists(lists);
        System.out.println(res.val);

    }

    private static ListNode createList(int[] l) {
        if(l.length == 0) return null;
        ListNode t = new ListNode(l[0]);
        ListNode tmp = t;
        for(int i = 1; i < l.length; i++){
            tmp.next = new ListNode(l[i]);
            tmp = tmp.next;
        }
        return t;
    }

}