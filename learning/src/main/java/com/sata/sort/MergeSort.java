package com.sata.sort;

import java.util.Arrays;

public class MergeSort {
    //有返回值的写法
    public static int[] mergeSort(int[] nums, int start, int end) {
        if(start == end) return new int[]{nums[start]}; //start == end就退出了，因此是左闭右开
        int mid = start + (end - start) /2;
        int[] left = mergeSort(nums, start, mid);
        int[] right = mergeSort(nums, mid + 1, end);
        int[] res = new int[left.length + right.length];
        int index = 0, i = 0, j = 0;
        while(index < res.length && i < left.length && j < right.length) {
            res[index ++] = left[i] < right[j] ? left[i++] : right[j++];
        }
        while(i<left.length) {
            res[index ++] = left[i ++];
        }
        while(j < right.length) {
            res[index ++] = right[j ++];
        }
        return res;
    }

    //无返回值的写法
    public static void mergeSort2(int[] nums, int left, int right) {
        if(left >= right) return; //左闭右开的写法
        int[] copy = Arrays.copyOfRange(nums, 0, nums.length);
        int mid = left + (right - left) /2;
        mergeSort2(nums, left, mid);
        mergeSort2(nums, mid + 1, right);
        int p = left;
        int q = mid+1;
        int index = left;
        while(p <= mid && q <= right) {
            copy[index++] = nums[p] < nums[q]? nums[p++] : nums[q++];
        }
        while(p <= mid) copy[index++] = nums[p++];
        while(q <= right)copy[index++] = nums[q++];
        for(int i = left; i <= right; i++) {
            nums[i] = copy[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,4,1, 0, 9, 8};
        //int[] res = mergeSort(arr, 0, 5);
        mergeSort2(arr, 0, 5);
        Arrays.stream(arr).boxed().mapToInt(Integer::intValue).forEach(System.out::println);
    }
}
