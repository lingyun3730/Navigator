package com.sata.greedy;

import java.util.Arrays;

/**
 * LC 1005
 */
public class MaxKNegatives {

    public static int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int i = 0;
        for(; i < n; i++) {
            if(nums[i] < 0 && k > 0) {
                k--;
                nums[i] = -nums[i];
            }else if(nums[i] >= 0) break;
            if(k <= 0) break;
        }
        int minJ = 0;
        for(int j = 0; j < n; j++) {
            if(nums[j] < nums[minJ]) minJ = j;
        }
        while(k > 0) {
            nums[minJ] = -nums[minJ];
            k--;
        }
        int sum = 0;
        for(int j = 0; j < n; j++) {
            sum += nums[j];
        }
        return sum;
    }
    public static void main(String[] args) {
        int[] nums = {-8,3,-5,-3,-5,-2};
        int res = largestSumAfterKNegations(nums, 6);
        System.out.println(res);
    }

}
