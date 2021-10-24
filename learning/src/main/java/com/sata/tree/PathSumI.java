package com.sata.tree;

/**
 * LC 112 https://leetcode.com/problems/path-sum/， 递归有返回值
 */
public class PathSumI {

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        return helper(root, sum - root.val);
    }
    //求某一条路径是否存在，不需要遍历全部的路径，找到就返回了。
    private boolean helper(TreeNode root, int count) {
        if(root.left == null && root.right == null && count == 0) {
            return true;
        }
        if(root.left == null && root.right == null) {
            return false;
        }
        if(root.left != null) {
            if(helper(root.left, count-root.left.val)) return true;
            // count -= root.left.val;
            // if(helper(root.left, count)) return true;
            // count += root.left.val;
        }
        if(root.right != null) {
            if(helper(root.right, count-root.right.val)) return true;
            // count -= root.right.val;
            // if(helper(root.right, count)) return true;
            // count += root.right.val;
        }
        return false;
    }
}
