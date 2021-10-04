package com.sata.others.learning;

import java.util.*;

public class VerticalSort {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Map<TreeNode, int[]> positions = new HashMap<>();
        int[] rootPos = {0,0};
        positions.put(root, rootPos);
        traversal(root, positions);
        List<Map.Entry<TreeNode, int[]>> list = new ArrayList<>(positions.entrySet());
        //然后通过比较器来实现排序
        //升序排序
        list.sort((o1, o2) -> {
            if (o1.getValue()[0] - o2.getValue()[0] != 0) {
                return o1.getValue()[0] - o2.getValue()[0];
            } else {
                if (o1.getValue()[1] - o2.getValue()[1] != 0) {
                    return o2.getValue()[1] - o1.getValue()[1];
                } else {
                    return o1.getKey().val - o2.getKey().val;
                }
            }
        });
        int verticalPo = Integer.MIN_VALUE;
        List<Integer> tmp = new ArrayList<>();
        for(Map.Entry<TreeNode, int[]> entry : list){
            if(entry.getValue()[0] != verticalPo){
                if(! tmp.isEmpty()) res.add(new ArrayList<>(tmp));
                tmp.clear();
                verticalPo = entry.getValue()[0];
            }
            tmp.add(entry.getKey().val);
        }
        res.add(new ArrayList<>(tmp));
        return res;
    }
    private void traversal(TreeNode root, Map<TreeNode, int[]> positions){
        if(root == null) return;
        if(root.left != null) {
            int [] pleft = new int[2];
            pleft[0] = positions.get(root)[0] - 1;
            pleft[1] = positions.get(root)[1] - 1;
            positions.put(root.left, pleft);
            traversal(root.left, positions);
        }
        if(root.right != null) {
            int [] pright = new int[2];
            pright[0] = positions.get(root)[0] + 1;
            pright[1] = positions.get(root)[1] - 1;
            positions.put(root.right, pright);
            traversal(root.right, positions);
        }
    }


    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;
        VerticalSort sort = new VerticalSort();
        List<List<Integer>> res = sort.verticalTraversal(treeNode1);
        System.out.println(res.size());
    }

    private static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }
}
