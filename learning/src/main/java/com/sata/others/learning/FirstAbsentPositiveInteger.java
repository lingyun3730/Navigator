package com.sata.others.learning;

public class FirstAbsentPositiveInteger {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i=0; i<n; i++){
            if(nums[i] > 0 && nums[i] < n && nums[nums[i] - 1] != nums[i]) {
                int tmp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[nums[i] - 1] = tmp;
            }
        }
        for(int i=0; i<n; i++) {
            if(nums[i] != i+1) return i+1;
        }
        return n+1;
    }

    public static void main(String[] args) {
        FirstAbsentPositiveInteger firstAbsentPositiveInteger = new FirstAbsentPositiveInteger();
        int[] nums = {3,4,-1,1};
        int res =  firstAbsentPositiveInteger.firstMissingPositive(nums);
        System.out.println(res);
    }

}

