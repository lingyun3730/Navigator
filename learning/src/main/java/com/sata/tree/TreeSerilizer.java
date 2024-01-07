package com.sata.tree;

import sun.swing.StringUIClientPropertyKey;

import java.util.ArrayList;
import java.util.List;

public class TreeSerilizer {
    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        serializeHelper(root, res);
        return res.toString();
    }

    private static void serializeHelper(TreeNode root, StringBuilder res) {
        if(root == null) {
            res.append("none,");
            return;
        }
        res.append(root.val).append(",");
        serializeHelper(root.left, res);
        serializeHelper(root.right, res);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        String[] dataArr = data.split(",");
        List<String> dataList = new ArrayList<>();
        for(String dataItem : dataArr) {
            dataList.add(dataItem);
        }
        return deserializeHelper(dataList);
    }

    private static TreeNode deserializeHelper(List<String> dataList) {
        if(dataList.isEmpty()) return null;
        if(dataList.get(0).equals("none")) {
            dataList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = deserializeHelper(dataList);
        root.right = deserializeHelper(dataList);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode (1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String res = serialize(root);
        TreeNode rt = deserialize(res);
        StringBuilder s1 = new StringBuilder("123");
        s1.reverse();
        s1.length();
        System.out.println(rt);
    }
}
