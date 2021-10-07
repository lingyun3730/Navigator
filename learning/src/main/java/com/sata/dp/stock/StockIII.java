package com.sata.dp.stock;

/**
 * LC 123: 最多两次买卖, 无非是状态多了几个而已
 */
public class StockIII {
    /**
     * 原始一维数组
     * @param prices
     * @return
     */
    public int maxProfitI(int[] prices) {
        //仍然可以用dp来做，只不过是状态多了几个而已。
        int n = prices.length;
        int[][] dp = new int[n][4]; // 0: first buy, 1: first sell, 2: second buy, 3: second sell
        dp[0][0] = -prices[0];
        dp[0][2] = -prices[0];
        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] - prices[i]);
            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] + prices[i]);
        }
        return dp[n-1][3];
    }
    /**
     * 常数空间复杂度, 滚动数组优化
     */
    public int maxProfitII(int[] prices) {
        int n = prices.length;
        int[] dp = new int[4]; // 0: first buy, 1: first sell, 2: second buy, 3: second sell
        dp[0] = -prices[0];
        dp[2] = -prices[0];
        for(int i = 1; i < n; i++) {
            dp[0] = Math.max(dp[0], -prices[i]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);
            dp[2] = Math.max(dp[2], dp[1] - prices[i]);
            dp[3] = Math.max(dp[3], dp[2] + prices[i]);
        }
        return dp[3];
    }
}
