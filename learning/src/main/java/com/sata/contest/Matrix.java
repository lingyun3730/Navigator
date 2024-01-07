package com.sata.contest;

public class Matrix {
    public static int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] res = new int[n-2][n-2];
        for(int i = 0; i < n - 2; i++) {
            for(int j = 0; j < n - 2; j ++) {
                res[i][j] = calculateMax(grid, i, j);
            }
        }
        return res;
    }

    private static int calculateMax(int[][] grid, int i, int j) {
        int max = grid[i][j];
        for(int k = 0; k <= 2; k ++) {
            for(int l = 0; l <= 2; l ++) {
                max = Math.max(max, grid[i + k][j + l]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{9,9,8,1},{5,6,2,6},{8,2,6,4}, {6,2,2,2}};
        int [][] res = largestLocal(matrix);
        System.out.println(res);
    }
}
