package com.sata.dp.backpack.complete;

/**
 * LC 518 : 完全背包问题的变形，问有多少种组合方式，滚动数组的递推表达式是：dp[j] += dp[j-nums[i]], j 必须顺序填充
 * 二维数组的递推表达式是：
 * dp[i][j] = dp[i-1][j] when j < nums[i]
 * dp[i][j] = dp[i-1][j] + dp[i][j-nums[i]] when j > nums[i]
 * 本题是求组合数，也就是结果集中每个结果内部的coin的顺序无关，因此先遍历物品，再遍历背包大小，
 * 如果是求排列数，结果集中每个结果的顺序是需要考虑的，则先遍历背包大小，再遍历物品。
 */
public class CoinsChange2 {

    public int changeI(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        //初始化dp[i][0] = 1
        for(int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }
        for(int i = 1; i <= coins.length; i++) {
            for(int j = 1; j <= amount; j++) {
                if(j < coins[i-1]) {
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                }
            }
        }
        return dp[coins.length][amount];
    }

    public int changeII(int amount, int[] coins) {
        //完全背包问题。
        //dp[j]表示从coins里找到数字的和为j的组合方式
        //dp[j] += dp[j - coins[i]]

        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int i = 0; i < coins.length; i++) {
            for(int j = coins[i]; j <= amount; j++) { //顺序填充
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }


}
