package com.sata.bitsolution;

import java.util.Arrays;

/**
 * LC 260
 */
public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int x = 0;
        for(int num : nums) {
            x ^= num;
        }
        int mask = 1;
        while((mask & x) == 0) mask=mask<<1; //mask位置为1的位 区分两个不同的数字
        int[] res = new int[2];
        Arrays.fill(res, 0);
        for(int num : nums) {
            if((mask & num) >= 1) {
                res[0] ^= num;
            }
            else res[1] ^= num;
        }
        return res;
    }
}
