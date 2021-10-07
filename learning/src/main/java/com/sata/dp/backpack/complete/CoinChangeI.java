package com.sata.dp.backpack.complete;

import java.util.Arrays;

/**
 * LC 322
 */
public class CoinChangeI {
    public int coinChange(int[] coins, int amount) {
        //硬币可以重复取->完全背包，求最小个数->遍历顺序无关
        //dp[j] 表示amount为j， 最少需要的coin个数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i =0; i < coins.length; i++) {
            for(int j = coins[i]; j <= amount; j++) {
                if(dp[j - coins[i]] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
            }
        }
        return dp[amount] == Integer.MAX_VALUE? -1 : dp[amount];
    }
}
