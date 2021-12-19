package com.sata;

import java.util.Arrays;
import java.util.List;

public class CodeTemplate {
    /**
     * Here list some code templates
     */

    //-------------------- 背包问题 --------------------
    List<Integer> weight = Arrays.asList(1,2,3);
    List<Integer> value = Arrays.asList(1,2,3);
    int bagWeight = 99;
    int n = weight.size();


    /**
     * 0-1 背包问题： n个物品，w大小的背包，每个物品只能用一次，求背包所能装下的最大价值
     */

    /**
     * 二维数组实现
     */
    public void zeroOneBackPackTwoDemission() {
        int[][] dp = new int[n][100];
        //dp[i][j]表示有物品编号0 -> i-1可选的时候，背包容量为j所能装下的物品的最大价值
        //只有一个物品0的时候，对大于该物品重量的背包容量进行初始化
        for(int i = weight.get(0); i <= bagWeight; i++) {
            dp[0][i] = value.get(0);
        }
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= bagWeight; j++) {
                if(j < weight.get(i)) dp[i][j] = dp[i-1][j];
                dp[i][j] = Math.max(dp[i - 1][j], dp[i-1][j - weight.get(i)] + value.get(i));
            }
        }
        System.out.println(dp[n-1][bagWeight]);
    }

    /**
     * 一维数组的实现，必须先遍历物品再遍历背包容量
     */
    public void zeroOneBackPackOneDemission() {
        int[] dp = new int[100]; //容量为i的背包最多能容纳的物品的价值
        //先遍历物品再遍历背包，因为反过来会使得每个背包只能放一个物品。
        for(int i = 0; i < weight.size(); i++) { // 遍历物品
            for(int j = bagWeight; j >= weight.get(i); j--) { // 遍历背包容量，从大到小遍历，因为每个物品只能放进去一次
                dp[j] = Math.max(dp[j], dp[j - weight.get(i)] + value.get(i));
            }
        }
    }

    /**
     * 完全背包问题： n个物品，w大小的背包，每个物品可以取多次，求背包所能装下的最大价值
     */

    /**
     * 二维数组实现
     */
    public void completeBackPackTwoDemission() {
        int[][] dp = new int[n][100];
        //只有一个物品0的时候，对大于该物品重量的背包容量进行初始化
        for(int i = 0; i <= bagWeight; i++) {
            dp[0][i] = value.get(0) * (i / weight.get(0));
        }
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= bagWeight; j++) {
                if(j < weight.get(i)) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight.get(i)] + value.get(i));
            }
        }
        System.out.println(dp[n-1][bagWeight]);
    }

    /**
     * 一维数组实现
     */
    public void completeBackPackOneDemission() {
        int[] dp = new int[100]; //容量为i的背包最多能容纳的物品的价值
        // 先遍历物品，再遍历背包，反过来其实也可以，但是和0-1背包问题统一起来
        for(int i = 0; i < weight.size(); i++) { // 遍历物品
            for(int j = weight.get(i); j <= bagWeight ; j++) { // 遍历背包容量， 从小到大遍历，因为每个物品可以放进去多次
                dp[j] = Math.max(dp[j], dp[j - weight.get(i)] + value.get(i));

            }
        }
    }

    /**
     * 以上是纯的0-1背包问题和完全背包问题的模板，但是处理实际问题的时候一定会有变形，题目的问法不同都会有不同的写法
     */

}
