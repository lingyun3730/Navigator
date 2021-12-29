package com.sata.dp.Palindromic;

/**
 * LC 516 两种遍历方式，从下向上和沿着对角线填充，子序列问题，定义dp[i][j]为i -> j之间的字符串中最长的回文子序列长度
 */
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseqI(String s) {
        //dp[i][j] 表示i - j之间回文序列的最大长度。
        //从下向上填充，保证下面的先被遍历到
        // dp[i][i] = 1
        //dp[0][n-1] is the result.
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for(int i = n-1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j-1] + 2;
                } else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]) ;
                }
            }
        }
        return dp[0][n-1];
    }

    public int longestPalindromeSubseqII(String s) {
        //dp[i][j] 表示i - j之间回文串的最大长度。
        //对角线填充，从对角线向上填充
        // dp[i][i] = 1
        //dp[0][n-1] is the result.
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for(int len = 1; len < n; len++) {
            for(int i = 0; i + len < n; i ++) {
                int j = i + len;
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j-1] + 2;
                } else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]) ;
                }
            }
        }
        return dp[0][n-1];
    }
}
