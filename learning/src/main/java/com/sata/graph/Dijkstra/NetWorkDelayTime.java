package com.sata.graph.Dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LC 743 dijkstra 堆优化，时间复杂读是O(E + VlogV)
 */
public class NetWorkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] edges = new int[101][101];
        for(int i = 0; i < 101; i++) {
            Arrays.fill(edges[i], -1);
        }
        for(int[] time : times) {
            edges[time[0]][time[1]] = time[2]; //构造临接矩阵，其实临接表也是可以的。
        }
        //按照距离从小到大依次排序，距离短的优先被填充dist数组，后面再出现的该节点会被忽略，因为到这个节点的距离一定是更长的
        //<从起点的距离，dest节点>
        PriorityQueue<int[]> q = new PriorityQueue<int[]>(
                (info1, info2) -> info1[0] - info2[0]); //距离小的在堆顶，
        int[] dist = new int[101];
        Arrays.fill(dist, -1); //到每个节点的最短距离dist数组，初始化为-1。
        q.add(new int[] {0, k});
        while(!q.isEmpty()) {
            int[] info = q.poll();
            int d = info[0];
            int node = info[1];
            if(dist[node] != -1) continue; //之前已经找到了到node的最短路径，这个距离一定是偏长的，continue
            dist[node] = d; //填充到这个节点的最短距离
            for(int i = 0; i < 101; i++) { //以这个节点为起点，向着其他点进行遍历，看从该点到其他点的能否构成最短距离
                if(edges[node][i] != -1 && dist[i] == -1) {
                    q.add(new int[] {d + edges[node][i], i});
                }
            }
        }
        int ans = 0;
        for(int i = 1; i <= n; i++) {
            if(dist[i] == -1) return -1;
            ans = Math.max(ans, dist[i]);
        }
        return ans;
    }
}
