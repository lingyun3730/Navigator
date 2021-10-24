package com.sata.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * LC 501 : 可以用中序遍历的递归和非递归方法做。
 */
public class FindModeInBST {

    public static int[] findMode(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null; //define pre
        int count = 0;
        int maxCount = 0;
        while(cur != null || ! s.isEmpty()) {
            while(cur!= null) {
                s.push(cur);
                cur = cur.left;
            }
            cur = s.pop();
            System.out.println(cur.val);
            if(pre == null || pre.val != cur.val) {
                count = 1;
            }else{
                count ++; //新的数字
            }
            if(count > maxCount) {
                maxCount = count;
                res.clear();
                res.add(cur.val);
            }else if(count == maxCount) {
                res.add(cur.val);
            }
            pre = cur;
            cur = cur.right;
        }
        int[] ret = new int[res.size()];
        for(int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }


    private int count = 0;
    private int maxCount = 0;
    TreeNode pre = null;
    List<Integer> res = new ArrayList<>();

    /**
     * Recursive method
     * @param root
     * @return
     */
    public int[] findModeII(TreeNode root) {
        inOrder(root);
        int[] ret = new int[res.size()];
        for(int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }

    private void inOrder(TreeNode root) {
        if(root == null) return;
        inOrder(root.left);
        if(pre == null || pre.val != root.val) {
            count = 1;
        }else{
            count ++;
        }
        if(count > maxCount){
            maxCount = count;
            res.clear();
            res.add(root.val);
        }else if(count == maxCount) {
            res.add(root.val);
        }
        pre = root; //pre = root
        inOrder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
        int[] res = findMode(root);
        System.out.println(res);
    }
}
