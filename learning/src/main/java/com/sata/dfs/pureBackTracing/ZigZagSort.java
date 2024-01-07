package com.sata.dfs.pureBackTracing;

import java.util.ArrayList;
import java.util.List;

public class ZigZagSort {
    static long res = 0;
    private static void zigZagSort(int [] arr) {
        helperUp(arr, 0, new ArrayList<>());
    }

    private static void helperUp(int[] arr, int index, List<Integer> visited) {
        if(index == arr.length) {
            res += 1;
            return;
        }
        for(int i = 0; i < arr.length; i++) {
            if(! visited.contains(arr[i])) {
                if(visited.isEmpty() || (index % 2 == 1 && visited.get(visited.size() - 1) > arr[i])
                || (index % 2 == 0 && visited.get(visited.size() - 1) < arr[i])) {
                    visited.add(arr[i]);
                    helperUp(arr, index + 1, visited);
                    visited.remove(visited.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 6, 19, 80, 0};
        zigZagSort(arr);
        System.out.println(res * 2);
    }
}
