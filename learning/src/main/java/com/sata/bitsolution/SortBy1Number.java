package com.sata.bitsolution;

import java.util.*;

/**
 * LC 1356
 */
public class SortBy1Number {
    public int[] sortByBits(int[] arr) {
        //学习这个sort方法
        return Arrays.stream(arr).boxed().sorted((a, b) -> {
            int val = cntOne(a) - cntOne(b);
            return val == 0? a - b : val;
        }).mapToInt(Integer::intValue).toArray();
    }
    private int cntOne(int copy) {
        int count = 0;
        while(copy != 0)  {
            if((copy & 1) == 1) {
                count ++;
            }
            copy = copy >> 1;
        }
        return count;
    }
}
