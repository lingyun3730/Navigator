package com.sata.dp.gamedp;

/**
 * LC 1510 game dp
 */
public class StoneGameIV {
    public boolean winnerSquareGame(int n) {
        //博弈型dp
        boolean[][] dp = new boolean[n+1][2];
        dp[0][0] = true;
        dp[0][1] = false;
        for(int i = 1; i * i <= n; i++) {
            dp[i*i][0] = true; //面对平方数作为先手是true
            dp[i*i][1] = false; //面对平方数作为后手是false
        }
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= i / 2; j++) {
                if(i - j * j >= 0) {
                    long index = i - j * j;
                    dp[i][0] = dp[i][0] || dp[(int) index][1];
                    if(dp[i][0]) break;
                }
            }
            dp[i][1] = !dp[i][0];
        }
        return dp[n][0]; //alice是先手
    }
}
