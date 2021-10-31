package com.sata.dfs.pureBackTracing.PermutationsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 47  https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0047.%E5%85%A8%E6%8E%92%E5%88%97II.md
 */
public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        helper(res, tmp, nums, 0, visited);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> tmp, int[] nums, int index, boolean[] visited) {
        if(index == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            //visite[i-1]==false说明这个节点在同一层被访问过了，visited[i-1] == true说明这个节点在同一个树枝被访问过了。
            if(i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue; //使用这个去重条件的前提是，数组进行了排序，
            if(visited[i]) continue;
            visited[i] = true;
            tmp.add(nums[i]);
            helper(res, tmp, nums, index + 1, visited);
            tmp.remove(tmp.size() - 1);
            visited[i] = false;
        }
    }
}
