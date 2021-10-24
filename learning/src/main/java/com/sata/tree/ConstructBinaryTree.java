package com.sata.tree;

/**
 * LC 105， LC 106
 */
public class ConstructBinaryTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return helper(preorder, inorder, 0, n-1, 0, n-1);

    }
    private TreeNode helper(int[] preorder, int[] inorder,
                            int preStart, int preEnd,
                            int inStart, int inEnd) {
        if(preStart > preEnd || inStart > inEnd) return null;
        int val = preorder[preStart];
        int index = -1;
        for(int i = inStart; i <= inEnd; i++) {
            if(val == inorder[i]) {
                index = i;
            }
        }
        TreeNode root = new TreeNode(val);
        root.left = helper(preorder, inorder,
                preStart + 1, preStart + index - inStart,
                inStart, index - 1);
        root.right = helper(preorder, inorder,
                preStart + index - inStart + 1, preEnd,
                index + 1, inEnd);
        return root;
    }
}
