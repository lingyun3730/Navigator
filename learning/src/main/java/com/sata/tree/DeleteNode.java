package com.sata.tree;

public class DeleteNode {

    public TreeNode deleteNode(TreeNode root, int key) {
        /**
         * BST 二叉查找树
         */
        if(root == null) return root;
        if(root.val < key) {
            root.right = deleteNode(root.right, key);
        }else if(root.val > key){
            root.left = deleteNode(root.left, key);
        }else{
            if(root.left == null && root.right == null) {
                return null;
            }else if(root.left == null || root.right == null) {
                return root.left == null? root.right : root.left;
            } else {
                TreeNode p = root.right;
                while(p.left != null) {
                    p = p.left;
                }
                root.val = p.val;
                root.right = deleteNode(root.right, p.val);
            }
        }
        return root;
    }
}
