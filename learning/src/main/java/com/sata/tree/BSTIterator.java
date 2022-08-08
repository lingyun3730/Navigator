package com.sata.tree;

import java.util.Stack;

public class BSTIterator {
    private Stack<TreeNode> st = new Stack<TreeNode>();
    /**
     * @param root: The root of binary tree.
     */
    public BSTIterator(TreeNode root) {
        // do intialization if necessary
        TreeNode cur = root;
        while(cur != null) {
            st.push(cur);
            cur = cur.left;
        }
    }

    /**
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        // write your code here
        return ! st.isEmpty();
    }

    /**
     * @return: return next node
     */
    public TreeNode next() {
        // write your code here
        if(hasNext()) {
            TreeNode node = st.pop();
            if(node.right != null) {
                TreeNode temp = node.right;
                while(temp != null) {
                    st.push(temp);
                    temp = temp.left;
                }
            }
            return node;
        }
        return null;
    }
}
