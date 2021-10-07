package com.sata.dp.stock;

/**
 * LC 309
 */
public class StockV {
    public int maxProfit(int[] prices) {
        //买卖多次，但是有冷冻期，主要有四种状态：
        //0 表示买入或保持买入状态
        //1 表示今天卖出了股票
        //2 表示前两天已经卖出股票并且过了冷冻期
        //3 表示处于冷冻期
        //dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i], dp[i-1][3] - prices[i])
        //dp[i][1] = dp[i-1][0] + prices[i]
        //dp[i][2] = Math.max(dp[i-1][2], dp[i-1][3])
        //dp[i][3] = dp[i-1][1]
        int n = prices.length;
        int[][] dp = new int[n][4];
        dp[0][0] = -prices[0];
        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][2] - prices[i], dp[i-1][3] - prices[i]));
            dp[i][1] = dp[i-1][0] + prices[i];
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][3]);
            dp[i][3] = dp[i-1][1];
        }
        return Math.max(dp[n-1][1], Math.max(dp[n-1][2], dp[n-1][3]));
    }


}
