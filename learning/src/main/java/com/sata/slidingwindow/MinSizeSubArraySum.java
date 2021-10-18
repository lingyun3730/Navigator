package com.sata.slidingwindow;

/**
 * LC 209
 */
public class MinSizeSubArraySum {
    public int minSubArrayLen(int target, int[] nums) {
        //sliding window
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        int i = 0; // start point of sliding window
        int sum = 0;
        for(int j = 0; j < n; j++) {
            sum += nums[j];
            while(sum >= target) {
                int windowSize = j - i + 1;
                res = res > windowSize ? windowSize : res;
                sum -= nums[i++]; //滑动窗口， 起始点向前移动， sum相应地减去nums[i]
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
