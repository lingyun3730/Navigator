package com.sata.dfs.pureBackTracing.island;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LC 827
 */
public class MakeALargeIsland {
    /**
     * 思路是这样的：首先算出矩阵中所有的岛屿的面积，然后对这些岛屿分别进行染色 （染成 > 1的数字)，最后找矩阵中为0的位置，
     * 找该位置四邻不同颜色的岛屿，把这些岛屿的面积加起来，选举出最大值。
     */
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, { 0, 1}, {0, -1}};
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Map<Integer, Integer> mp = new HashMap<>(); //color, area
        int color = 1;
        int res = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    color ++; //染色的color > 1
                    int area = helper(grid, i, j, n, m, color);
                    mp.put(color, area);
                    res = Math.max(res, area); //面积最大的岛屿记录下来，有种EDGE case是，当整个矩阵都是1，没有0
                }
            }
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 0) {
                    Set<Integer> colors = new HashSet<>(); //记录下0位置周围有几个color，也就是有几个岛屿
                    for(int[] dir : dirs) {
                        if(isValid(i + dir[0], j + dir[1], n, m)) {
                            if(grid[i + dir[0]][j + dir[1]] != 0){
                                colors.add(grid[i + dir[0]][j + dir[1]]);
                            }
                        }
                    }
                    int area = 1;
                    for(int c : colors) { //把这些相邻的岛屿面积都加起来，再加上自己
                        area += mp.get(c);
                    }
                    res = Math.max(area, res); //选举出最大面积
                }
            }
        }
        return res;
    }

    private boolean isValid(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    /**
     * 求岛屿面积的dfs代码，因为有染色，所以不需要visited标记
     * @param grid
     * @param i
     * @param j
     * @param n
     * @param m
     * @param color
     * @return
     */
    private int helper(int[][] grid, int i, int j, int n, int m, int color) {
        if(i < 0 || i >=n || j <0 || j >= m  || grid[i][j] != 1) return 0;
        grid[i][j] = color;
        return 1 + helper(grid, i-1, j, n, m, color) + helper(grid, i+1, j, n, m, color)
                +helper(grid, i, j + 1, n, m, color) + helper(grid, i, j-1, n, m, color);
    }
}
