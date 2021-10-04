package com.sata.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreTraverse {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> s = new Stack<TreeNode> ();
        TreeNode cur = root;
        while(cur != null || !s.empty()) {
            while(cur != null){
                res.add(cur.val);
                s.push(cur);
                cur = cur.left;
            }
            cur = s.peek();
            s.pop();
            cur = cur.right;
        }
        return res;
    }
}
