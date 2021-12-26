package com.sata.dp.houserobber;

/**
 * LC 213
 */
public class RobII {
    public int rob(int[] nums) {
        //可以把环解开成[0, n-2], [1, n-1]
        int n = nums.length;
        if(n == 1) return nums[0];
        if(n == 2) return Math.max(nums[0], nums[1]);
        int res1 = helper(nums, 0, n-2);
        int res2 = helper(nums, 1, n-1);
        return res1 > res2 ? res1 : res2;
    }

    private int helper(int[] nums, int start, int end) {
        int[] dp = new int[end - start + 2];
        dp[1] = nums[start];
        for(int i = start + 2; i <= end + 1; i++) {
            dp[i - start] = Math.max(dp[i - start - 1], dp[i - start - 2] + nums[i - 1]);
        }
        return dp[end - start + 1];
    }
}
