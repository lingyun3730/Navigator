package com.sata.dfs.basicquestions;

import java.util.*;

/**
 * 母题4 : Permutation problem, 有重复， 要加剪枝, 时间复杂度是O(n!)
 *Given a string with duplicate characters, return a list with all permutations of the characters.
 * Examples
 * Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
 * Set = "", all permutations are [""]
 * Set = null, all permutations are []
 * Set = "abbc" all permutation are ["abbc", "abcb", "acbb", "babc", "bacb", "bbac", "bbca", "bcab", "bcba", "cabb", "cbab", "cbba"]
 */
public class Permutation {

    public static List<String> getAllPermutations(String s) {
        List<String> res = new ArrayList<>();
        boolean[] visited = new boolean[s.length()];
        String ss = String.copyValueOf(s.toCharArray());
        char[] sChar = ss.toCharArray();
        Arrays.sort(sChar);
        ss = new String(sChar);
        helper(ss, res, "", 0, visited);
        return res;
    }

    public static void helper(String s, List<String> res, String tmp, int level, boolean[] visited) {
        if(level == s.length()) {
            res.add(tmp);
            return;
        }
        for(int i = 0; i < s.length(); i++) {
            if(! visited[i]) {
                //the same char must appear in the same level because it does not appear in the same path.
                if(i > 0 && s.charAt(i) == s.charAt(i-1) && ! visited[i-1]) continue;
                visited[i] = true;
                tmp += s.charAt(i);
                helper(s, res, tmp, level + 1, visited);
                tmp = tmp.substring(0, tmp.length() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        List<String> res = getAllPermutations("abcb");
        res.forEach(System.out::println);
    }

}
