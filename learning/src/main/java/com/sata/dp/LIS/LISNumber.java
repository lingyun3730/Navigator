package com.sata.dp.LIS;

import java.util.Arrays;

/**
 * 673 : LIS number
 */
public class LISNumber {
    public int findNumberOfLIS(int[] nums) {
        //这个题的难点在于需要使用两个数组
        //dp[i]表示以i位置结尾的最长上升子序列的长度
        //count[i]表示以i结尾的最长上升子序列的路径个数
        //dp[i] = max (dp[j] + 1) if nums[i] > nums[j] and j < i
        //count[i] = count[j] if(dp[i] < dp[j] + 1)
        //count[i] += count[j] if(dp[i] == dp[j] + 1)
        int number = 0;
        int[] dp = new int[nums.length];
        int[] count = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        int maxLen = 0;
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    if(dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }else if(dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
            if(maxLen < dp[i]) {
                maxLen = dp[i];
            }
        }
        for(int i = 0; i < nums.length; i++) {
            if(maxLen == dp[i]) {
                number += count[i];
            }
        }
        return number;
    }
}
