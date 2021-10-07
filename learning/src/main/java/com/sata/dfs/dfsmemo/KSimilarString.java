package com.sata.dfs.dfsmemo;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 854
 */
public class KSimilarString {
    public static int kSimilarity(String s1, String s2) {
        Map<String, Integer> mp = new HashMap<>();
        int res = dfs(s1, s2, mp, 0);
        return res;
    }

    public static int dfs(String s1, String s2, Map<String, Integer> mp, int pos) {
        if(pos == s2.length()) { //交换结束, s1 == s2
            mp.put(s1, 0);
            return 0;
        }
        if(mp.containsKey(s1)) {
            return mp.get(s1);
        }
        if(s1.charAt(pos) == s2.charAt(pos)) { //对位相同，不需要交换
            return dfs(s1, s2, mp, pos + 1);
        }
        int count = 1000;
        for(int i = pos + 1; i < s1.length(); i++) {
            if(s2.charAt(pos) == s1.charAt(i)) { //可以将 s1的pos 和 i位置交换， 使得s1和s2在pos位置上相同。
                StringBuilder s1Builder = new StringBuilder(s1);
                swap(s1Builder, pos, i);
                count = Math.min(count, dfs(s1Builder.toString(), s2, mp, pos + 1) + 1); //交换了一次
            }
        }
        mp.put(s1, count);
        return count;
    }

    public static void swap(StringBuilder stringBuilder, int i, int j) {
        char ci = stringBuilder.charAt(i);
        char cj = stringBuilder.charAt(j);
        stringBuilder.deleteCharAt(i);
        stringBuilder.insert(i, cj);
        stringBuilder.deleteCharAt(j);
        stringBuilder.insert(j, ci);
    }

    public static void main(String[] args) {
        int res = kSimilarity("aabc", "abca");
        System.out.println(res);
    }
}
