package com.sata.dfs.pureBackTracing;

import com.sata.tree.TreeNode;

/**
 * LC  129 : 最简单的回溯法
 */
public class SumRootToLeafNumbers {

    private int res = 0;
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        dfs(root, 0);
        return res;
    }
    private void dfs(TreeNode root, int cur) {
        if(root.left == null && root.right == null) {
            res += cur * 10 + root.val;
            return;
        }
        if(root.left != null) dfs(root.left, cur * 10 + root.val);
        if(root.right != null) dfs(root.right, cur * 10 + root.val);
    }
}
