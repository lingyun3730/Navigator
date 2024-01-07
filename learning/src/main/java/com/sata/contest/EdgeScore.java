package com.sata.contest;

public class EdgeScore {
    public static int edgeScore(int[] edges) {
        int n = edges.length;
        long[] res = new long[n];
        long maxVal = 0;
        for(int i = 0; i < edges.length; i++) { // edge start node
            res[edges[i]] += i;
            if(res[edges[i]] > maxVal) maxVal = res[edges[i]];
        }
        for(int j = 0; j < res.length; j++) {
            if(res[j] == maxVal) return j;
        }
        return 0;
    }
    public static void main(String[] args) {

        int res = edgeScore(new int[]{1,0,0,0,0,7,7,5});
        System.out.println(res);
    }

}
