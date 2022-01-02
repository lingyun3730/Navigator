package com.sata.unionfind;

public class RankUnionFind {
    private int[] parents;
    private int[] rank;
    private int n;
    public RankUnionFind(int n) {
        this.n = n;
        parents = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++) {
            parents[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int i) {
        //带了路径压缩，让同一个group所有节点的父节点都指向同一个
        return i == parents[i] ? i : (parents[i] = find(parents[i]));
    }

    public void union(int i, int j) {
        //把简单的树向复杂的树上合并，也就是所谓的按秩合并
        int pi = find(i);
        int pj = find(j);
        if(pi == pj) return;
        if(rank[pi] >= rank[pj]) {
            parents[pj] = pi;
        }else{
            parents[pi] = pj;
        }
        if(rank[pi] == rank[pj]) { //新的父亲节点的rank + 1
            rank[pi] ++;
        }
    }

    public boolean isConnected(int i, int j) {
        return find(i) == find(j);
    }
}
