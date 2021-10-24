package com.sata.tree;

/**
 * LC 236
 */
public class LCABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //想那个回溯的图 https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0236.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88.md
        if(root == null) return null;
        if(root == p || root == q) return root; //找到一个节点就是递归出口
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //整个树进行递归处理逻辑
        if(left != null && right != null) return root;
        else if(right == null) return left;
        else if(left == null) return right;
        else return null;
    }
}
