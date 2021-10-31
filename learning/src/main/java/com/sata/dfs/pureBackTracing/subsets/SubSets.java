package com.sata.dfs.pureBackTracing.subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * LC  78
 */
public class SubSets {
    //方法1：收集每个节点的答案，这是和组合问题不同的地方
    public List<List<Integer>> subsetsI(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        helperI(res, tmp, nums, 0);
        return res;
    }
    private void helperI(List<List<Integer>> res, List<Integer> tmp, int[] nums, int index) {
        res.add(new ArrayList<>(tmp));
        if(index >= nums.length) {
            return;
        }
        for(int i = index; i < nums.length; i++) {
            tmp.add(nums[i]);
            helperI(res, tmp, nums, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }


    //方法2：对于每个字符，有取它和不取它两种选择。
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        helper(res, tmp, nums, 0);
        return res;
    }
    //对于一个数字，有两种情况，一种是取它，一种是不取它
    private void helper(List<List<Integer>> res, List<Integer> tmp, int[] nums, int index) {
        if(index == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        //取它
        tmp.add(nums[index]);
        helper(res, tmp, nums, index + 1);
        tmp.remove(tmp.size() - 1);
        //不取它
        helper(res, tmp, nums, index + 1);
    }

}
