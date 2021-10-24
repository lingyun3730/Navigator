package com.sata.tree;

import java.util.Stack;

public class BSTJudge {

    public static boolean isValidBST(TreeNode root) {
        //中序遍历迭代算法
        if(root == null) return false;
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur != null || ! s.isEmpty()) {
            if(cur != null) {
                s.push(cur);
                cur = cur.left;
            }else{
                cur = s.pop();
                System.out.println("pre :" + ((pre == null) ? null : pre.val));
                System.out.println("cur :" + cur.val);
                if(pre != null && cur.val <= pre.val) {
                    return false;
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return true;
    }

    /**
     * 这个写法比较熟悉
     * @param root
     * @return
     */
    public boolean isValidBSTII(TreeNode root) {
        //中序遍历迭代算法
        if(root == null) return false;
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur != null || ! s.isEmpty()) {
            while(cur != null) {
                s.push(cur);
                cur = cur.left;
            }
            cur = s.pop();
            if(pre != null && cur.val <= pre.val) {
                return false;
            }
            pre = cur;
            cur = cur.right;
        }
        return true;
    }

    //递归
    private TreeNode pre = null;
    public boolean isValidBSTIII(TreeNode root) {
        //中序遍历递归
        if(root == null) return true;
        boolean left = isValidBST(root.left);
        if(pre != null && pre.val >= root.val) return false;
        pre = root;
        boolean right = isValidBST(root.right);
        return left && right;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        boolean res = isValidBST(root);
        System.out.println(res);
    }
}
