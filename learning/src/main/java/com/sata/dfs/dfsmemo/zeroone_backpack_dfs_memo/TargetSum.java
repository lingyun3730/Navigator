package com.sata.dfs.dfsmemo.zeroone_backpack_dfs_memo;

import java.util.Arrays;

/**
 * 494
 */
public class TargetSum {

    /**
     * 问题转化为和为一个正数的组合数
     * @param nums
     * @param target
     * @return
     */
    public static  int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if((sum + target) % 2 == 1) return 0;
        int t = (sum + target) / 2;
        if(t < 0) t = -t;
        int[][] dp = new int[nums.length][t + 1];
        for(int i = 0; i < nums.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(nums, 0, t, 0, dp);
    }
    private static int dfs(int[] nums, int cur, int target, int index, int[][] dp) {
        if(index >= nums.length) {
            if(cur == target) return 1;
            else return 0;
        }
        if(cur > target) return 0;
        if(dp[index][cur] >= 0) return dp[index][cur];
        int cnt1 = dfs(nums, cur, target, index + 1, dp);
        int cnt2 = dfs(nums, cur + nums[index], target, index + 1, dp);
        dp[index][cur] = cnt1 + cnt2;
        return dp[index][cur];
    }
//    public static int findTargetSumWays(int[] nums, int target) {
//        int sum = 0;
//        for(int num : nums) {
//            sum += num;
//        }
//        if((sum + target) % 2 == 1) return 0;
//        int t = (sum + target) / 2;
//        int[][] dp = new int[nums.length][t + 1];
//        for(int i = 0; i < nums.length; i++) {
//            Arrays.fill(dp[i], -1);
//        }
//        return dfs(nums, 0, t, 0, dp, 0);
//    }
//    private static int dfs(int[] nums, int cur, int target, int index, int[][] dp, int tmp) {
//        //问题转化为子集问题，求和为某个值的组合的个数
//        if(cur == target) {
//            tmp ++;
//            if(index >= nums.length) return tmp;
//        }
//        if(index >= nums.length || cur > target) return 0;
//        if(dp[index][cur] >= 0) return dp[index][cur];
//        int res = 0;
//        for(int i = index; i < nums.length; i++) {
//            res += dfs(nums, cur + nums[i], target, i + 1, dp, tmp);
//        }
//        dp[index][cur] = res;
//        return res;
//    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1};
        int res = findTargetSumWays(arr, 3);
        System.out.println(res);
    }
}
