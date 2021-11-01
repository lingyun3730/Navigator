package com.sata.dp.treeDp;


import com.sata.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 337
 */
public class TreeRobIII {

    /**
     * dfs solution
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]); //two status : robbed / notRobbed
    }

    private int[] helper(TreeNode root) {
        int[] res = new int[2];
        if(root == null) {
            return res;
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        int robbed = root.val + left[1] +right[1];
        int notRobbed = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[0] = robbed;
        res[1] = notRobbed;
        return res;
    }

    /**
     * dfs + memo 1
     * @param root
     * @return
     */
    public int robII(TreeNode root) {
        //dfs + memo
        Map<TreeNode, Integer> mp = new HashMap<>();
        return robHelper(root, mp);
    }
    private int robHelper(TreeNode root, Map<TreeNode, Integer> mp) {
        if(root == null) {
            return 0;
        }
        if(mp.containsKey(root)) return mp.get(root);
        int res = root.val;
        if(root.left != null) {
            res += robHelper(root.left.left, mp) + robHelper(root.left.right, mp);
        }
        if(root.right != null) {
            res += robHelper(root.right.left, mp) + robHelper(root.right.right, mp);
        }
        res = Math.max(res, robHelper(root.left, mp) + robHelper(root.right, mp));
        mp.put(root, res);
        return res;
    }

    /**
     * dfs + memo 2
     * @param root
     * @return
     */
    public int robIII(TreeNode root) {
        Map<TreeNode, int[]> mp = new HashMap<>();
        int[] res = helperII(root, mp);
        return Math.max(res[0], res[1]);
    }
    private int[] helperII(TreeNode root, Map<TreeNode, int[]> mp) {
        int[] res = new int[2];
        if(root == null) return res;
        if(mp.containsKey(root)) {
            return mp.get(root);
        }
        res[1] += root.val;
        int[] left = helperII(root.left, mp);
        int[] right = helperII(root.right, mp);
        //取不取子节点都行
        res[0] += Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //不能取子节点
        res[1] += left[0] + right[0];
        mp.put(root, res);
        return res;
    }
}
