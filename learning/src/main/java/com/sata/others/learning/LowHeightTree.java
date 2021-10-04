package com.sata.others.learning;


import java.util.*;

public class LowHeightTree {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        List<List<Integer>> linkedTable = new ArrayList<>(n); //临接表
        for(int i = 0; i < n; i++) {
            linkedTable.add(new ArrayList<>());
        }
        int[] degree = new int[n]; //出入度，无向图，入度等于出度
        //图的初始化
        for(int[] edge : edges) {
            linkedTable.get(edge[0]).add(edge[1]);
            linkedTable.get(edge[1]).add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i<n; i++) {
            if(degree[i] == 1) {
                q.add(i);
            }
        }

        while(! q.isEmpty()) {
            int size = q.size();
            res.clear();
            for(int i = 0; i < size; i++) {
                int t = q.poll();
                res.add(t);
                for(int j : linkedTable.get(t)) {
                    degree[j]--;
                    if(degree[j] == 1) q.add(j);
                }
            }
        }
        return res;
    }
}
