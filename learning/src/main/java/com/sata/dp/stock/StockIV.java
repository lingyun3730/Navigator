package com.sata.dp.stock;

/**
 * LC 188 : 最多买卖k次，那么一共有 2*k 个状态需要遍历。偶数0，2，4，..表示买入， 1，3，5,..表示卖出
 */
public class StockIV {

    /**
     * 二维数组
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(n == 0 || k == 0) return 0; //特殊数值单独处理
        //0 表示啥也不干，奇数表示买入和保持买入，偶数表示卖出或者保持卖出
        int[][] dp = new int[n][2 * k + 1];
        //对第0天的k次买入进行初始化
        for(int i = 1; i <= k; i++) {
            dp[0][2 * i - 1] = -prices[0];
        }
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < 2 * k; j += 2) {
                //第k次买入和卖出
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1] - prices[i]);
                dp[i][j + 1] = Math.max(dp[i-1][j + 1], dp[i-1][j] + prices[i]);
            }
        }
        return dp[n-1][2 * k];
    }

    /**
     * 滚动数组优化
     */

    public int maxProfitII(int k, int[] prices) {
        int n = prices.length;
        if(n == 0 || k == 0) return 0; //特殊数值单独处理
        int[] dp = new int[2 * k + 1]; //0 表示啥也不干，奇数表示买入和保持买入，偶数表示卖出或者保持卖出
        //对第0天的k次买入进行初始化
        for(int i = 1; i <= k; i++) {
            dp[2 * i - 1] = -prices[0];
        }
        for(int i = 1; i < n; i++) { //1 -> n
            for(int j = 1; j < 2 * k; j += 2) {
                //第k次买入和卖出
                dp[j] = Math.max(dp[j], dp[j-1] - prices[i]);
                dp[j + 1] = Math.max(dp[j + 1], dp[j] + prices[i]);
            }
        }
        return dp[2 * k];
    }
}
