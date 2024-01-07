package com.sata.bfs;

import java.util.*;

/**
 * LC 126
 */
public class wordLadderII {

    private static int len = Integer.MAX_VALUE;

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (beginWord.length() != endWord.length()) return res;
        List<String> tmp = new ArrayList<>();
        tmp.add(beginWord);
        Set<String> visited = new HashSet<>();
        helper(beginWord, endWord, res, tmp, wordList, visited);
        return res;
    }

    private static void helper(String beginWord, String endWord, List<List<String>> res, List<String> tmp, List<String> wordList, Set<String> visited) {
        if (beginWord.equals(endWord)) { // find one path;
            if (tmp.size() < len) {
                res.clear();
                len = tmp.size();
            }
            if (tmp.size() == len) res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < beginWord.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (beginWord.charAt(i) != (char) ('a' + j)) {
                    String tempStr = beginWord.substring(0, i) + (char) ('a' + j) +
                            beginWord.substring(i + 1);
                    if (!wordList.contains(tempStr) || visited.contains(tempStr)) continue;
                    tmp.add(tempStr);
                    visited.add(tempStr);
                    helper(tempStr, endWord, res, tmp, wordList, visited);
                    visited.remove(tempStr);
                    tmp.remove(tmp.size() - 1);
                }
            }
        }
    }

    /**
     * Methed 2
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */

    public static List<List<String>> findLaddersII(String beginWord, String endWord, List<String> wordList) {
        // BFS
        List<List<String>> res = new ArrayList<>();
        Map<String, Set<String>> mp = new HashMap<>(); // <node, parent nodes> map
        List<String> tmp = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        boolean hasPath = false;
        while (!q.isEmpty()) {
            Queue<String> qTemp = new LinkedList<>(q);
            for(int i = q.size(); i > 0; i--) {
                String x = q.poll();
                if(x.equals(endWord)) {
                    hasPath = true;
                    break;
                }
                String temp = x;
                for(int j = 0; j < beginWord.length(); j++) {
                    for(char c = 'a'; c <= 'z'; c++) {
                        if (temp.charAt(j) != c){
                            temp = temp.substring(0, j) + c + temp.substring(j + 1);
                        }
                        if(wordList.contains(temp)) {
                            String finalTemp = temp;
                            q.add(temp);
                            wordList.removeIf(o -> o.equals(finalTemp));
                            mp.putIfAbsent(temp, new HashSet<>());
                            mp.get(temp).add(x);
                            Queue<String> p = new LinkedList<>(qTemp); // copy last layer, because last layer should be used more than once.
                            while (!p.isEmpty()) { // traverse the last layer
                                String tp = p.poll();
                                if (differOne(tp, temp)) { // find parents
                                    mp.get(temp).add(tp);
                                }
                            }
                        }
                        temp = x;
                    }
                }
            }
            if(hasPath) break;
        }
        if (hasPath) {
            tmp.add(endWord);
            helper(tmp, res, mp, beginWord, endWord);
        }
        return res;
    }

    private static boolean differOne(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        if (m != n) return false;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                cnt++;
                if (cnt > 1) return false;
            }
        }
        return cnt == 1;
    }

    private static void helper(List<String> tmp, List<List<String>> res, Map<String, Set<String>> mp, String startWord, String endWord) {
        if(endWord.equals(startWord)) {
            List<String> reverse = new ArrayList<>(tmp);
            Collections.reverse(reverse);
            res.add(reverse);
            return;
        }
        for(String m : mp.get(endWord)) {
            tmp.add(m);
            helper(tmp, res, mp, startWord, m);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        //"dot","dog","lot","log","cog"
        List<String> strs = new ArrayList<>();
//        strs.add("hot");
//        strs.add("dot");
//        strs.add("dog");
//        strs.add("lot");
//        strs.add("log");
//        strs.add("cog");
//        List<List<String>> res = findLaddersII("hit", "cog", strs);
//        strs.add("a");
//        strs.add("b");
//        strs.add("c");
//        List<List<String>> res = findLaddersII("a", "c", strs);
//        ["ted","tex","red","tax","tad","den","rex","pee"]
        strs.add("ted");
        strs.add("tex");
        strs.add("red");
        strs.add("tax");
        strs.add("tad");
        strs.add("den");
        strs.add("rex");
        strs.add("pee");
        List<List<String>> res = findLaddersII("red", "tax", strs);
        System.out.println(res);
    }

}
