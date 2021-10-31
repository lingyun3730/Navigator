package com.sata.dp.LIS;

import java.util.Arrays;

/**
 * 673 : LIS number
 */
public class LISNumber {
    public int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length]; // <=i 的最长递增子序列的长度
        Arrays.fill(dp, 1);
        int[] count = new int[nums.length]; //以i结尾的最长递增子序列的个数
        Arrays.fill(count, 1);
        int maxLen = 0;
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    if(dp[j] + 1 > dp[i]) { //发现了一条更长的路径
                        dp[i] = dp[j] + 1; //更新LIS长度
                        count[i] = count[j]; //count相应地修改
                    }else if(dp[j] + 1 == dp[i]) { //一条新的和之前最长长度相等的路径
                        count[i] += count[j]; //加上路径条数
                    }
                }
            }
            if(maxLen < dp[i]) {
                maxLen = dp[i];
            }
        }
        int res = 0;
        for(int i = 0; i < nums.length; i++) {
            if(dp[i] == maxLen) {
                res += count[i];
            }
        }
        return res;
    }
}
