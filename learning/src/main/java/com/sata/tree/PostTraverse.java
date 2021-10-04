package com.sata.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostTraverse {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        TreeNode pre = null;
        while (!s.empty()) {
            TreeNode cur = s.peek();
            /**
             * pre-condition: 左和右都被访问了，或者该节点是叶子节点，才会访问当前节点。
             */
            if ((cur.left == null && cur.right == null) || (pre != null && (cur.left == pre || cur.right == pre))) {
                res.add(cur.val);
                pre = cur;
                s.pop();
            } else {
                /**
                 * 入栈的顺序是先右子节点后左子节点，最后该节点本身。
                 */
                if (cur.right != null) s.push(cur.right);
                if (cur.left != null) s.push(cur.left);
            }
        }
        return res;
    }
}
