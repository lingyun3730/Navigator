package com.sata.dp.backpack.zeroone;

/**
 * LC 494 ---可以转化为LC 39求组合求和，也可以转化为01背包问题
 */
public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        /**
         * 将数组分成两部分，left, right.
         * left + right = sum of nums.
         * left - right = target.
         * left = (sum + target) / 2;
         * so，(sum + target)%2 could not be 1.
         * target <= sum
         * we can transform the question to find all the possibilities of number sum to be left. --backtracking LC 39
         * 也可以将题目转化为01背包问题，找出和为left的所有可能的填充方法。
         * dp[i] represents all he possibilities of number composition to sum up to i.
         * 递推公式和原始的01背包问题略有不同。
         * dp[i] = dp[i] + dp[i - nums[j]]  滚动数组
         * dp[i][j] = dp[i-1][j] + dp[i][j-nums[i]]. --> 0-i-1区间内组合成容量为j的组合方式 + 加上nums[i]之后增量的可能性dp[i][j-nums[i]]
         */
        //dp[i] += dp[i - nums[j]]
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if(target > sum) return 0;
        if((target + sum) % 2 == 1) return 0;
        int left = (target + sum) / 2;
        int[] dp = new int[left + 1];
        dp[0] = 1;
        for(int i = 0; i < nums.length; i++) {
            for(int j = left; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[left];
    }
}
