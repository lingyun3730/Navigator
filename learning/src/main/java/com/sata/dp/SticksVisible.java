package com.sata.dp;

/***
 * LC 1866
 * https://leetcode.com/problems/number-of-ways-to-rearrange-sticks-with-k-sticks-visible/
 */
public class SticksVisible {
    private int mod = (int) 1e9 + 7;
    private long[][] dp = new long[1005][1005];
    public int rearrangeSticks(int n, int k) {
        // dfs + memo
        return (int) (helper(n, k) % mod);
    }

    private long helper(int n, int k) {
        if(n == k) {
            return 1;
        }
        if(k == 0) {
            return 0;
        }
        if(dp[n][k] != 0) return (int) (dp[n][k] % mod);
        // dp[n][k] = dp[n-1][k-1] (1 at the first place)
        //           + dp[n-2][k-1] * (n-1)  (2 at the first place)
        //           + dp[n-3][k-1] * (n-1) * (n-2) (3 at the first place)
        //           +... + dp[n-k][k-1] * (n-1) * (n-2) * ... *(n-k+1) (k at the first place)
        //           + dp[k-1][k-1] * (n-1) * (n-2) * ... * k (n-k+1 at the first place)
        // dp[n-1][k] = dp[n-2][k-1] (1 at the first place)
        //           + dp[n-3][k-1] * (n-2)  (2 at the first place)
        //           + dp[n-4][k-1] * (n-2) * (n-3) (3 at the first place)
        //           +... + dp[n-k-1][k-1] * (n-2) * ... *(n-k) (k at the first place)
        //           + dp[k-1][k-1] * (n-2) * ... * k (n-k+1 at the first place)
        // to see that dp[n][k] = dp[n-1][k-1] + dp[n-1][k] * (n-1);
        long ans = 0;
        ans = (ans + helper(n-1, k-1)) % mod;
        ans = (ans + (helper(n-1, k)) * (n-1)) % mod;
        dp[n][k] = ans;
        return ans;
    }
}
