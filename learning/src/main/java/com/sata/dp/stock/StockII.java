package com.sata.dp.stock;

/**
 * LC 122 : 买卖多次。
 */
public class StockII {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2]; // 0 represents hold, 1 represents sold
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for(int i = 1; i <n;i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] - prices[i]); //之前买入和今天买入取最大
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i]); //之前卖出和今天卖出取最大
        }
        return dp[n-1][1];
    }
}
