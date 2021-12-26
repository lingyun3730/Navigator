package com.sata.dfs.dfsmemo;

/**
 * LC 546
 */
public class MoveBoxes {
    public int removeBoxes(int[] boxes) {
        //三维动态规划，推箱子问题，dp[i][j][k]表示i -> j之间的子数组，i左边与i相等的数字的个数是k 所得的分数。
        //如果移除箱子i，则所得的分数可以作为初始化的值 dp[i][j][k] = dp[i + 1][j][0] + (k+1) * (k+1)
        //i和i的左边一共有k+1个和i相同的箱子可以一起消掉，这是至少的一种情况。
        //在i + 1 -> j之间，当发现p位置上的箱子和i相同，则可以和i一起消掉，那么先移除i+1 -> p-1之间的箱子，
        //再移除p -> j之间的箱子，p左边一共有 k + 1个箱子与p相同
        //dp[i][j][k] = max(dp[i][j][k], dp[i + 1][p-1][0] + dp[p][j][ k + 1]) (if boxes[i] == boxes[p])
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return dfs(0, n-1, 0, boxes, dp);
    }
    private int dfs(int i, int j, int k, int[] boxes, int[][][] dp) {
        if(j < i) return 0;
        if(dp[i][j][k] > 0) return dp[i][j][k];
        dp[i][j][k] = (1 + k) * (1 + k) + dfs(i + 1, j, 0, boxes, dp);
        for(int x = i + 1;  x <= j; x ++) {
            if(boxes[x] == boxes[i]) {
                dp[i][j][k] = Math.max(dp[i][j][k], dfs(i + 1, x - 1, 0, boxes, dp) + dfs(x, j, k + 1, boxes, dp));
            }
        }
        return dp[i][j][k];
    }
}
