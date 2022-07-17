package com.sata.dp.coordinate;

/**
 * 坐标型动态规划，注意填充方式，这个题还是第一遍写对了的
 * https://www.lintcode.com/problem/875/?utm_source=sc-%20libao-hwx
 */
public class Pointer {
    /**
     * @param m: the 01 matrix
     * @return: the longest line of consecutive one in the matrix
     */
    public int longestLine(int[][] m) {
        // Write your code here
        if(m.length == 0) return 0;
        int r = m.length;
        int c = m[0].length;
        int[][][] dp = new int[r + 1][c + 1][4];
        int maxLen = 0;
        for(int i = 1; i <= r; i ++) {
            for(int j = 1; j <= c; j ++) {
                if(m[i - 1][j - 1] == 1) {
                    if(j - 1 >= 0) dp[i][j][0] = dp[i][j-1][0] + 1; //left
                    if(i - 1 >= 0) dp[i][j][1] = dp[i-1][j][1] + 1; //up
                    if(i - 1 >= 0 && j - 1 >= 0) dp[i][j][2] = dp[i-1][j-1][2] + 1;
                }
                maxLen = Math.max(maxLen, Math.max(dp[i][j][0], Math.max(dp[i][j][1], dp[i][j][2])));
            }
        }
        for(int i = 1; i <= r; i++) {
            for(int j = c; j >= 1; j--) {
                if(m[i - 1][j - 1] == 1) {
                    if(i - 1 >= 0 && j + 1 <= c) dp[i][j][3] = dp[i-1][j + 1][3] + 1;
                }
                maxLen = Math.max(maxLen, dp[i][j][3]);
            }
        }
        return maxLen;
    }
}
