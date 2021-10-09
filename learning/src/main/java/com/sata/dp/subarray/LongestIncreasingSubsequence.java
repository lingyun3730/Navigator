package com.sata.dp.subarray;

import java.util.Arrays;

/**
 * LC 300
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        //dp[i]表示包含nums[i]的最长上升子序列
        //dp[i] = max(dp[j] + 1) (if nums[i] > nums[j], j < i)
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxVal = dp[0];
        for(int i = 1;  i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if(dp[i] > maxVal) maxVal = dp[i];
        }
        return maxVal;
    }
}
