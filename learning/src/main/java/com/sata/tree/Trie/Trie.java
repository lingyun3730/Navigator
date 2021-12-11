package com.sata.tree.Trie;

/**
 * LC 208
 */
class Trie {
    class Node {
        Node[] children = new Node[26];
        String word;
    }
    Node root;
    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for(int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if(node.children[c] == null) {
                node.children[c] = new Node();
            }
            node = node.children[c];
        }
        node.word = word;
    }

    public boolean search(String word) {
        Node node = root;
        //按照单词的字符顺序一个一个地走。
        for(char c : word.toCharArray()) {
            if(node.children[c - 'a'] == null) return false;
            else node = node.children[c - 'a'];
        }
        return node.word != null;
    }

    public boolean startsWith(String prefix) {
        Node node = root;
        for(char c : prefix.toCharArray()) {
            if(node.children[c - 'a'] == null) return false;
            else node = node.children[c - 'a'];
        }
        return true;
    }
}
