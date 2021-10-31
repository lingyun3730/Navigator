package com.sata.dfs.pureBackTracing.subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 491 子集问题
 */
public class IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        helper(res, tmp, nums, 0);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> tmp, int[] nums, int index) {
        if(tmp.size() > 1) { //子集问题，每个节点都会算路径
            res.add(new ArrayList<>(tmp));
        }
        if(index >= nums.length) return; //这个递归结束条件其实可不用写
        //在每一层开始之前，需要定义去重数组。
        int[] uset = new int[205];
        for(int i = index; i < nums.length; i++) {
            if((tmp.size() > 0 && tmp.get(tmp.size() - 1) > nums[i]) || uset[nums[i] + 100] == 1){
                continue;
            }
            uset[nums[i] + 100] = 1;
            tmp.add(nums[i]);
            helper(res, tmp, nums, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }
}
