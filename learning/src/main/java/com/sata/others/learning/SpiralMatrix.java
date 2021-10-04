package com.sata.others.learning;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List<Integer> res = spiralMatrixTraverse(matrix);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    private static List<Integer> spiralMatrixTraverse(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) return res;
        int n = matrix[0].length;
        int left = 0, right = n - 1, top = 0, bottom = m - 1;
        while (left <= right && top <= bottom) {
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][left]);
            }
            for (int i = left + 1; i <= right; i++) {
                res.add(matrix[bottom][i]);
            }
            for (int i = bottom - 1; i >= top && left < right; i--) {
                res.add(matrix[i][right]);
            }
            for (int i = right - 1; i > left && top < bottom; i--) {
                res.add(matrix[top][i]);
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return res;
    }
}
