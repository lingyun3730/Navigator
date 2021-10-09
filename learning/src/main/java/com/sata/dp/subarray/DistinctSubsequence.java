package com.sata.dp.subarray;

import java.util.Arrays;

/**
 * LC 115
 */
public class DistinctSubsequence {
    /**
     * DP solution bottom - up
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        //bottom - up
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1]; //不匹配 + 匹配
                }else{
                    dp[i][j] = dp[i-1][j]; //不匹配
                }
            }
        }
        return dp[n][m];
    }

    /**
     * DFS + memo top-down
     */
    public int numDistinctII(String s, String t) {
        //top-down + memo
        int[][] dp = new int[s.length() + 1][t.length() +1];
        for(int i = 0; i <= s.length(); i++){
            Arrays.fill(dp[i], -1);
        }
        return dfs(s, t, 0, 0, dp);
    }

    private int dfs(String s, String t, int sIndex, int tIndex, int[][] dp) {
        if(tIndex == t.length()) {
            return 1;
        }
        if(sIndex == s.length()) {
            return 0;
        }
        if(dp[sIndex][tIndex] != -1) return dp[sIndex][tIndex];
        int res;
        if(s.charAt(sIndex) == t.charAt(tIndex)) {
            res = dfs(s, t, sIndex+1, tIndex+1, dp) + dfs(s, t, sIndex + 1, tIndex, dp);
        } else{
            res = dfs(s, t, sIndex+1, tIndex, dp);
        }
        return dp[sIndex][tIndex] = res;
    }
}
