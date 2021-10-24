package com.sata.unionfind;

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
}
