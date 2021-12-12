package com.sata.dfs.dfsmemo;

/**
 * LC 329
 */
public class LongestIncreasingPath {
    int[][] dirs = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    public int longestIncreasingPath(int[][] matrix) {
        //dfs + memo
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m]; //dp[i][j]表示从i，j出发能到达的最远的上升距离
        int len = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                len = Math.max(len, helper(matrix, i, j, n, m, dp));
            }
        }
        return len;
    }
    private int helper(int[][] matrix, int i, int j, int n, int m, int[][] dp) {
        if(dp[i][j] > 0) return dp[i][j];
        int len = 1;
        for(int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if(ni >= n || ni < 0 || nj >= m || nj < 0 || matrix[ni][nj] <= matrix[i][j]) continue;
            len = Math.max(len, helper(matrix, ni, nj, n, m, dp) + 1);
        }
        dp[i][j] = len;
        return dp[i][j];
    }
}
