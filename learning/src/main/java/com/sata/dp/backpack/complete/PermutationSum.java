package com.sata.dp.backpack.complete;

import java.util.Arrays;

/**
 * LC 377.
 * 排列总和，是需要考虑结果集中元素的排列顺序，不同顺序的排列是不一样的
 * 先遍历背包再遍历物品，但是这种题目建议使用dfs+memo backtracking
 */
public class PermutationSum {

//    public static  int PermutationSumI(int[] nums, int target) {
//        int[][] dp = new int[target + 1][nums.length + 1];
//        for(int i = 0; i <= nums.length; i++) {
//            dp[0][i] = 1;
//        }
//        for (int i = 0; i <= target; i++) {
//            for (int j = 1; j <= nums.length; j++) {
//                if (i >= nums[j - 1]) {
//                    dp[i][j] = dp[i][j-1] + dp[i - nums[j-1]][j];
//                } else{
//                    dp[i][j] = dp[i][j-1];
//                }
//            }
//            System.out.println(String.format("for target %s.", i));
//            for(int k = 0; k <=target; k++) {
//                System.out.println(Arrays.toString(dp[k]));
//            }
//        }
//        return dp[target][nums.length];
//    }

    public int combinationSum4(int[] nums, int target) {
        //dfs+memo比dp更好理解
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return dfs(nums, memo, target);
    }

    private int dfs(int[] nums, int[] memo, int target) {
        if(0 == target) {
            return 1;
        }
        if(target < 0) return 0;
        if(memo[target] != -1) return memo[target];
        int res = 0;
        for(int num : nums) { //数字可以重复使用，遍历的顺序需要考虑的
            res += dfs(nums, memo, target - num);
        }
        memo[target] = res;
        return res;
    }


    //滚动数组
    public static int PermutationSumII(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
            System.out.println(String.format("for target %s.", i));
            for(int k = 0; k <=target; k++) {
                System.out.println(dp[k]);
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int res = PermutationSumII(nums, 4);
//        int res2 = PermutationSumI(nums, 4);
        //System.out.println(res);
        System.out.println(res);
    }
}
