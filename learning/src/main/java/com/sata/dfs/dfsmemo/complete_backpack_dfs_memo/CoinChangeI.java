package com.sata.dfs.dfsmemo.complete_backpack_dfs_memo;

import java.util.Arrays;

/**
 * LC 322
 * 这个题存在无解的情况需要单独处理一下, 无解
 */
public class CoinChangeI {
    int maxValue = -1;
    public int coinChange(int[] coins, int amount) {
        //dfs + memo
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        int res = dfs(coins, amount, 0, 0, dp);
        return res == maxValue ? -1 : res;
    }

    private int dfs(int[] coins, int amount, int index, int cur, int[][] dp) {
        if(cur == amount) {
            return 0;
        }

        //无解的情况，返回一个大整数
        if(index >= coins.length || cur > amount || cur < 0) { //有可能cur会超过整数限制，所以需要判断下 cur < 0
            return maxValue;
        }
        if(dp[index][cur] >= 0) return dp[index][cur];
        int min = Integer.MAX_VALUE;
        for(int i = index; i < coins.length; i++) {
            int res = dfs(coins, amount, i, cur + coins[i], dp);
            if(res != maxValue) { //保证有解的情况下再更新min。
                min = Math.min(res + 1, min);
            }
        }
        dp[index][cur] = min;
        return dp[index][cur];
    }
}
