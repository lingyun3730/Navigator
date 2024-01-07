package com.sata.OJ.bfs;

import java.util.*;

public class Main {
    private static int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cnt = in.nextInt();
        List<int[][]> input = new ArrayList<>();
        List<int[]> x = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            int N = in.nextInt();
            int M = in.nextInt();
            int K = in.nextInt();
            int[][] p = new int[N][M];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    p[j][k] = in.nextInt();
                }
            }
            input.add(p);
            x.add(new int[]{N, M, K});
        }
        for (int t = 0; t < cnt; t++) {
            System.out.println(bfs(input.get(t), x.get(t)[0], x.get(t)[1], x.get(t)[2]));
        }
    }

    private static long bfs(int[][] p, int N, int M, int K) {
        if (K >= N * M) return 0;
        long res = p[0][0];

        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
                    if (res > p[i][j]) {
                        res = p[i][j];
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
                    if (res == p[i][j]) {
                        q.add(new int[]{i, j, (int) res});
                    }
                }
            }
        }

        int count = 0;
        while (N * M - count > K || !q.isEmpty()) {
            if (!q.isEmpty()) {
                int[] cur = q.poll();
                if(cur[2] <= res) {
                    visited[cur[0]][cur[1]] = true;
                    count++;
                    if (N * M - count <= K) {
                        return res;
                    }
                } else {
                    q.add(cur);
                }
            } else if (N * M - count > K) {
                long low = 100000000;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (visited[i][j]) {
                            for (int[] dir : dirs) {
                                if (i + dir[0] < 0 || i + dir[0] >= N
                                        || j + dir[1] < 0 || j + dir[1] >= M
                                        || visited[i + dir[0]][j + dir[1]]) {
                                    continue;
                                }
                                if (p[i + dir[0]][j + dir[1]] < low) {
                                    low = p[i + dir[0]][j + dir[1]];
                                    if (low < res) {
                                        low = res;
                                    }
                                }
                                q.add(new int[]{i + dir[0], j + dir[1], p[i + dir[0]][j + dir[1]]});
                            }
                        }
                    }
                }
                res = low;
            }
        }
        return res;
    }
}
