package com.sata.sort;

public class HeapSort {
    private void heapAdjust(int[] nums, int start, int len) {
        int left = start * 2 + 1;
        int right = start * 2 + 2;
        int largest = start;
        if(left < len && nums[left] > nums[largest]) {
            largest = left;
        }
        if(right < len && nums[right] > nums[largest]) {
            largest = right;
        }
        if(largest != start) {
            swap(nums, largest, start);
            heapAdjust(nums, largest, len);
        }
    }

    private void constructHeap(int[] nums, int len) {
        for(int i = len / 2 - 1; i >= 0; i--) {
            heapAdjust(nums, i, len);
        }
    }

    public int[] sortArray(int[] nums) {
        //堆排序
        int len = nums.length;
        if(len == 0) return nums;
        constructHeap(nums, len);
        //堆顶元素和数组的最后一个元素进行交换
        for(int i = nums.length - 1; i >= 0; i--) {
            swap(nums, 0, i);
            len --;
            heapAdjust(nums, 0, len);//交换之后，对依然还没有序的部分进行堆调整。
        }
        return nums;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
