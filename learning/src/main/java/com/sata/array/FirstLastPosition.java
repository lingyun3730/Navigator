package com.sata.array;

import java.util.Arrays;

/**
 * LC 34
 */
public class FirstLastPosition {
    public int[] searchRange(int[] nums, int target) {
        //二分法
        int[] res = new int[2];
        Arrays.fill(res, -1);
        int start = 0;
        int end = nums.length - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] < target) {
                start = mid + 1;
            }else if(nums[mid] > target){
                end = mid - 1;
            }else { //nums[mid] == target
                int first = mid;
                int second = mid;
                while(first >= 0 && nums[first] == nums[mid]) {
                    first--;
                }
                while(second <= nums.length - 1 && nums[second] == nums[mid]) {
                    second++;
                }
                res[0] = first + 1;
                res[1] = second - 1;
                break;
            }
        }
        return res;
    }

}
