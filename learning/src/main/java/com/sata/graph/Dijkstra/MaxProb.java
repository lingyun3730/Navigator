package com.sata.graph.Dijkstra;

import java.util.*;

/**
 * LC 1514
 */
public class MaxProb {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        //狄杰斯特拉算法
        //step 1: 把edges改成临接表 <start,  List<<end, proc>>>,是无向图

        Map<Integer, Map<Integer, Double>> mp = new HashMap<>();
        for(int i = 0;  i < edges.length; i++) {
            if(!mp.containsKey(edges[i][0])) {
                mp.put(edges[i][0], new HashMap<>());
            }
            mp.get(edges[i][0]).put(edges[i][1], succProb[i]);
            if(!mp.containsKey(edges[i][1])) {
                mp.put(edges[i][1], new HashMap<>());
            }
            mp.get(edges[i][1]).put(edges[i][0], succProb[i]);
        }

        //dest数组，记录到节点i的prob
        double[] dest = new double[10001];
        Arrays.fill(dest, -1.0); //初始化为-1

        //最大堆， 存储pair (prob, destNode)，按照概率大小从大到小排序
        PriorityQueue<double[]> q = new PriorityQueue<double[]>(
                (info1, info2) -> info2[0] - info1[0] > 0 ? 1 : (info2[0] - info1[0] == 0? 0 : -1));
        q.add(new double[] {1.0, start});
        while(!q.isEmpty()) {
            double[] p = q.poll();
            int destNode = (int) p[1];
            double dis = p[0];
            if(dest[destNode] != -1) continue; //之前已经找到到这个节点的最大概率了，新的概率值乘了小于1的系数，变得更小了
            dest[destNode] = dis; //填充到这个节点的最大概率
            if(destNode == end) return dis;
            if(mp.containsKey(destNode)) {
                for(Map.Entry<Integer, Double> road : mp.get(destNode).entrySet()) {
                    if(dest[road.getKey()] == -1) { //
                        q.add(new double[] {dis * road.getValue(), road.getKey()});
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 无堆优化的
     * @param n
     * @param edges
     * @param succProb
     * @param start
     * @param end
     * @return
     */
    public double maxProbabilityI(int n, int[][] edges, double[] succProb, int start, int end) {
        //狄杰斯特拉算法
        //step 1: 把edges改成临接表 <start,  List<<end, proc>>>,是无向图
        Map<Integer, Map<Integer, Double>> mp = new HashMap<>();
        for(int i = 0;  i < edges.length; i++) {
            if(!mp.containsKey(edges[i][0])) {
                mp.put(edges[i][0], new HashMap<>());
            }
            mp.get(edges[i][0]).put(edges[i][1], succProb[i]);
            if(!mp.containsKey(edges[i][1])) {
                mp.put(edges[i][1], new HashMap<>());
            }
            mp.get(edges[i][1]).put(edges[i][0], succProb[i]);
        }

        //dest数组，记录到节点i的prob
        double[] dest = new double[10001];
        Arrays.fill(dest, -1.0); //初始化为-1
        Queue<double[]> q = new LinkedList<>();
        q.add(new double[] {1.0, start});
        while(!q.isEmpty()) {
            double[] p = q.poll();
            int destNode = (int) p[1];
            double dis = p[0];
            if(dest[destNode] < dis) dest[destNode] = dis; //更新到destNode的最大概率
            if(mp.containsKey(destNode)) {
                for(Map.Entry<Integer, Double> road : mp.get(destNode).entrySet()) {
                    if(dest[road.getKey()] < dis * road.getValue()) { //找到更大概率的路径，入队
                        q.add(new double[] {dis * road.getValue(), road.getKey()});
                    }
                }
            }
        }
        return dest[end] == -1.0 ? 0 : dest[end];
    }
}
