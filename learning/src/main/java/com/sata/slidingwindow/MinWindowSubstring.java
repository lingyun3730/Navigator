package com.sata.slidingwindow;

/**
 * LC 76
 */
public class MinWindowSubstring {
    public static String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        if(n < m) return "";
        int[] mps = new int[128]; //appearance number of chars in s sliding window.
        int[] mpt = new int[128];
        for(int k = 0; k < m; k++) {
            mpt[t.charAt(k)] ++;
        }
        int i = 0;  //start index of sliding window
        int j = 0;  //end index of sliding window
        int finalI = 0;
        int finalJ = 0;
        int res = Integer.MAX_VALUE;
        for(;j < n; j++) {
            mps[s.charAt(j)] ++;
            while(match(mps, mpt)) { //sliding window 的精髓之处，while循环
                if(res > j-i) {
                    res = j-i;
                    finalI = i;
                    finalJ = j;
                }
                mps[s.charAt(i++)]--;
            }
        }
        return res == Integer.MAX_VALUE? "" : s.substring(finalI, finalJ + 1);
    }
    private static boolean match(int[] mps, int[] mpt) {
        for(int i = 0; i < mpt.length; i++) {
            if(mpt[i] != 0 && mps[i] < mpt[i])  {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String res = minWindow("ADOBECODEBANC", "ABC");
        System.out.println(res);
    }
}
