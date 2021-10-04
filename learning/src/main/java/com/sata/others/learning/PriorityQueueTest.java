package com.sata.others.learning;

import java.util.PriorityQueue;
import java.util.Queue;

class PriorityQueueHelper {
    public int findKthLargest(int[] nums, int k) {
        //use priority_queue
        int n = nums.length;
        Queue<Integer> q =  new PriorityQueue<>(((o1, o2) -> o2  - o1));
        for(int x : nums){
            q.offer(x);
        }
        for(int i=0; i<k-1; i++) {
            q.poll();
        }
        return q.peek();
    }
}