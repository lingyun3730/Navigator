package com.sata.contest;

import com.sata.tree.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * LC 2385
 */
public class InfectedBinaryTree {
    static class Node {
        int val;
        Node left;
        Node right;
        Node parent;
        Node() {}
        Node(int val) {
            this.val = val;
        }
        Node(int val, Node left, Node right, Node parent) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    public static  int amountOfTime(TreeNode root, int start) {
        Node r = helper(root);
        Node startNode = findStart(r, start);
        int res = 0;
        Queue<Node> q = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        q.add(startNode);
        while(! q.isEmpty()) {
            for(int i = q.size() - 1; i >= 0; i--) {
                Node node = q.poll();
                if(node.parent != null && ! visited.contains(node.parent)) {
                    q.add(node.parent);
                }
                if(node.right != null && ! visited.contains(node.right)) {
                    q.add(node.right);
                }
                if(node.left != null && ! visited.contains(node.left)) {
                    q.add(node.left);
                }
                visited.add(node);
            }
            res ++;
        }
        return res - 1;
    }

    private static Node helper(TreeNode node) {
        if(node == null) return null;
        Node n= new Node(node.val);
        Node left = helper(node.left);
        Node right = helper(node.right);
        n.left = left;
        n.right = right;
        if(n.left != null) n.left.parent = n;
        if(n.right != null) n.right.parent = n;
        return n;
    }

    private static Node findStart(Node node, int x) {
        if(node == null) return null;
        if(node.val == x) return node;
        Node left = findStart(node.left, x);
        Node right = findStart(node.right, x);
        if(left != null) return left;
        if(right != null) return right;
        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(10);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(9);
        TreeNode node7 = new TreeNode(2);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        int res = amountOfTime(root, 3);
        System.out.println(res);
    }
}
