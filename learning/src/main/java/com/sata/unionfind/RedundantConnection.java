package com.sata.unionfind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LC 684
 */
public class RedundantConnection {
    class UnionFind {
        private int n;
        private int[] parents;
        UnionFind(int n) {
            this.n = n;
            parents = new int[n];
            for(int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }
        public int find(int i) {
            return i == parents[i] ? i : find(parents[i]);
        }
        public void union(int i, int j) {
            int fi = find(i);
            int fj = find(j);
            if(fi == fj) return;
            parents[fi] = fj; //fi的集合的元素全部link到fj上
        }
        public boolean connected(int i, int j) {
            int fi = find(i);
            int fj = find(j);
            return fi == fj;
        }
    }
    public int[] findRedundantConnection(int[][] edges) {
        //并查集经典题目
        UnionFind uf = new UnionFind(1005);
        for(int i = 0; i < edges.length; i++) {
            if(uf.connected(edges[i][0], edges[i][1])) return edges[i];
            else uf.union(edges[i][0], edges[i][1]);
        }
        return new int[0];
    }

    /**
     * DFS solution
     * @param edges
     * @return
     */

    public static int[] findRedundantConnectionII(int[][] edges) {
        //dfs solution
        Map<Integer, Set<Integer>> mp = new HashMap<>(); //每个节点的下个节点的集合
        for(int[] edge : edges) {
            //每添加一条边就判断是否成环
            //mp中已经包含了从edge[0] -> edge[1]的通路，再添加这个边就成环了
            if(hasLoop(edge[0], edge[1], mp, -1)) return edge;
            if(! mp.containsKey(edge[0])) {
                mp.put(edge[0], new HashSet<>());
            }
            mp.get(edge[0]).add(edge[1]);
            if(! mp.containsKey(edge[1])) {
                mp.put(edge[1], new HashSet<>());
            }
            mp.get(edge[1]).add(edge[0]);
        }
        return new int[0];
    }
    //是否成环的dfs
    private static boolean hasLoop(int start, int end, Map<Integer, Set<Integer>> mp, int pre) {
        if(mp.containsKey(start) && mp.get(start).contains(end))  return true;
        if(mp.containsKey(start)) {
            for(int node : mp.get(start)) {
                if(node == pre) continue;
                if(hasLoop(node, end, mp, start)) return true;
            }
        }
        return false;
    }
}
