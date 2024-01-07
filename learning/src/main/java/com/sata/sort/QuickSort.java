package com.sata.sort;

import java.util.*;

public class QuickSort {
    //快速排序，左右指针交换法
    public static void quickSort(int[] arr, int left, int right) {
        if (left > right) return;
        int pos = partition(arr, left, right);
        quickSort(arr, left, pos - 1);
        quickSort(arr, pos + 1, right);
    }

    public static int partition(int[] arr, int left, int right) {
        int tmp = arr[right];
        int i = left - 1; //i始终指向最后一个小于等于tmp的元素的下标
        int j;
        for(j = left; j < right; j++) {
            if(arr[j] <= tmp) {
                i++;
                swap(arr, i, j);
            }
        }
        arr[right] = arr[i+1]; //最后一个元素成为中间元素
        arr[i + 1] = tmp;
        return i + 1;
    }

    private static void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {4,2,6,7,1,9,0};
        int[] copy = Arrays.copyOf(arr, arr.length);
        quickSort(arr, 0, 6);
        Arrays.stream(arr).boxed().mapToInt(Integer::intValue).forEach(System.out::println);
        List<Integer> copyList = new ArrayList<>();
        for(int x : copy) {
            copyList.add(x);
        }
        copyList.sort(Comparator.comparingInt(o -> o));
        copyList.forEach(System.out::println);
    }
}
