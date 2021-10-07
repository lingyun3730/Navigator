package com.sata.dp;

/**
 * LC 343
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        //dp[i]表示i的结果。
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for(int i = 3; i <= n; i++) {
            for(int j = 1; j < i ; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i-j) , j * dp[i-j])); //分成两个去乘以及分成多个去乘的区别。
            }
        }
        return dp[n];
    }
}
