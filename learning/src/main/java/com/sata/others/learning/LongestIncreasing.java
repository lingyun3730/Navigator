package com.sata.others.learning;

public class LongestIncreasing {
    static final int[][] DIRECTIONS=new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
    public static int longestIncreasingPath(int[][] matrix) {
        int m=matrix.length,n=m>0?matrix[0].length:0,max=0;
        int[][] dp= new int[m][n];
        for(int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                max=Math.max(max,dfs(matrix,dp,i,j));
            }
        }
        return max;
    }

    static int dfs(int[][] matrix,int[][] dp,int i,int j) {
        if(dp[i][j]==0) {
            // not visited
            dp[i][j]=1;
            for(int[] d:DIRECTIONS) {
                if(getValue(matrix,i+d[0],j+d[1])>matrix[i][j]) {
                    // if neighbor is greater, compute neighbor first and update value with max
                    dp[i][j]=Math.max(dp[i][j],1+dfs(matrix,dp,i+d[0],j+d[1]));
                }
            }
        }
        return dp[i][j];
    }

    static int getValue(int[][] matrix,int i,int j) {
        int m=matrix.length,n=matrix[0].length;
        // if out of matrix, return min iteger so it won't continue
        if(i>=m||i<0||j>=n||j<0) return Integer.MIN_VALUE;
        return matrix[i][j];
    }

    public static void main(String[] args) {
        int[][] test = {{9,9,4},{6,6,8},{2,1,1}};
        int res = longestIncreasingPath(test);
        System.out.println(res);
    }
}
