package com.sata.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.lintcode.com/problem/156/description?utm_source=sc-libao-mwy
 * time: O(nlogn) -- sort time
 * space: O(logn) -- sort space, the res space is not counted in. Merge sort.
 */
public class MergeInterval {
    public class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        // sort the interval list
        intervals.sort((o1, o2) -> {
            if (o1.start == o2.start) return o1.end - o2.end;
            return o1.start - o2.start;
        });
        List<Interval> res = new ArrayList<>();
        for(int i = 0; i < intervals.size(); i++) {
            int left = intervals.get(i).start;
            int right = intervals.get(i).end;
            if(res.size() == 0 || res.get(res.size() - 1).end < left) {
                res.add(new Interval(left, right));
            } else {
                res.get(res.size() - 1).end = Math.max(res.get(res.size() - 1).end, right);
            }
        }
        return res;
    }
}
