package com.sata.dp.stock;

/**
 * LC 714 买股票的最佳时机，带手续费了
 */
public class StockVI {
    //这个题用动态规划的话，对于手续费的处理比较简单，只要在状态转移方程上减掉fee即可
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] dp = new int[2]; // 0 represents hold, 1 represents sold
        dp[0] = -prices[0];
        dp[1] = 0;
        for(int i = 1; i <n;i++){
            dp[0] = Math.max(dp[0], dp[1] - prices[i]); //之前买入和今天买入取最大
            dp[1] = Math.max(dp[1], dp[0] + prices[i] - fee); //之前卖出和今天卖出取最大
        }
        return dp[1];
    }
    //但是用贪心来做就需要考虑fee的处理了。

}
