package com.sata.dfs.pureBackTracing.combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 77
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        helper(res, tmp, 1, n, k);
        return res;
    }

    private void helper(List<List<Integer>> res,
                        List<Integer> tmp,
                        int cur, int n,
                        int k) {
        if(tmp.size() == k) { //number 是当前层数
            res.add(new ArrayList<>(tmp));
            return;
        }
        for(int i = cur; i <= n - (k - tmp.size()) + 1; i++) { //这里做了剪枝， i至少从n - (k - tmp.size()) + 1开始，避免元素个数不够
            tmp.add(i);
            helper(res, tmp, i + 1, n, k);
            tmp.remove(tmp.size() - 1);
        }
    }
}
