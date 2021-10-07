package com.sata.dfs.basicquestions;

import java.util.ArrayList;
import java.util.List;

/**
 * 母题1：求一个字符串所有子串，单纯穷举。 O(2^n)
 * Given a set of characters represented by a String, return a list containing all subsets of the characters.
 * Assumptions
 * There are no duplicate characters in the original set.
 * Examples
 * Set = "abc", all the subsets are [“”, “a”, “ab”, “abc”, “ac”, “b”, “bc”, “c”]
 * Set = "", all the subsets are [""]
 * Set = null, all the subsets are []
 */
public class AllSubString {
    // find all sub strings of string s.
    public static List<String> allSubStrs(String s) {
        List<String> res = new ArrayList<>();
        String tmp = "";
        helper(s, 0, tmp, res);
        return res;
    }

    public static void helper(String s, int index, String tmp, List<String> res) {
        if(index == s.length()) {
            res.add(tmp);
            return;
        }
        //选择
        helper(s, index + 1, tmp + s.charAt(index), res);
        //不选择
        helper(s, index + 1, tmp, res);
    }

    public static void main(String[] args) {
        List<String> res = allSubStrs("abc");
        res.forEach(System.out::println);
    }
}
