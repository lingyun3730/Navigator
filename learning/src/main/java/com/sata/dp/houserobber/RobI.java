package com.sata.dp.houserobber;

/**
 * LC 198
 */
public class RobI {
    public int rob(int[] nums) {
        //dp[i]表示0-i之间进行抢劫最多能抢到多少钱。
        //dp[i] = max(dp[i-1], dp[i-2] + nums[i]); //要么偷当前位置的钱，要么不偷
        int n = nums.length;
        int[] dp = new int[n];
        if(n == 1) return nums[0];
        if(n == 2) return Math.max(nums[0], nums[1]);
        dp[0] = nums[0];
        dp[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        for(int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[n-1];
    }
}
