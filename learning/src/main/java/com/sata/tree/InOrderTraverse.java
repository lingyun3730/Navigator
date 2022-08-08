package com.sata.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InOrderTraverse {
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> s = new Stack<TreeNode> ();
        TreeNode cur = root;
        while(cur != null || !s.empty()) {
            while(cur != null){
                s.push(cur);
                cur = cur.left;
            }
            cur = s.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}
