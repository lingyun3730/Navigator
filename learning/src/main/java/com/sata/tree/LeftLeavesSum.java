package com.sata.tree;

import java.util.Stack;

/**
 * LC 404  https://leetcode.com/problems/sum-of-left-leaves/
 */
public class LeftLeavesSum {
    public int sumOfLeftLeavesI(TreeNode root) {
        //递归
        if(root == null) return 0;
        int left = sumOfLeftLeaves(root.left);
        int right = sumOfLeftLeaves(root.right);
        int res = 0;
        if(root.left != null && root.left.left == null && root.left.right == null) {
            res += root.left.val;
        }
        return res + left + right;
    }

    public int sumOfLeftLeaves(TreeNode root) {
        //迭代法，前序遍历
        if(root == null) return 0;
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode cur = root;
        int res = 0;
        while(cur != null || ! s.empty()) {
            while(cur!= null) {
                if(cur.left != null && cur.left.left == null && cur.left.right == null) {
                    res += cur.left.val;
                }
                s.push(cur);
                cur = cur.left;
            }
            cur = s.pop();
            cur = cur.right; //转向
        }
        return res;
    }
}
