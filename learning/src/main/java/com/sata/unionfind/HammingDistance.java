package com.sata.unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC 1722
 */
public class HammingDistance {

    static class UnionFindClass {
        int[] parents ; // 0 -> n-1
        public UnionFindClass(int n) {
            parents = new int[n];
            for(int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }
        int find(int i){
            if(i == parents[i]) {
                return i;
            }
            parents[i] = find(parents[i]);
            return parents[i];
        }

        void union(int i, int j) {
            int pi = find(i);
            int pj = find(j);
            if(pi != pj) {
                parents[pi] = pj;
            }
        }
    }

    /**
     * Solution 1: DFS
     * @param source
     * @param target
     * @param allowedSwaps
     * @return
     */
    public static int minimumHammingDistanceI(int[] source, int[] target, int[][] allowedSwaps) {
        //把allowedSwaps转化成为临接表
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int[] tmp : allowedSwaps) {
            List<Integer> r = mp.getOrDefault(tmp[0], new ArrayList<>());
            r.add(tmp[1]);
            mp.put(tmp[0], r);
            List<Integer> rr = mp.getOrDefault(tmp[1], new ArrayList<>());
            rr.add(tmp[0]);
            mp.put(tmp[1], rr);
        }
        int n = source.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < source.length; i++) {
            List<Integer> group = new ArrayList<>();
            //找到所有的联通子图，子图内的下标的数字是可以任意互换的
            dfs(mp, group, i, visited);
            Map<Integer, Integer> count = new HashMap<>();
            for (int t : group) {
                count.put(source[t], count.getOrDefault(source[t], 0) + 1);
            }
            for (int t : group) {
                if (count.containsKey(target[t]) && count.get(target[t]) > 0) {
                    --n;
                    count.put(target[t], count.getOrDefault(target[t], 0) - 1);
                }
            }
        }
        return n;
    }

    private static void dfs(Map<Integer, List<Integer>> mp, List<Integer> group, int i, boolean[] visited) {
        if (visited[i]) return;
        visited[i] = true;
        group.add(i);
//        if (mp.get(i) == null) return;
        for (int tmp : mp.get(i)) {
            dfs(mp, group, tmp, visited);
        }
    }

    /**
     * Solution 2: Union find
     * @param
     */
    public static int minimumHammingDistanceII(int[] source, int[] target, int[][] allowedSwaps) {
        UnionFindClass uf = new UnionFindClass(source.length);
        for(int[] tmp : allowedSwaps) {
            uf.union(tmp[0], tmp[1]);
        }

        //<parent, group member>
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for(int i = 0; i < source.length; i++) {
            int pi = uf.find(i);
            mp.putIfAbsent(pi, new ArrayList<>());
            mp.get(pi).add(i);
        }
        int hammingDistance = 0;
        for(Map.Entry<Integer, List<Integer>> entry : mp.entrySet()) {
            // <node, count> in each group
            Map<Integer, Integer> tmp = new HashMap<>();
            for(int i = 0; i < entry.getValue().size(); i++) {
                int node = source[entry.getValue().get(i)];
                tmp.putIfAbsent(node, 0);
                tmp.put(node, tmp.get(node) + 1);
            }
            for(int j = 0; j < entry.getValue().size(); j++) {
                int node = target[entry.getValue().get(j)];
                if (! tmp.containsKey(node) || tmp.get(node) <= 0){
                    hammingDistance ++;
                } else{
                    tmp.put(node, tmp.get(node) - 1);
                }
            }
        }
        return hammingDistance;
    }


    public static void main(String[] args) {
/**[5,1,2,4,3]
 [1,5,4,2,3]
 [[0,4],[4,2],[1,3],[1,4]]
 */
        int[] source = {1,2,3,4};
        int[] target = {2,1,4,5};
        int[][] sw = {{0,1},{2,3}};
        int res = minimumHammingDistanceI(source, target, sw);
        int res1 = minimumHammingDistanceII(source,target, sw);
        System.out.println(res);
        System.out.println(res1);
        assert res == 1;
        assert res1 == 1;
    }

}