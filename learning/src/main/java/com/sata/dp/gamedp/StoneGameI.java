package com.sata.dp.gamedp;

/**
 * LC 877
 */
public class StoneGameI {
    /**
     * dp 解法1
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        //典型的game dp
        //dp[i][j][0] = max(dp[i + 1][j][1] + piles[i], dp[i][j - 1][1] + piles[j])
        //dp[i][j][1] = dp[i][j][0] == dp[i + 1][j][1] + piles[i]? dp[i+1][j][0] : dp[i][j-1][0];
        int n = piles.length;
        int[][][] dp = new int[n][n][2];
        //对角线初始化
        for(int i = 0; i < n; i++) {
            dp[i][i][0] = piles[i]; //先手拿到
            dp[i][i][1] = 0; //后手没有拿到
        }
        //从下向上，从左向右
        for(int i = n-1; i >=0; i--) {
            for(int j = i + 1; j < n; j++) {
                int left = piles[i] + dp[i + 1][j][1];
                int right = piles[j] + dp[i][j-1][1];
                dp[i][j][0] = Math.max(left, right);
                dp[i][j][1] = dp[i][j][0] == left ? dp[i + 1][j][0] : dp[i][j-1][0];
            }
        }
        return dp[0][n-1][0] > dp[0][n-1][1];
    }

    /**
     * DP 解法 2
     * @param piles
     * @return
     */
    public boolean stoneGameII(int[] piles) {
        //dp[i][j]表示i->j区间内先手比后手多拿的数字
        int n = piles.length;
        int[][] dp = new int[n][n];
        //对角线填充，先初始化对角线
        for(int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }
        for(int i = n-1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                //先拿i, 面对i+1 ->j，自己是后手，对手比自己多拿了dp[i+1][j]，
                //先拿j, 面对i -> j - 1, 自己同样是后手，对手比自己多拿了dp[i][j-1].
                dp[i][j] = Math.max(piles[i] - dp[i+1][j], piles[j] - dp[i][j-1]);
            }
        }

        return dp[0][n-1] > 0;//面对0-> n-1，alice是先手
    }

}
