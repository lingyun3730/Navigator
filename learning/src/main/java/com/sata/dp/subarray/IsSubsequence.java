package com.sata.dp.subarray;

/**
 * LC 392
 */
public class IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        if(n == 0) return true;
        if(m == 0) return false;
        int[][] dp = new int[m + 1][n + 1];
        //dp[i][j] = dp[i-1][j-1] + 1 if(s.charAt(i) == t.charAt(j))
        //dp[i][j] = dp[i][j-1] if(s.charAt(i) != t.charAt(j))
        for(int i = 1; i <=m; i++) {
            for(int j = 1; j <= n; j++) {
                if(t.charAt(i-1) == s.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[m][n] == n; //判断两个字符串相同长度的子序列的长度是不是正好等于s的长度。
    }
}
