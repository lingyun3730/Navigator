package com.sata.dp.backpack.complete;

public class CompleteBackpack {
    /**
     * 完全背包问题: 物品数量无限
     * dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i - 1]] + value[i - 1])，
     * 这里是dp[i][j - weight[i - 1]]而不是dp[i-1][j - weight[i - 1]]，是因为可以放很多个相同的物品进去，物品可以重复，
     * 所以相同行的前者影响后面的决策
     */

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        testCompleteBagProblem(weight, value, bagSize);
        testCompleteBagProblemII(weight, value, bagSize);
    }

    /**
     * 完全背包的二维数组解法
     * @param weight
     * @param value
     * @param bagSize
     */
    public static void testCompleteBagProblem(int[] weight, int[] value, int bagSize){
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
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i - 1]] + value[i - 1]);
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
     * 完全背包的滚动数组解法，单纯的完全背包，求的是最大价值，因此与先遍历背包还是先遍历物品无关
     */
    public static void testCompleteBagProblemII(int[] weight, int[] value, int bagSize) {
        int[] dp = new int[bagSize + 1]; //dp[i]表示容量为i的背包，其最大可容纳的物品的价值。
        //dp[j] = max(dp[j], dp[j-weight[i]] + value[i]) when j >= weight[i]
        //j < weight[i], dp[j]保持不变，因为是从i-1层拷贝下来的dp[j]（相当于二维情况下的 dp[i-1][j]).
        //初始化就是当i=0时，当i=0时，无论背包容量如何，其价值都将是0。
        for(int i = 1; i <= weight.length; i++) {
            for(int j = weight[i - 1]; j <= bagSize; j++) { //和01背包问题不同的地方在于填充顺序是从小到大
                dp[j] = Math.max(dp[j], dp[j-weight[i-1]] + value[i-1]);
            }
        }
        //打印dp数组
        for (int j = 0; j <= bagSize; j++){
            System.out.print(dp[j] + " ");
        }
    }
}
