package com.sata.dfs.pureBackTracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 40
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
 * Each number in candidates may only be used once in the combination.
 * Note: The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * Example 2:
 * <p>
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * Constraints:
 * <p>
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 *
 *  1:      1             1         2          5          6             7           10
 *  2: 1 2 5 6 7 10   dedupe     5 6 7 10   6 7 10      7 10           10
 *
 */
public class CombinationSum {
    public static List<List<Integer>> getCombinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        helper(candidates, target, res, tmp, 0);
        return res;
    }

    public static void helper(int[] candidates, int target, List<List<Integer>> res, List<Integer> tmp, int level) {
        if (target == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        if(target < 0) return;
        for (int i = level; i < candidates.length; i++) {
            if(i > level && candidates[i-1] == candidates[i]) continue; //dedupe
            tmp.add(candidates[i]);
            helper(candidates, target - candidates[i], res, tmp, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {1,2,5,1,6,7,10};
        List<List<Integer>> res = getCombinationSum(candidates, 8);
        res.forEach(System.out::println);
    }
}
