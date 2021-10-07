package com.sata.dp.backpack.zeroone;

/**
 * LC 474 ： 二维0-1背包问题。
 */
public class OnesAndZeros {

    public int findMaxForm(String[] strs, int m, int n) {
        //这是有两个维度的0-1背包问题。
        //dp[i][j]表示有i个0和j个1的最大字串的size
        //dp[i][j] = max(dp[i][j], dp[i-0number][j-1number] + 1)
        //最原始的三维dp递推公式：
        //dp[x][i][j] = max(dp[x-1][i][j], dp[x-1][i-0number][j-1number] + 1)
        int[][] dp = new int[m + 1][n + 1];
        for(String str : strs) {
            int zeroNum = 0; //weight 1
            int oneNum = 0; //weight 2
            for(char c : str.toCharArray()) {
                if(c == '0') zeroNum ++;
                if(c == '1') oneNum ++;
            }
            for(int i = m; i >= zeroNum; i--) {
                for(int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-zeroNum][j-oneNum] + 1); //value 都是 1
                }
            }
        }
        return dp[m][n];
    }
}
