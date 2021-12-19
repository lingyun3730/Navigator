package com.sata.dfs.dfsmemo.complete_backpack_dfs_memo;

import java.util.Arrays;

/**
 * LC 377
 */
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        //dfs+memo比dp更好理解
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return dfs(nums, memo, target, 0);
    }

    private int dfs(int[] nums, int[] memo, int target, int index) {
        if(index == target) {
            return 1;
        }
        if(target < index) return 0;
        if(memo[index] != -1) return memo[index];
        int res = 0;
        for(int num : nums) { //数字可以重复使用，遍历的顺序需要考虑的,所以每一层从头开始遍历，是排列问题
            res += dfs(nums, memo, target, index + num);
        }
        memo[index] = res;
        return res;
    }
}
