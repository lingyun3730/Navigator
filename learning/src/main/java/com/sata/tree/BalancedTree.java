package com.sata.tree;

/**
 * LC 110 子高差不超过1
 */
public class BalancedTree {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int getDepth(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
    }
}
