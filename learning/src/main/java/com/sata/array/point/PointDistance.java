package com.sata.array.point;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PointDistance {
    public static int numberOfBoomerangs(int[][] points) {
        // Write your code here
        int res = 0;
        for(int i = 0; i < points.length; i++) {
            Map<Double, Integer> disMap = new HashMap<>();
            for(int j = 0; j < points.length; j++) {
                if(j == i) continue;
                double dis = distance(points[i], points[j]);
                disMap.putIfAbsent(dis, 0);
                disMap.put(dis, disMap.get(dis) + 1);
            }
            for(Map.Entry<Double, Integer> entry : disMap.entrySet()) {
                if(entry.getValue() > 1) {
                    res += entry.getValue() * (entry.getValue() - 1);
                }
            }
        }
        return res;
    }

    private static double distance(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

    public static void main(String[] args) {
        int[][] input = new int[][] {{0, 0}, {1, 0}, {2, 0}};
        int res = numberOfBoomerangs(input);
        System.out.println(res);
    }

}
