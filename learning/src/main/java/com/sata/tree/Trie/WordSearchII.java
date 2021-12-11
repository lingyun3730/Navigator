package com.sata.tree.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 212
 */
public class WordSearchII {
    class Node {
        Node[] children = new Node[26];
        String str;
    }
    Node root = new Node();
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        //字典树 + dfs
        Node node = root;
        for(String word : words) {
            node = root;
            for(char c : word.toCharArray()) {
                if(node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new Node();
                }
                node = node.children[c - 'a'];
            }
            node.str = word;
        }
        int n = board.length;
        int m = board[0].length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                dfs(i, j, n, m, board, root, res, "");
            }
        }
        return res;
    }

    private void dfs(int i, int j, int n, int m, char[][] board, Node root, List<String> res, String temp) {
        char c = board[i][j];
        if(root.children[c - 'a'] == null) return;
        temp = temp + c;
        root = root.children[c - 'a'];
        if(root.str != null) {
            res.add(temp);
            root.str = null; //避免重复计算
        }
        board[i][j] = '#';
        if(i + 1 < n && board[i+1][j] != '#') dfs(i + 1, j, n, m, board, root, res, temp);
        if(i - 1 >= 0 && board[i-1][j] != '#') dfs(i - 1, j, n, m, board, root, res, temp);
        if(j - 1 >= 0 && board[i][j - 1] != '#') dfs(i, j - 1, n, m, board, root, res, temp);
        if(j + 1 < m && board[i][j + 1] != '#') dfs(i, j + 1, n, m, board, root, res, temp);
        board[i][j] = c;
    }
}
