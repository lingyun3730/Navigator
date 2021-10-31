package com.sata.array.binarySearch;

/**
 * LC 35
 */
public class BinarySearch {
    //两种二分法写法
    //左闭右开
    public int searchInsert(int[] nums, int target) {
        //二分法
        int start = 0, end = nums.length;  //[start, end)
        while(start < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] > target) {
                end = mid; //开区间的那边end没被占用
            }else if(nums[mid] < target) {
                start = mid + 1;
            }else{
                return mid;
            }
        }
        //插入点在所有数字的左边 [0, 0)
        //插入点在数字中间[start, end)
        //插入点在所有数字的右边[start, end)
        return end; //因此插入的位置应该是end。
    }

    //左闭右闭
    public int searchInsertII(int[] nums, int target) {
        //二分法
        int start = 0, end = nums.length -1;  //[start, end]
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] > target) {
                end = mid - 1;
            }else if(nums[mid] < target) {
                start = mid + 1;
            }else{
                return mid;
            }
        }
        //插入点在所有数字之左[0, -1]; //退出时end < start
        //插入点在数字中间[start, end] //退出时end < start
        //插入点在数字区间之右[start, end]
        return end + 1; //因此插入的位置应该是end + 1。
    }
}
