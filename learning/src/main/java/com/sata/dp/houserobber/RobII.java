package com.sata.dp.houserobber;

/**
 * LC 213
 */
public class RobII {
    public int rob(int[] nums) {
        //成环了
        int n = nums.length;
        if(n == 1) return nums[0];
        if(n == 2) return nums[0] > nums[1] ? nums[0] : nums[1];
        return Math.max(rob(nums, 0, n-2), rob(nums, 1, n-1)); //把环解开了
    }
    private int rob(int[] nums, int start, int end) {
        int[] dp = new int[end - start + 1];
        if(end == start) return nums[start];
        if(end == start + 1) return nums[start] > nums[end] ? nums[start] : nums[end];
        dp[0] = nums[start];
        dp[1] = nums[start] > nums[start + 1] ? nums[start] : nums[start + 1];
        for(int i = start + 2; i <= end; i++) {
            dp[i-start] = Math.max(dp[i-start-1], dp[i-start-2] + nums[i]);
        }
        return dp[end - start];
    }
}
