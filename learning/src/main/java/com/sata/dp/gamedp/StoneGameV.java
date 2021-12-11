package com.sata.dp.gamedp;

/**
 * LC 1563
 */
public class StoneGameV {

    public int stoneGameV(int[] s) {
        int n = s.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        int[] presum = new int[n + 1];
        for(int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] + s[i];
        }
        for(int i = n-1; i >=0; i--) { //i 从 n-1 -> 0
            for(int j = i + 1; j < n; j++) {
                for(int x = 1; i + x <= j; x ++) {
                    int left = presum[i + x] - presum[i];
                    int right = presum[j + 1] - presum[i + x];
                    if(left > right) {
                        dp[i][j] = Math.max(dp[i][j], right + dp[i+x][j]);
                    } else if (left < right){
                        dp[i][j] = Math.max(dp[i][j], left + dp[i][i + x - 1]);
                    } else {
                        dp[i][j] = Math.max(dp[i][j], Math.max(right + dp[i+x][j], left + dp[i][i + x - 1]));
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}
