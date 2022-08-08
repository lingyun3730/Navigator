package com.sata.dp.rollingArray;

/**
 * LC 221
 */
public class MaximalSquare {
    /**
     * Straight forward solution, two dimension dp array
     */
    public int maximalSquare(char[][] matrix) {
        //矩阵类型的题目还是考虑动态规划
        //dp[i][j]表示以（i-1, j-1) 为下角的最大正方形的边长
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0;
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                    if(maxLen < dp[i][j]) maxLen = dp[i][j];
                }
            }
        }
        return maxLen * maxLen;
    }

    /**
     * optimization : rolling array
     */
    public int maximalSquareI(char[][] matrix) {
        //矩阵类型的题目还是考虑动态规划
        //dp[j]表示以（., j-1) 为下角的最大正方形的边长
        int m = matrix.length;
        int n = matrix[0].length;
        int[]dp = new int[n + 1];
        int maxLen = 0;
        int pre = 0; // 表示原来数组中的dp[i-1][j-1]
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                int tmp = dp[j];   // 在第i行还没有更新dp[j]（dp[i][j]) 之前先把dp[j]（dp[i-1][j]保存起来,作为下一个更新dp[i][j + 1]时候的pre
                if(matrix[i-1][j-1] == '1') {
                    dp[j] = Math.min(pre, Math.min(dp[j-1], dp[j])) + 1;
                    if(maxLen < dp[j]) maxLen = dp[j];
                }else {
                    dp[j] = 0;
                }
                pre = tmp;
            }
        }
        return maxLen * maxLen;
    }
}
