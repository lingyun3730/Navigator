package com.sata.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC 513. Find Bottom Left Tree Value
 */
public class FindBottomLeftVal {

    public int findBottomLeftValueI(TreeNode root) {
        //用层序遍历很简单，找到最后一行的最左边一个就行了
        if(root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int res = root.val;
        while(! q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if(i == 0) res = cur.val;
                if(cur.left != null) q.add(cur.left);
                if(cur.right != null) q.add(cur.right);
            }
        }
        return res;
    }

    //递归
    private int bottomLeftVal;
    private int maxLen = Integer.MIN_VALUE;
    public int findBottomLeftValue(TreeNode root) {
        //使用preorder 来遍历二叉树， 因为是前序， 所以遍历到的每一层第一个的叶子结点一定是这一层最左边的叶子节点
        //用preorder来求树高，递归写法
        if(root == null) return 0;
        preTraversal(root, 0);
        return bottomLeftVal;
    }
    private void preTraversal(TreeNode root, int len) {
        if(root == null) return;
        if(root.left == null && root.right == null) { //遇到叶子结点
            if(len > maxLen) { //每次刚到了新的一层一定是这一层的最左边，这个时候需要更新下bottomLeftval, 不是新的一层不需要更新
                maxLen = len; //树高更新
                bottomLeftVal = root.val; //左叶子的数值更新
            }
            return;
        }
        if(root.left != null) {
            preTraversal(root.left, len + 1);
        }
        if(root.right != null) {
            preTraversal(root.right, len + 1);
        }
    }
}
