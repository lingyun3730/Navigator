package com.sata.contest;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LC 2386
 */
public class FindTheKSumOfArray {

    public static long kSum(int[] nums, int k) {
        Queue<long[]> q = new PriorityQueue<>((o1, o2) -> (int) (o2[0] - o1[0]));
        int n = nums.length;
        long sum = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] > 0) sum += nums[i];
            nums[i] = Math.abs(nums[i]);
        }
        Arrays.sort(nums);
        q.add(new long[] {sum - nums[0], 0});
        long res = sum; // first maximum is sum.
        while(--k > 0) {
            long[] cur = q.poll();
            if(cur[1] + 1 < n) {
                q.add(new long[] {cur[0] + nums[(int) cur[1]] - nums[(int) cur[1] + 1], cur[1] + 1});
                q.add(new long[] {cur[0] - nums[(int) cur[1] + 1], cur[1] + 1});
            }
            res = cur[0];
        }
        return res;
    }

    public static void main(String[] args) {
        long res = kSum(new int[] {2, 4, -2}, 5);
        System.out.println(res);
    }
}
