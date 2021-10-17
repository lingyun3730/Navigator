package com.sata.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * LC 56
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if(n == 0) return new int[0][0];
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        int lastIndex = 0; //记住结果list里的lastIndex
        for(int i = 1; i < n; i++) {
            if(res.get(lastIndex)[1] >= intervals[i][0]) {
                res.get(lastIndex)[1] = Math.max(res.get(lastIndex)[1], intervals[i][1]);
            }else {
                res.add(intervals[i]);
                lastIndex++;
            }
        }
        return res.toArray(new int[][]{});
    }
}
