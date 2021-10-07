package com.sata.dp.backpack.zeroone;

public class ZeroOneBackpack {

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        testWeightBagProblem(weight, value, bagSize);
        testWeightBagProblemII(weight, value, bagSize);
    }

    public static void testWeightBagProblem(int[] weight, int[] value, int bagSize){
        int wLen = weight.length, value0 = 0;
        //定义dp数组：dp[i][j]表示背包容量为j时，前i个物品能获得的最大价值
        int[][] dp = new int[wLen + 1][bagSize + 1];
        //初始化：背包容量为0时，能获得的价值都为0
        for (int i = 0; i <= wLen; i++){
            dp[i][0] = value0;
        }
        //遍历顺序：先遍历物品，再遍历背包容量
        for (int i = 1; i <= wLen; i++){
            for (int j = 1; j <= bagSize; j++){
                if (j < weight[i - 1]){
                    dp[i][j] = dp[i - 1][j];
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        //打印dp数组
        for (int i = 0; i <= wLen; i++){
            for (int j = 0; j <= bagSize; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    /**
     * 滚动数组解法
     */
    public static void testWeightBagProblemII(int[] weight, int[] value, int bagSize) {
        int[] dp = new int[bagSize + 1]; //dp[i]表示容量为i的背包，其最大可容纳的物品的价值。
        //dp[j] = max(dp[j], dp[j-weight[i]] + value[i]) when j >= weight[i]
        //j < weight[i], dp[j]保持不变，因为是从i-1层拷贝下来的dp[j]（相当于二维情况下的 dp[i-1][j]).
        //初始化就是当i=0时，当i=0时，无论背包容量如何，其价值都将是0。
        for(int i = 1; i <= weight.length; i++) {
            for(int j = bagSize; j >= weight[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j-weight[i-1]] + value[i-1]);
            }
        }
        //打印dp数组
        for (int j = 0; j <= bagSize; j++){
            System.out.print(dp[j] + " ");
        }
    }

}
