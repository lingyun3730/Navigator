package com.sata.dp.regular;

/**
 * https://www.lintcode.com/problem/1177/description?utm_source=sc%20-libao-hwx
 * 一个长度为n的字符串，包含A， P， L， 求合法的字符串的个数，要求不能超过一个A和两个连续的L.
 */
public class StudentShow {
    /**
     * @param n: an integer
     * @return: return an integer
     */
    int modd = 1000000007;
    public int checkRecord(int n) {
        // write your code here
        //仅仅考虑 P和L，不考虑 A，那么dp[i]表示长度为i的字符串合法的个数
        //dp[i] <- dp[i-1] + "P";
        //dp[i] <- dp[i-2] + "PL";
        //dp[i] <- dp[i-3] + "PLL";
        long[] dp = new long[n+1];
        dp[0] = 1;
        if(n >= 1) dp[1] = 2;
        if(n >= 2) dp[2] = 4;
        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i -1] + dp[i-2] + dp[i-3];
            dp[i] %= modd;
        }
        long res = 0;
        //considering that A can be in each position.
        for(int i = 1; i <= n; i++) {
            res += dp[i-1] * dp[n-i];
        }
        return (int) (res + dp[n]) % modd; // considering no A in the string
    }

    public int checkRecordII(int n) {
        // write your code here
        long[][] dp = new long[2][3]; //rolling array, index i is ignored
        dp[0][0] = 1;
        for(int i = 0; i < n; i++) {
            long[][] dpNew = new long[2][3];
            // position i has A
            for(int k = 0; k <= 2; k++) {
                // total number of A is 1, its status can only be migrated from total number of A is 0
                // continuous number of L is reset to 0
                dpNew[1][0] = (dpNew[1][0] + dp[0][k]) % modd;
            }
            // position i has P
            for(int j = 0; j <= 1; j++) {
                for(int k = 0; k <= 2; k++) {
                    //continuous number of L is reset to 0,
                    //total number of A is kept, which is either 0 or 1.
                    dpNew[j][0] = (dpNew[j][0] + dp[j][k]) % modd;
                }
            }
            // position i has L
            for(int j = 0; j <= 1; j++) {
                for(int k = 1; k <= 2; k++) {
                    //continuous number of L can only be migrated from 0 and 1.
                    dpNew[j][k] = (dpNew[j][k] + dp[j][k-1]) % modd;
                }
            }
            //rolling array, setback
            dp = dpNew;
        }
        long res = 0;
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 3; j++) {
                res += dp[i][j];
                res %= modd;
            }
        }
        return (int) (res  % modd);
    }
}
