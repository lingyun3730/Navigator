package com.sata.unionfind;

/**
 * Union find class， size optimization
 */
public class UnionFind {
    //parents[i] 表示第i个元素的父节点
    private int[] parents;

    //sz[i]表示以i为根的group size
    private int[] sz;

    //number size
    private int count;

    UnionFind(int n) {
        parents = new int[n];
        sz = new int[n];
        //初始化
        for(int i = 0;  i < n; i++) {
            parents[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int i) {
        //不断找自己的父节点，直到节点本身就是根节点
        while(i != parents[i]) {
           i = parents[i];
        }
        return i;
    }

    public void union(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        if(pi == pj) return;
        if(sz[pi] < sz[pj]) {
            parents[pi] = pj;
            sz[pj] +=  sz[pi];
        }else {
            parents[pj] = pi;
            sz[pi] += sz[pj];
        }
    }

    public boolean isConnected(int i, int j) {
        return find(i) == find(j);
    }

}
