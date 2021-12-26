package com.sata.array.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18 :双指针，主要难点还是在去重和加速
 */
public class FourSum {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        if(n < 4) return res;
        for(int i = 0; i < n - 3; i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            if(nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target) break;
            if(nums[i] + nums[n-1] + nums[n-2] + nums[n-3] < target) continue;
            for(int j = i+1; j < n-2; j++){
                if(j>i+1 && nums[j] == nums[j-1]) continue;
                if(nums[i] + nums[j] + nums[j+1] + nums[j+2] > target) break;
                if(nums[i] + nums[j] + nums[n-1] + nums[n-2]  < target) continue;
                int left = j+1;
                int right = n-1;
                while(left < right) {
                    if(nums[i] + nums[j] + nums[left] + nums[right] < target) left ++;
                    else if(nums[i] + nums[j] + nums[left] + nums[right] > target) right --;
                    else{
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[left]);
                        tmp.add(nums[right]);
                        res.add(tmp);
                        while(left < right && nums[left] == nums[left+1]) left ++;
                        while(left < right && nums[right] == nums[right-1]) right --;
                        left ++;
                        right --;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //这个case会整型溢出
        int[] nums = {0,0,0,1000000000,1000000000,1000000000,1000000000};
        List<List<Integer>> res = fourSum(nums, 1000000000);

        int sum = 1000000000 + 1000000000 + 1000000000;
        System.out.println(sum);
    }

}
