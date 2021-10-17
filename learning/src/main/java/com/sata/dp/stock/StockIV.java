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
        int[][] dp = new int[n][2*k];
        for(int i = 0; i < k; i++) {
            dp[0][i * 2] = -prices[0];
        }
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < k; j++) {
                //买入处理，持有的最大钱
                if(j == 0) {
                    dp[i][j * 2] = Math.max(dp[i-1][j * 2],  - prices[i]); //第1次单独处理
                } else {
                    dp[i][j * 2] = Math.max(dp[i-1][j * 2], dp[i-1][j * 2 - 1] - prices[i]);
                }
                //卖出处理，卖出的最大钱
                dp[i][j * 2 + 1] = Math.max(dp[i-1][j * 2 + 1], dp[i-1][j * 2] + prices[i]);
            }
        }
        return dp[n-1][2*k-1];
    }

    /**
     * 滚动数组优化
     */

    public int maxProfitII(int k, int[] prices) {
        int n = prices.length;
        if(n == 0 || k == 0) return 0; //特殊数值单独处理
        int[] dp = new int[2*k];
        for(int i = 0; i < k; i++) {
            dp[i * 2] = -prices[0];
        }
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < k; j++) {
                if(j == 0) {
                    dp[j * 2] = Math.max(dp[j * 2],  - prices[i]);
                } else {
                    dp[j * 2] = Math.max(dp[j * 2], dp[j * 2 - 1] - prices[i]);
                }

                dp[j * 2 + 1] = Math.max(dp[j * 2 + 1], dp[j * 2] + prices[i]);
            }
        }
        return dp[2*k-1];
    }
}
