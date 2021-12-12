package com.sata.graph.TopologicalSorting;

import java.util.*;

/**
 * LC 1203 双重拓扑排序
 */
public class SortItemsByGroups {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        //两层拓扑排序，group 层面和item 层面。
        //首先把不属于任何group的节点构造成group，放在从m开始的位置上
        for(int i = 0; i < n; i++) {
            if(group[i] == -1) {
                group[i] = m++;
            }
        }
        List<List<Integer>> groupGraph = new ArrayList<>(); //每个group的临接表
        for(int i = 0; i < m; i++) {
            groupGraph.add(new ArrayList<>());
        }
        List<List<Integer>> itemGraph = new ArrayList<>(); //每个item的临接表
        for(int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }
        int[] groupIndegree = new int[m]; //currently m groups
        int[] itemIndegree = new int[n]; //currently n items
        //分别构造group和item层面的临接表和入度映射
        for(int i = 0; i < n; i++) {
            int toGroup = group[i];
            List<Integer> beforeList = beforeItems.get(i);
            for(int pre : beforeList) {
                int fromGroup = group[pre];
                if(toGroup != fromGroup && !groupGraph.get(fromGroup).contains(toGroup)) {
                    groupGraph.get(fromGroup).add(toGroup); //pre所在的fromGroup临接表增加了i所在的toGroup
                    ++groupIndegree[toGroup]; //i所在的toGroup的入度+1
                }
                itemGraph.get(pre).add(i); //节点pre的临接表增加了i
                ++itemIndegree[i]; //节点i的入度+1
            }
        }
        //分别在group和item层面进行拓扑排序，可以共用一套代码
        int[] groupSort = helper(groupGraph, groupIndegree);
        int[] itemSort = helper(itemGraph, itemIndegree);
        //拓扑排序存在环，则结果为空
        if(groupSort.length == 0 || itemSort.length == 0) return new int[]{};
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for(int item : itemSort) {
            mp.putIfAbsent(group[item], new ArrayList<>());
            mp.get(group[item]).add(item);
        }
        int[] res = new int[n];
        int index = 0;
        for(int grp : groupSort) {
            if(mp.containsKey(grp)) {
                List<Integer> itemInGrp = mp.get(grp);
                for(int item : itemInGrp) {
                    res[index++] = item;
                }
            }
        }
        return res;
    }
    //拓扑排序代码，bfs，参数是临接表和入度映射，临接表就是当前节点作为前置节点先被访问，表里的节点才有可能被访问。
    private int[] helper(List<List<Integer>> graph, int[] indegree) {
        int[] res = new int[indegree.length];
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                q.add(i); //入度为0的节点进队列
            }
        }
        int count = 0, index = 0;
        while(!q.isEmpty()) {
            int node = q.poll();
            count ++;
            res[index ++] = node;
            for(int next : graph.get(node)) {
                --indegree[next];
                if(indegree[next] == 0){
                    q.add(next);
                }
            }
        }
        return count == indegree.length? res : new int[]{};
    }
}
