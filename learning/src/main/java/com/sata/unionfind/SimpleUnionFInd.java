package com.sata.unionfind;

public class SimpleUnionFInd {
    private int n;
    private int[] parents;
    SimpleUnionFInd(int n) {
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
