package com.sata.dp.regular;

/**
 * LC 790
 */
public class DominoAndTrominoTiling {
    public int numTilings(int n) {
        //用dp[i] i > 0 表示i的时候有多少种组合方式。
        //dp[0] = 1 (when i = 0, we return 0)
        //dp[1] = 1
        //dp[2] = 2
        //dp[3] = 3 + 2*1 = dp[1] + dp[2] + 2 * dp[0] = 5
        //dp[4] = dp[3] + dp[2] + 2 *(dp[1] + dp[0]) = 5 + 2 + 2 * (1 + 1) = 11
        //dp[i] = dp[i-1] + dp[i-2] + 2 * (dp[i-3] + ...+ dp[0])
        //      = dp[i-1] + dp[i-3] + dp[i-2] + dp[i-3] + 2 *(dp[i-4] +...+dp[0])
        //      = dp[i-1] + dp[i-3] + dp[i-1]
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++) {
            dp[i] = (dp[i-1] * 2 + dp[i-3]) % 1000000007 ;
        }
        return (int) dp[n];
    }
}
