package com.sata.string;

import java.util.Arrays;

/**
 * 205
 */
public class IsomorphicStrings {

    public static boolean isIsomorphic(String s, String t) {
        int[] smpt = new int[208]; //s到t的对应字符的映射
        int[] tmps = new int[208]; //t到s的对应字符的映射
        Arrays.fill(smpt, -1);
        Arrays.fill(tmps, -1);
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (smpt[s.charAt(i)] == -1) {
                smpt[s.charAt(i)] = t.charAt(i);
            }
            if (tmps[t.charAt(i)] == -1) {
                tmps[t.charAt(i)] = s.charAt(i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (smpt[s.charAt(i)] != t.charAt(i) || tmps[t.charAt(i)] != s.charAt(i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean res = isIsomorphic("egg", "add");
        System.out.println(res);
    }
}
