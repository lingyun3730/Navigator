package com.sata.dp.gamedp;

import java.util.Arrays;

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

    /**
     * DFS + memo
     * @param s
     * @return
     */
    public int stoneGameV_(int[] s) {
        //top - down solution
        //先计算presum
        int n = s.length;
        int[] presum = new int[n + 1];
        for(int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] + s[i];
        }
        int[][] memo = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(s, 0, n - 1, memo, presum);
    }

    private int dfs(int[] s, int start, int end, int[][] memo, int[] presum) {
        if(start > end) return 0;
        if(memo[start][end] != -1) return memo[start][end];
        int res = 0;
        for(int i = start; i <= end; i++) {
            int left = presum[i + 1] - presum[start]; //[start, i];
            int right = presum[end + 1] - presum[i + 1]; //[i + 1, end];
            if(left > right) {
                res = Math.max(res, dfs(s, i + 1, end, memo, presum) + right);
            } else if(left < right) {
                res = Math.max(res, dfs(s, start, i, memo, presum) + left);
            } else{
                res = Math.max(res, Math.max(dfs(s, start, i,memo, presum) + left, dfs(s, i + 1, end, memo, presum) + right));
            }
        }
        memo[start][end] = res;
        return res;
    }
}
