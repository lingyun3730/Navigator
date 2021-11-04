package com.sata.tree.pathsum;

import com.sata.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 113 经典回溯的题目，递归没有返回值
 */
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        if(root == null) return res;
        tmp.add(root.val);
        helper(root, res, targetSum - root.val, tmp);
        return res;
    }
    //全树扫描，不需要处理递归结果，所以不需要返回值
    private void helper(TreeNode root, List<List<Integer>> res, int targetSum, List<Integer> tmp) {
        if(root.left == null && root.right == null && targetSum == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        if(root.left == null && root.right == null) return;

        if(root.left != null) {
            tmp.add(root.left.val);
            helper(root.left, res, targetSum - root.left.val, tmp);
            tmp.remove(tmp.size() - 1);
        }
        if(root.right != null) {
            tmp.add(root.right.val);
            helper(root.right, res, targetSum - root.right.val, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
