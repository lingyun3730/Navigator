package com.sata.tree;

public class SubTreeOfAnotherTree {

    public boolean isSubtree(TreeNode root, TreeNode subTree) {
        if(root == null && subTree == null) return true;
        if(root == null || subTree == null) return false;
        if(isSame(root, subTree)) return true;
        return isSubtree(root.left, subTree) || isSubtree(root.right, subTree);
    }

    private boolean isSame(TreeNode left, TreeNode right) {
        if(left == null && right == null) return true;
        if(left == null || right == null || (left.val != right.val)) return false;
        return isSame(left.left, right.left) && isSame(left.right, right.right);
    }
}
