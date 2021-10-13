package com.sata.PriorityQueue;

import java.util.*;

/**
 * LC 347 优先级队列的初始化方式
 */
public class TopKProblem {
    public int[] topKFrequent(int[] nums, int k) {
        //对频率构造一个小顶堆，留下频率最大的k个数
        Map<Integer, Integer> mp = new HashMap<>();
        for(int num : nums) {
            mp.putIfAbsent(num, 0);
            mp.put(num, mp.get(num) + 1);
        }
        int[] res = new int[k];
        //小顶堆的初始化方式
        Queue<Map.Entry<Integer, Integer>> p = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for(Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            p.add(entry);
            if(p.size() > k) {
                p.poll(); //只保留k个最大的，其他小的都poll掉
            }
        }
        for(int i = k-1; i >=0; i--) {
            res[i] = p.poll().getKey();
        }
        return res;
    }
}
