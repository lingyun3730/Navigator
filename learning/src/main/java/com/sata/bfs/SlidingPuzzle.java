package com.sata.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * LC 773，这个题不难的，就是代码长了点而已，矩阵类典型而直观的bfs题目
 */
public class SlidingPuzzle {
    private String dest = "123450";
    private int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int slidingPuzzle(int[][] board) {
        if(toString(board).equals(dest)) return 0;
        //least number : BFS
        Queue<int[][]> q = new LinkedList<>();
        q.add(board);
        Set<String> visited = new HashSet<>();
        visited.add(toString(board));
        int res = 0;
        while(! q.isEmpty()) {
            res ++;
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int[][] tmp = q.poll();
                for(int[] dir : dirs) {
                    int[][] afterMove = moveOne(tmp, dir); //move one dir
                    if(afterMove.length == 0) continue; //invalid, continue
                    String strAfter = toString(afterMove);
                    if(strAfter.equals(dest)) return res; //find the dest, return.
                    if(visited.contains(strAfter)) continue;
                    visited.add(strAfter);
                    q.add(afterMove);
                }
            }
        }
        return -1;
    }
    //朝着一个方向move就行了，注意要复制数组
    private int[][] moveOne(int[][] tmp, int[] dir) {
        int[][] res = new int[2][3];
        int zerox = -1;
        int zeroy = -1;
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 3; j++) {
                res[i][j] = tmp[i][j];
                if(tmp[i][j] == 0) {
                    zerox = i;
                    zeroy = j;
                }
            }
        }
        int newx = zerox + dir[0];
        int newy = zeroy + dir[1];
        if(newx < 0 || newx >= 2 || newy < 0 || newy >= 3) return new int[][]{};
        int t = res[newx][newy];
        res[newx][newy] = 0;
        res[zerox][zeroy] = t;
        return res;
    }
    //为了去重，把矩阵转化为字符串
    private String toString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }
}
