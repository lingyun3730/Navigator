package com.sata.graph.Dijkstra;

import java.util.*;

/**
 * LC 787
 */
public class CheapestFlights {
    /**
     * BFS solution
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //you can just use bfs directly, it seems a shortest path problem
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {src, 0}); //到某个地点的花费（其中一种情况），初始设置为(src, 0)
        Map<Integer, List<int[]>> mp = new HashMap<>();
        for(int[] flight : flights) {
            List<int[]> list = mp.getOrDefault(flight[0], new ArrayList<>());
            list.add(new int[] {flight[1], flight[2]}); //从flight[0]到其他站点的花费的list
            mp.put(flight[0], list);
        }
        int step = 0;
        int[] costs = new int[n]; //存储到达第i个station的最小花费
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int[] tmp = q.poll();
                if(costs[tmp[0]] > tmp[1]) costs[tmp[0]] = tmp[1]; //更新从src到达某个station的最小花费
                if(mp.containsKey(tmp[0])) {
                    for(int[] road : mp.get(tmp[0])) {
                        if(costs[road[0]] > road[1] + tmp[1]) { //找到一条到达road[0] station的更小花费，入队
                            q.add(new int[] {road[0], road[1] + tmp[1]});
                        }
                    }
                }

            }
            if(step ++ > k) break;
        }
        return costs[dst] == Integer.MAX_VALUE? -1 : costs[dst];
    }
}
