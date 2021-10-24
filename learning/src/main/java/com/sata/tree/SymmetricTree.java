package com.sata.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC 110： 比较两个子树的递归过程或者迭代过程
 */
public class SymmetricTree {

    public boolean isSymmetricI(TreeNode root) {
        //用两种方式来判断一个二叉树是不是镜像对称的。
        //递归
        if(root == null) return true;
        return isSymmetric(root.left, root.right);
    }
    private boolean isSymmetric(TreeNode left, TreeNode right) {
        //递归出口两个
        if(left == null && right == null) return true;
        if(left == null || right == null || left.val != right.val) return false;
        //单层的递归逻辑， left.val == right.val, 没有触发递归出口的逻辑，继续递归
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
    //层序遍历
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        while(! q.isEmpty()) {
            TreeNode left = q.poll();
            TreeNode right = q.poll();
            if(left == null && right == null) {
                continue;
            }else if(left == null || right == null || left.val != right.val){
                return false;
            }
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);
        }
        return true;
    }

}
