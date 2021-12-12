package com.sata.bfs;

import java.util.*;

/**
 * LC 815
 */
public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target) return 0;
        //bfs，需要建立每个站点有哪些公交可以到达的map
        Map<Integer, List<Integer>> mp = new HashMap<>(); //站点，公交线路list
        for(int i = 0; i < routes.length; i++) {
            int[] route = routes[i];
            for(int stop : route) {
                mp.putIfAbsent(stop, new ArrayList<>());
                mp.get(stop).add(i);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        int res = 0;
        Set<Integer> visited = new HashSet<>();
        while(!q.isEmpty()) {
            res ++; //新的一层，层数 + 1
            int n = q.size() ;
            for(int i = 0; i < n; i++) { //找出所有的从该层的站可以直接到达的其他站作为新的一层，加到queue里
                int stop = q.poll();
                for(int bus : mp.get(stop)) { //经过该站的所有bus进行遍历
                    if(visited.contains(bus)) continue; //该bus被访问过，该bus的所有站点也应该被访问过。
                    visited.add(bus);
                    for(int newStop : routes[bus]) {
                        if(newStop == target) return res;
                        q.add(newStop); // 即使添加了本层已经访问过的站点也没关系，因为该bus一定是被访问过了
                    }
                }
            }
        }
        return -1;
    }
}
