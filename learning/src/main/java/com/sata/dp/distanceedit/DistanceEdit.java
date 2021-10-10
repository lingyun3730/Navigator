package com.sata.dp.distanceedit;

/**
 * LC 115
 */
public class DistanceEdit {

    public int minDistance(String word1, String word2) {
        //dp[i][j]表示word1以 i-1 结尾，word2以 j - 1结尾的字符串的最小编辑距离
        //dp[i][j] = dp[i-1][j-1] if(word1[i-1] == word2[j-1])
        //dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1 if(word1[i-1] != word2[j-1]).
        //dp[i-1][j] + 1表示删除word1的一个元素， dp[i][j-1] + 1表示删除word2的一个元素， dp[i-1][j-1] + 1表示替换word1[i-1]和word2[j-1]
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 0; i <= n; i++) dp[i][0] = i;
        for(int i = 0; i <= m; i++) dp[0][i] = i;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                }
            }
        }
        return dp[n][m];
    }
}
