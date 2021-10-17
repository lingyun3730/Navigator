package com.sata.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 15 ： 双指针，主要难点在于去重逻辑的实现
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        //双指针法
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        if(nums.length < 3) return res;
        for(int index = 0; index < nums.length - 2; index ++) {
            if(index > 0 && nums[index] == nums[index - 1]) continue; //最外层去重逻辑
            int i = index + 1;
            int j = nums.length -1;
            if(nums[index] + nums[j] + nums[j-1] < 0) continue; //当前循环的最大组合小于0， 这一趟后面不会有答案了
            if(nums[index] + nums[i] + nums[i+1] > 0) break; //当前最小的数字组合都大于0，后面无论如何也不会有答案了
            while(i < j) {
                if(nums[index] + nums[i] + nums[j] == 0) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[index]);
                    tmp.add(nums[i]);
                    tmp.add(nums[j]);
                    res.add(tmp);
                    //下一个数字和当前数字相等，去重
                    while(i < j && nums[i] == nums[i+1]) i++;
                    while(i < j && nums[j] == nums[j-1]) j--;
                    i++;
                    j--;
                }else if(nums[index] + nums[i] + nums[j] < 0) {
                    i++;
                }else{
                    j--;
                }
            }
        }
        return res;
    }
}
