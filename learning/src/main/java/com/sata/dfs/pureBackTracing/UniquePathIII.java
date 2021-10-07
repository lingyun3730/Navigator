package com.sata.dfs.pureBackTracing;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * LC 980, it can be regarded as dfs + memo, memo is the current count of '0' grid.
 */
public class UniquePathIII {

    public static int uniquePathsIII(int[][] grid) {
        int count = 1; //start point should be included.
        int start = 0, end = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) {
                    count ++;
                    visited[i][j] = false;
                }
                if(grid[i][j] == 1) {
                    start = i;
                    end = j;
                }
            }
        }
        if(count == 0) return 0;
        AtomicInteger res = new AtomicInteger(0);
        dfs(grid, start, end, count, visited, res);
        return res.get();
    }

    /**
     * dfs + memo
     * @param grid
     * @param start
     * @param end
     * @param count : memo, regarded as the current status.
     * @param visited : one grid can only be visited once.
     * @return
     */
    public static void dfs(int[][] grid, int start, int end, int count, boolean[][] visited, AtomicInteger res) {
        int n = grid.length;
        int m = grid[0].length;
        if(start < 0 || start >= n || end < 0 || end >= m || grid[start][end] == -1) return;
        if(grid[start][end] == 2) {
           if(count == 0) {
               res.getAndIncrement();
           }
           return;
        }
        //grid[start][end] == 0, can go to the next step
        if(! visited[start][end]) {
            visited[start][end] = true;
            dfs(grid, start - 1, end, count - 1, visited, res);
            dfs(grid, start + 1, end, count - 1, visited, res);
            dfs(grid, start , end + 1, count - 1, visited, res);
            dfs(grid, start , end - 1, count - 1, visited, res);
            visited[start][end] = false;
        }
        return;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        int res = uniquePathsIII(grid);
        System.out.println(res);
    }

}
