package com.sata.dfs.dfsmemo.zeroone_backpack_dfs_memo;

import java.util.Arrays;

/**
 * LC 416
 */
public class PartitionEqualSum {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if(sum % 2 == 1) return false;
        int n = nums.length;
        int[][] dp = new int[n][sum / 2 + 1];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(0, sum / 2, nums, 0, n, dp);
    }

    private boolean dfs(int cur, int sum, int[] nums, int index, int n, int[][] dp) {
        if(cur == sum) {
            return true;
        }
        if(index >= n || cur > sum) {
            return false;
        }
        if(dp[index][cur] != -1) return dp[index][cur] == 1;
        boolean tmp = false;
        for(int i = index; i < n; i++) {
            //0-1 背包问题，只能放一个进去，所以下一个index 是 i+1
            tmp = tmp || dfs(cur + nums[i], sum, nums, i + 1, n, dp);
        }
        dp[index][cur] = tmp? 1 : 0;
        return tmp;
    }
}
