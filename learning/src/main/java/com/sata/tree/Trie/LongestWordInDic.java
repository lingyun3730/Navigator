package com.sata.tree.Trie;

import java.util.*;

/**
 * LC 720
 */
public class LongestWordInDic {
    /**
     * 暴力解法
     * @param words
     * @return
     */
    public String longestWord_(String[] words) {
        //暴力解法
        Arrays.sort(words); //必须先对数组进行排序，因为需要返回字典序小的字符串
        Set<String> s = new HashSet<>();
        String res = "";
        for(String word : words) {
            if(word.length() == 1 || s.contains(word.substring(0, word.length() - 1))) {
                res = res.length() < word.length()? word : res;
                s.add(word);
            }
        }
        return res;
    }

    /**
     * Way 2: Trie + BFS
     */
    class Node {
        //定义字典树： 每个节点表示一个字符，该字符包含26个子节点，按照从左向右的顺序分别表示a-z。
        Node[] children = new Node[26];
        String word; //如果节点到了一个单词的结尾了，那么这个字段就会存储该单词，否则为null.
    }
    Node root = new Node(); //字典树的根节点
    public String longestWord(String[] words) {
        //字典树 + bfs
        root.word = "";
        //将数组中的word都插入到字典树中
        for(String word : words) {
            Node node = root;
            for(int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if(node.children[c] == null) {
                    node.children[c] = new Node();
                }
                node = node.children[c];
            }
            node.word = word; //单词的结尾节点需要把单词放进去
        }
        //开始 bfs
        String res = ""; //结果字符串
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();
            //按照单词长度分层进行遍历，每一层从左向右，保证字典顺序从大到小，每一层只需记录该层出现的单词的最左边的记录即可
            String smallest = "";
            //每一层的单词拥有相同的长度
            while(size > 0) {
                size --;
                Node cur = q.poll();
                if("".equals(smallest)) {
                    smallest = cur.word;
                }
                Node[] children = cur.children;
                for(int i = 0; i < 26; i++) {
                    if(children[i] == null || children[i].word == null) {
                        continue;
                    }
                    q.add(children[i]);
                }
            }
            res = smallest;
        }
        return res;
    }

    /**
     * Trie + DFS
     * @param words
     * @return
     */
    public String longestWordDFS(String[] words) {
        //字典树 + bfs
        root.word = "";
        //将数组中的word都插入到字典树中
        for(String word : words) {
            Node node = root;
            for(int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if(node.children[c] == null) {
                    node.children[c] = new Node();
                }
                node = node.children[c];
            }
            node.word = word; //单词的结尾节点需要把单词放进去
        }
        List<String> res = new ArrayList<>();
        dfs(res, root);
        if(res.size() == 0) return "";
        Collections.sort(res);
        return res.get(0);
    }
    private void dfs(List<String> res, Node root) {
        if(root == null) return;
        if(root.word != null) { //遇到一个单词，需要判断下它是不是一个需要添加到结果数组
            if(res.size() == 0) { //res为空，直接添加word
                res.add(root.word);
            }else {
                if(res.get(0).length() <= root.word.length()) {
                    if(res.get(0).length() < root.word.length()){ //长度更长，clear res
                        res.clear();
                    }
                    res.add(root.word);
                }
            }
        }
        for(int i = 0; i < 26; i++) {
            //按照题目要求，子节点必须有单词才能往下走递归
            if(root.children[i] != null && root.children[i].word != null) {
                dfs(res, root.children[i]); //深度优先遍历
            }
        }
    }
}
