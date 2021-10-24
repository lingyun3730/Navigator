package com.sata.tree;

/**
 * LC 669
 */
public class TrimBST {
    /**
     * 迭代
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBSTI(TreeNode root, int low, int high) {
        //迭代法三步
        //第一步：将root移动到区间范围内
        //第二步：将左子树 < low的部分trim掉
        //第三步：将右子树 > high部分trim掉
        if(root == null) return root;
        while(root != null && (root.val < low || root.val > high)) {
            if(root.val < low) root = root.right;
            else if(root.val > high) root = root.left;
        }
        TreeNode cur = root;
        while(cur != null) {
            while(cur.left != null && cur.left.val < low) { //左孩子 < low
                cur.left = cur.left.right;
            }
            cur = cur.left;
        }
        cur = root;
        while(cur != null) {
            while(cur.right != null && cur.right.val > high) {
                cur.right = cur.right.left;
            }
            cur = cur.right;
        }
        return root;
    }

    /**
     * 递归法
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null) return null;
        //将root定到区间内
        if(root.val < low) return trimBST(root.right, low, high);
        else if(root.val > high) return trimBST(root.left, low, high);
        //左右子树分别递归
        root.left = trimBST (root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
