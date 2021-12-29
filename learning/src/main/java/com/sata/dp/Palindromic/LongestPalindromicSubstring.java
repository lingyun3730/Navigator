package com.sata.dp.Palindromic;

/**
 * LC 5， 子串问题，需要定义dp[i][j]为boolean，表示i-j之间的子串是不是一个回文串
 */
public class LongestPalindromicSubstring {
    //方法一：非动态规划
    int left = 0;
    int right = 0;
    int maxLen = 0;
    public String longestPalindrome(String s) {
        //可以不用动态规划
        int n = s.length();
        for(int i = 0; i < n ; i++) {
            extendHelper(s, i, i, s.length()); //一个字符为中心
            extendHelper(s, i, i + 1, s.length()); //两个字符为中心
        }
        return s.substring(left, right + 1);
    }
    private void extendHelper(String s, int start, int end, int len) {
        while(start >= 0 && end < len && s.charAt(start) == s.charAt(end)) {
            if(end - start + 1 > maxLen) {
                left = start;
                right = end;
                maxLen = end - start + 1;
            }
            start--;
            end++;
        }
    }

    //方法二： 动态规划
    public String longestPalindromeII(String s) {
        //dp, 上三角，从下向上从左往右填充
        int n = s.length();
        int left = 0;
        int right = 0;
        int maxLen = 0;
        boolean[][] dp = new boolean[n][n];
        for(int i = n - 1; i >=0; i--) {
            for(int j = i; j < n; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    if(j - i <= 1) {
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if(dp[i][j] && maxLen <j - i + 1) {
                    maxLen = j - i + 1;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }
}
