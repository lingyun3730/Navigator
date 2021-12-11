package com.sata.dp.gamedp;

/**
 * LC 1140
 */
public class StoneGameII {
    /**
     * DP 解法，因为是从左向右取，所以应该是n-1向前遍历，结果是面对从0开始到n-1的数组集合的结果
     * @param piles
     * @return
     */
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        //dp[i][j]表示面对i -> n-1，M取j的前提下，先手取到的最大的总和。
        int[][] dp = new int[n][n+1]; //i表示坐标， j表示M, M取0无意义
        int[] resum = new int[n];
        resum[n-1] = piles[n-1];
        for(int i = n-2; i >= 0; i--) {
            resum[i] = resum[i+1] + piles[i];
        }
        //初始化dp，当剩下的石头数小于2M时，可以全部拿掉
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= n; j++) {
                if(i + 2*j >= n) {
                    dp[i][j] = resum[i];
                }
            }
        }
        //遍历
        for(int i = n-1; i >= 0; i--) {
            for(int j = n-1; j >= 0; j--) {
                for(int x = 1; x <= 2 * j && i + x < n; x++) {
                    //面对i->j,从i开始连续取x个，剩下的是从i + x 开始的，是对手取。
                    dp[i][j] = Math.max(dp[i][j], resum[i] - dp[i+x][Math.max(x, j)]);
                }
            }
        }
        return dp[0][1];
    }

    /**
     * Top - down
     * @param piles
     * @return
     */

    public int stoneGameII_(int[] piles) {
        //top- > down解法
        int n = piles.length;
        int[][] memo = new int[n][n]; //memo[i][j] 表示面对i -> n-1，M取j的前提下，先手取到的最大的总和。
        int[] resum = new int[n];
        resum[n-1] = piles[n-1];
        for(int i = n-2; i >= 0; i--) {
            resum[i] = resum[i + 1] + piles[i];
        }
        return helper(0, 1, resum, memo);
    }

    private int helper(int index, int x, int[] resum, int[][] memo) {
        if(index + 2 * x  >= resum.length) return resum[index]; //剩下的元素个数小于2 * x，可以全部取走
        if(memo[index][x] > 0) return memo[index][x];
        int res = 0;
        for(int i = 1; i <= 2 * x; i++) {
            res = Math.max(res, resum[index] - helper(index + i, Math.max(x, i), resum, memo));
        }
        memo[index][x] = res;
        return memo[index][x];
    }
}
