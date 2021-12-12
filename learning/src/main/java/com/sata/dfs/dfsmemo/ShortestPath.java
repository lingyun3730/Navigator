package com.sata.dfs.dfsmemo;

/**
 * LC 1293
 */
public class ShortestPath {
    private static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int shortestPath(int[][] grid, int k) {
        //dfs + memo
        int n = grid.length;
        int m = grid[0].length;
        int[][][] dp = new int[n][m][k + 1];
        boolean[][][] visited = new boolean[n][m][k + 1];
        int res = helper(grid, 0, 0,  n, m,0, k, dp, visited);
        return res == n * m ? -1 : res;
    }

    private static int helper(int[][] grid, int i, int j,  int n, int m, int nk, int k, int[][][] dp, boolean[][][] visited) {
        if (i == n - 1 && j == m - 1 && nk <= k) return 0;
        if (dp[i][j][nk] > 0) return dp[i][j][nk];
        int step = n * m;
        for (int[] dir : dirs) {
            int nx = i + dir[0];
            int ny = j + dir[1];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny][nk]) continue;
            if (grid[nx][ny] == 0 || (grid[nx][ny] == 1 && nk < k)) {
                visited[nx][ny][nk] = true;
                step = Math.min(step, helper(grid, nx, ny, n, m, nk + grid[nx][ny], k, dp, visited) + 1);
                visited[nx][ny][nk] = false;
            }
        }
        dp[i][j][nk] = step;
        return dp[i][j][nk];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}};
        int res = shortestPath(grid, 1);
        System.out.println(res);
    }

}
