package com.sata.array;

import java.util.PriorityQueue;
import java.util.Queue;

public class Fibonacci {
    public static int findKthLargest(int[] nums, int k) {
        Queue<Integer> q = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int num : nums) {
            q.add(num);
        }
        for(int i = 0; i < k - 1; i++) {
            q.poll();
        }
        return q.poll();
    }

    public static void main(String[] args) {
       int res = findKthLargest(new int[]{3,2,1,5,6,4}, 2);
       System.out.println(res);
    }
}
