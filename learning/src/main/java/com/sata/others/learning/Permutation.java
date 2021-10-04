package com.sata.others.learning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        //origin state: not be visited
        boolean[] visited = new boolean[n];
        Arrays.fill(visited,false);
        helper(nums, res, 0, visited);
        return res;
    }
    private void helper(int[] nums, List<List<Integer>> res, int level, boolean[] visited) {
        List<Integer> tmp = new ArrayList<>();
        if(level == nums.length) {
            res.add(tmp);
            return;
        }
        //每一层的分支遍历
        for(int i = 0; i < nums.length; i++) {
            if(!visited[i]) {
                tmp.add(nums[i]);
                visited[i] = true;
                helper(nums, res, level+1, visited);
                tmp.remove(tmp.size() -1);
                visited[i] = false;
            }
        }
    }
    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        int[] l = {1,2,3};
        List<List<Integer>> res = permutation.permute(l);
        System.out.println(res.size());
    }
}