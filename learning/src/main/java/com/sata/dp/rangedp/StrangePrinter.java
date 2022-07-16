package com.sata.dp.rangedp;

/**
 * Range dp
 * https://www.lintcode.com/problem/1100/description?utm_source=sc%20-libao-hwx
 * lintcode 1100
 */
public class StrangePrinter {
    public int strangePrinter(String s) {
        // write your code here
        int len = s.length();
        //range dp
        int[][] dp = new int[len][len];
        for(int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        //倒三角填充方式
        for(int i = len - 1; i >= 0; i--) {
            for(int j = i + 1; j < len; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j-1]; //acba <=> acb
                } else{
                    int min = Integer.MAX_VALUE;
                    for(int k = i; k < j; k++) {
                        min = Math.min(min, dp[i][k] + dp[k + 1][j]);  // range dp
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][len - 1];
    }
}
