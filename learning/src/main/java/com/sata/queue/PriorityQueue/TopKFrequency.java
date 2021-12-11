package com.sata.queue.PriorityQueue;

import java.util.*;

/**
 * LC 692
 */
public class TopKFrequency {
    //思路：priority queue + hashmap
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> mp = new HashMap<>();
        for(String word : words) {
            mp.put(word, mp.getOrDefault(word, 0) + 1);
        }
        //有两种方式
        //小顶堆, 字典序大的在上面，value小的在上面
        //大顶堆， 字典序小的在上面，value大的在上面，插入结束之后，最大的value在堆顶
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue()? b.getKey().compareTo(a.getKey()): a.getValue() - b.getValue());
        for(Map.Entry<String, Integer> entry : mp.entrySet()) {
            pq.add(entry);
            if(pq.size() > k) { //堆超过k个元素，堆顶的小元素出堆，最后剩下k个频率最大的元素，按顺序出堆是从小到大的顺序
                pq.poll();
            }
        }
        for(int i = 0; i < k; i++) {
            res.add(0, pq.poll().getKey());
        }
        return res;
    }
}
