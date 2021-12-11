package com.sata.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//LC 1036
public class EscapeLargeMaze {
    int[][] dirs = {{0, 1},{1, 0},{-1, 0},{0, -1}};
    int N = 1000000;
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        return helper(source, target, blocked) && helper(target, source, blocked);
    }

    /**
     * //这里涉及到一个数学问题， 从source和从target出发，都能走超过20000个点，就说明起始点和终点都没有被200
     * 个点完全包围，所以是有解的， 200个block最多能包围 199 * 200 / 2 = 19900个点。
     * @param source
     * @param target
     * @param blocked
     * @return
     */
    boolean helper(int[] source, int[] target, int[][] blocked) {
        Set<Long> visited = new HashSet<>();
        for(int[] block : blocked) {
            visited.add((long) block[0] * N + block[1]); //必须转化成为long，否则会超时
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(source);
        visited.add((long) source[0] * N + source[1]);
        int cnt = 0;
        while(! q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            if(x == target[0] && y == target[1]) return true;
            for(int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(! isVisited(nx, ny, visited)) {
                    int[] newPoint = new int[] {nx, ny};
                    q.add(newPoint);
                    visited.add((long) nx * N + ny);
                    if(++cnt == 20000) return true;
                }
            }
        }
        return false;
    }

    private boolean isVisited(int x, int y, Set<Long> visited) {
        return visited.contains((long) x * N + y);
    }
}
