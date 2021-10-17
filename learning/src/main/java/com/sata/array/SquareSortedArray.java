package com.sata.array;

/**
 * LC 977s: 双指针， O(n)
 */
public class SquareSortedArray {

    public int[] sortedSquares(int[] nums) {
        //双指针法
        int[] res = new int[nums.length];
        int i = 0;
        for(; i <= nums.length - 1; i++) {
            if(nums[i] >= 0) {
                break;
            }
        }
        int left = i - 1;
        int right = i;
        int index = 0;
        while(left >= 0 || right < nums.length) {
            if(left >= 0 && right < nums.length){
                if(nums[left] * nums[left] < nums[right] * nums[right]) {
                    res[index++] = nums[left] * nums[left];
                    left--;
                } else{
                    res[index++] = nums[right] * nums[right];
                    right++;
                }
            } else if(left >= 0) {
                res[index++] = nums[left] * nums[left];
                left--;
            } else if(right < nums.length) {
                res[index++] = nums[right] * nums[right];
                right++;
            }
        }
        return res;
    }
}
