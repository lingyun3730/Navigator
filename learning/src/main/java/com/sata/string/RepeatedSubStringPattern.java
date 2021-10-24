package com.sata.string;

public class RepeatedSubStringPattern {
    private void getNext(int[] next, String s) {
        next[0] = 0;
        int j = 0;
        for(int i = 1; i < s.length(); i++) {
            while(j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j-1];
            }
            if(s.charAt(i) == s.charAt(j)) {
                j ++;
            }
            next[i] = j;
        }
    }
    public boolean repeatedSubstringPattern(String s) {
        //求next数组
        /**
         * next
         * a b c a b c a b c
         * 0 0 0 1 2 3 4 5 6
         *
         * next[9-1] > 0 && 9 % (9 - next[9-1]) == 0
         **/
        int n = s.length();
        int[] next = new int[n];
        getNext(next, s);
        return next[n-1] > 0 && n % (n - next[n-1]) == 0;
    }
}
