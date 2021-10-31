package com.sata.bfs;

import com.sata.string.StrStr;

import java.util.*;

/**
 * LC 127
 */
public class WordLadder {
    /**
     * 高效算法
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //无向图求最短路径，bfs最合适
        //技巧1 ： 用hashset 替代list
        HashSet<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        Map<String, Integer> mp = new HashMap<>(); //map记录到单词word的路径长度
        mp.put(beginWord, 1);
        while(!q.isEmpty()) {
            String word = q.poll();
            int path = mp.get(word); //取得到改单词的长度
            for(int i = 0; i < word.length(); i++) {
                char[] chs = word.toCharArray();
                for(char c = 'a'; c <= 'z'; c++) { //用26个字母分别替换
                    chs[i] = c;
                    String newWord = String.valueOf(chs);
                    if(newWord.equals(endWord)) return path + 1;
                    if(set.contains(newWord) && ! mp.containsKey(newWord)) { //newWord没有被访问过
                        mp.put(newWord, path + 1);
                        q.add(newWord); //没找到endWord，继续入队
                    }
                }
            }
        }
        return 0;
    }

    public static int ladderLengthII(String beginWord, String endWord, List<String> wordList) {
        //bfs
        Map<String, List<String>> next = new HashMap<>();
        List<String> visited = new ArrayList<>();
        wordList.add(beginWord);
        buildNextMap(next, wordList);
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        int count = 1;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                String word = q.poll();
                if(visited.contains(word)) continue;
                visited.add(word);
                if(word.equals(endWord)) return count; //找到了，返回
                List<String> adjnext = next.get(word);
                for(String link : adjnext) {
                    q.add(link);
                }
            }
            count ++;
        }
        return 0;
    }
    private static void buildNextMap(Map<String, List<String>> next, List<String> wordList) {
        for(String word : wordList) {
            next.putIfAbsent(word, new ArrayList<>());
            for(String other : wordList) {
                if(! other.equals(word) && oneLetterDiffer(word, other)) {
                    next.get(word).add(other);
                }
            }
        }
    }
    private static boolean oneLetterDiffer(String word, String other) {
        if(word.length() != other.length()) return false;
        int differ = 0;
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) != other.charAt(i)) {
                differ ++;
            }
            if(differ >= 2) return false;
        }
        return differ == 1;
    }

    public static void main(String[] args) {
        //"dot","dog","lot","log","cog"
        List<String> strs = new ArrayList<>();
        strs.add("hot");
        strs.add("dot");
        strs.add("dog");
        strs.add("lot");
        strs.add("log");
        strs.add("cog");
        int res = ladderLengthII("hit", "cog", strs);
        System.out.println(res);
    }
}
