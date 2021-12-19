package com.sata.dfs.dfsmemo.complete_backpack_dfs_memo;

import java.util.Arrays;

/**
 * LC 518 dfs + memo solution
 */
public class CoinsChangeII {
    public int change(int amount, int[] coins) {
        //dfs + memo， top-down solution
        //如果实在想找到dp[i][j]的定义，那么它表示，i -> n-1的coins能组合成amount - j的组合数
        int[][] dp = new int[coins.length][amount + 1];  //两个维度，一个是coins，一个是amount.
        for(int i = 0; i < coins.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        int res = dfs(amount, coins, 0, 0, dp);
        return res;
    }

    private int dfs(int amount, int[] coins, int index, int cur, int[][] dp) {
        if(cur == amount) return 1;
        if(index >= coins.length || cur > amount) {
            return 0;
        }
        if(dp[index][cur] != -1) return dp[index][cur];
        int count = 0;
        for(int i = index; i < coins.length; i++){ //每个硬币能取多次，所以下一个循环还是i，不是i + 1
            count += dfs(amount, coins, i, cur + coins[i], dp);
        }
        dp[index][cur] = count;
        return dp[index][cur];
    }
}
