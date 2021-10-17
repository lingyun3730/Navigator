package com.sata.MonotonousQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC 239 双端队列deque
 */
public class SlidingWindow {


    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>(); // store subscript
        int[] res = new int[n - k + 1];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            //sliding window: i-k+1 -> i
            while (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.pollFirst();
            }
            //保持单调队列
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            dq.addLast(i);
            if (i >= k - 1) {
                res[idx++] = nums[dq.peekFirst()];
            }
        }
        return res;
    }

}
