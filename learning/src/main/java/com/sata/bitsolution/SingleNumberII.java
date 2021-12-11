package com.sata.bitsolution;

/**
 * LC 137
 */
public class SingleNumberII {
    /**
     * way 1
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        //设置一个位运算数组
        int res = 0;
        for(int i = 0; i < 32; i++) {
            int count = 0;
            for(int num : nums){
                if( ((num >> i) & 1) == 1) count ++;
            }
            res |= (count % 3) << i;
        }
        return res;
    }
}
