package com.sata.others.learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind {
    public static int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
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
        if (mp.get(i) == null) return;
        for (int tmp : mp.get(i)) {
            dfs(mp, group, tmp, visited);
        }
    }

    public static void main(String[] args) {
/**[5,1,2,4,3]
 [1,5,4,2,3]
 [[0,4],[4,2],[1,3],[1,4]]
 */
        int[] source = {5,1,2,4,3};
        int[] target = {1,5,4,2,3};
        int[][] sw = {{0,4},{4,2}, {1,3}, {1,4}};
        int res = minimumHammingDistance(source, target, sw);
        System.out.println(res);
    }

}