package com.sata.greedy;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * LC 406
 */
public class QueueConstruction {

    public int[][] reconstructQueue(int[][] people) {
        //先fix一个维度，然后比较另一维度
        //先按照身高从大到小排序，然后身高相同的，比较k， k小的在前面。
        Arrays.sort(people, (a, b) -> {
            if(a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        //使用linkedlist, 按照下标依次插入。
        LinkedList<int[]> que = new LinkedList<>();
        for(int[] p : people) {
            que.add(p[1], p); //在index为p[1]的位置上插入p
        }
        return que.toArray(new int[people.length][]);
    }
}
