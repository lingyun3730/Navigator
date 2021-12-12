package com.sata.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC  1293， 最短路径问题，优先想到bfs, 然后有obstacles可以消掉，因此状态是三个参数的，横纵坐标和可以消掉的个数k
 */
public class ShortestPath {

    private int[][] dirs = new int[][] {{-1, 0},{0, 1},{1, 0},{0, -1}};
    public int shortestPath(int[][] grid, int k) {
        //bfs
        int n = grid.length;
        int m = grid[0].length;
        int res = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][k + 1]; //三参数的访问数组，横坐标，纵坐标和k
        if(grid[0][0] == 1) k--;
        visited[0][0][k] = true;
        q.add(new int[] {0, 0, k});
        while(! q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int[] tmp = q.poll();
                if(tmp[0] == n-1 && tmp[1] == m-1) return res;
                for(int[] dir : dirs) {
                    int nx = tmp[0] + dir[0];
                    int ny = tmp[1] + dir[1];
                    int nk = tmp[2];
                    if(nx < 0 || nx >=n || ny < 0 || ny >= m) continue;
                    if(grid[nx][ny] == 0 || (grid[nx][ny] == 1 && nk > 0)) {
                        if(grid[nx][ny] == 1) {
                            nk--;
                        }
                        if(! visited[nx][ny][nk]) {
                            visited[nx][ny][nk] = true;
                            q.add(new int[] {nx, ny, nk});
                        }
                    }
                }
            }
            res ++;
        }
        return -1;
    }
}
