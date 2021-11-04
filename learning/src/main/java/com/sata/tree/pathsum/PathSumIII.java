package com.sata.tree.pathsum;

import com.sata.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 437
 */
public class PathSumIII {
    int res = 0;
    public int pathSum(TreeNode root, int targetSum) {
        traversal(root, targetSum);
        return res;
    }
    //对每个节点，求出以它为起点的和是targetSum的路径条数。
    //从当前节点开始，向下全子树扫描，但是需要处理递归的结果，因此需要返回值，每个子树的结果都需要处理
    int helper(TreeNode root, int targetSum, Map<TreeNode, Integer> mp) {
        if(root == null) return 0; //递归出口
        int num = 0;
        if(mp.get(root) != null) return mp.get(root);
        if(targetSum == root.val) { //在每个节点处都有可能找到一个目标结果
            num++;
        }
        num += helper(root.left, targetSum - root.val, mp);
        num += helper(root.right, targetSum - root.val, mp);
        mp.put(root, num);
        return num;
    }
    //中序遍历
    void traversal(TreeNode root, int targetSum) {
        if(root == null) return;
        traversal(root.left, targetSum);
        Map<TreeNode, Integer> mp = new HashMap<>();
        int cur = helper(root, targetSum, mp);
        res += cur;
        traversal(root.right, targetSum);
    }
}
