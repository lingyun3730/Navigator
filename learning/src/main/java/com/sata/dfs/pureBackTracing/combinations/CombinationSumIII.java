package com.sata.dfs.pureBackTracing.combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 216  https://leetcode.com/problems/combination-sum-iii/
 */
public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        helper(res, tmp, 1, 9, k, n);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> tmp,
                        int cur, int up,
                        int k, int leftSum) {
        if(tmp.size() == k) {
            if(leftSum == 0) {
                res.add(new ArrayList<>(tmp));
            }
            return;
        }
        if(leftSum < 0) return; //重要的剪枝
        for(int i = cur; i <= up - (k - tmp.size()) + 1; i++) {
            tmp.add(i);
            helper(res, tmp, i + 1, up, k, leftSum - i);
            tmp.remove(tmp.size() - 1);
        }
    }
}
