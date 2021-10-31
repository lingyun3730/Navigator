package com.sata.array.binarySearch;

/**
 * LC 922, 排序使得奇数位为奇数，偶数位为偶数
 */
public class SortArrayByParity {
    public int[] sortArrayByParityII(int[] nums) {
        //思路： 对于每一个偶数位，如果出现了奇数， 那么寻找最近的第一个奇数位是偶数的数字进行交换就行了。
        //时间复杂度 O(n),空间复杂度O(1)
        int oddIndex = 1; //奇数位
        for(int i = 0; i < nums.length; i += 2) {
            if(nums[i] % 2 == 1) {
                while(nums[oddIndex] % 2 != 0) {
                    oddIndex += 2;
                }
                if(oddIndex < nums.length) {
                    swap(nums, i, oddIndex);
                }
            }
        }
        return nums;
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
