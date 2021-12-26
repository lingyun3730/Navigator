package com.sata.dfs.dfsmemo;

/**
 * LC 664
 */
public class StrangePrinter {
    /**
     * 举个例子，aaa -> 1； ab -> 2； aba -> a, ba -> 2，可以看作b 和 a分别打印；
     * 对于字符串"abbac"，因为位置0上的a和位置3上的a相同，那么整个字符串的步数相当于"bb"和"ac"的步数之和，为3
     * 我们关心的是字符相等的地方，对于[i, j]范围的字符，我们从i+1位置上的字符开始遍历到j，
     * 如果和i位置上的字符相等，我们就以此位置为界，将[i+1, j]范围内的字符拆为两个部分，将二者的dp值加起来，和原dp值相比，取较小的那个。
     * dp[i][j] = min(dp[i][j], dp[i + 1][k - 1] + dp[k][j]       (s[k] == s[i] and i + 1 <= k <= j)
     */
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int res = dfs(s, 0, n-1, dp);
        return res;
    }

    private int dfs(String s, int start, int end, int[][] dp) {
        if(start == end) {
            return 1;
        }
        if(start > end) return 0;
        if(dp[start][end] != 0) return dp[start][end];
        dp[start][end] = 1 + dfs(s, start + 1, end, dp);
        for(int i = start + 1; i <= end; i++) {
            if(s.charAt(i) == s.charAt(start)) {
                dp[start][end] = Math.min(dp[start][end], dfs(s, start + 1, i - 1, dp) + dfs(s, i, end, dp));
            }
        }
        return dp[start][end];
    }
}
