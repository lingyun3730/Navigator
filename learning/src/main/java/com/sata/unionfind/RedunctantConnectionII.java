package com.sata.unionfind;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 685
 */
public class RedunctantConnectionII {

    class UnionFind {
        int n;
        int[] parents;
        UnionFind(int n) {
            this.n = n;
            parents = new int[n];
            for(int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }
        public int find(int i){
            return i == parents[i] ? i : find(parents[i]);
        }
        public void union(int i, int j) {
            int pi = find(i);
            int pj = find(j);
            if(pi == pj) return;
            parents[pi] = pj;
        }
        public boolean connected(int i, int j) {
            return find(i) == find(j);
        }
    }

    //define a function to judge if the edges can construct a tree.
    private boolean isTreeAfterDeleleEdge(int[][] edges, int deleteEdge) {
        UnionFind uf = new UnionFind(1006);
        for(int i = 0; i < edges.length; i++) {
            if(i == deleteEdge) continue; //continue for this edge
            if(uf.connected(edges[i][0], edges[i][1])) return false;
            uf.union(edges[i][0], edges[i][1]); //do union
        }
        return true;
    }

    //get the edge to delete when there is a loop there
    private int[] getDeleteEdge(int[][] edges) {
        UnionFind uf = new UnionFind(1006);
        for(int i = 0; i < edges.length; i++) {
            if(uf.connected(edges[i][0], edges[i][1])) return edges[i];
            uf.union(edges[i][0], edges[i][1]);
        }
        return new int[0];
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        //two cases to be considered.

        //case 1: in degree is 2
        int[] indegree = new int[1006];
        List<Integer> twoIndegree = new ArrayList<>(); //记录入度为2的节点所在的有向边的下标
        for(int[] edge : edges){
            indegree[edge[1]] ++;
        }
        for(int i = 0; i < edges.length; i++) {
            if(indegree[edges[i][1]] == 2) {
                twoIndegree.add(i); // indegree is 2, collect all the edges.
            }
        }
        if(twoIndegree.size() > 0) {
            for(int i = twoIndegree.size() - 1;  i>= 0; i--) { // note : in reverse order
                int toDeleteEdgeIndex = twoIndegree.get(i);
                if(isTreeAfterDeleleEdge(edges, toDeleteEdgeIndex)) return edges[toDeleteEdgeIndex];
            }
        }

        //case 2: has loop
        return getDeleteEdge(edges);
    }
}
