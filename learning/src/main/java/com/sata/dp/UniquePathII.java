package com.sata.dp;

/**
 * LC 63 : 滚动数组需要理解一下。
 */
public class UniquePathII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //滚动数组做
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            dp[i] = 1;
            if(obstacleGrid[0][i] == 1) {
                dp[i] = 0;
                break;
            }
        }
        for(int i = 1;  i < m; i++){
            for(int j = 0; j < n; j++) {
                if(obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else{
                    //数组按列复用，因为填充顺序是从左向右从上到下的，因此填充之前dp[j]其实是上一行的结果，也就是未优化之前的
                    //dp[i-1][j].
                    if(j > 0) dp[j] = dp[j] + dp[j-1];
                }
            }
        }
        return dp[n-1];
    }
}
