package com.sata.array.binarySearch;

/**
 * 二维数组二分搜索，找到矩阵边界
 * https://www.lintcode.com/problem/600/description?utm_source=sc-%20libao-hwx
 */
public class PixelsBinarySearch {
    /**
     * @param image: a binary matrix with '0' and '1'
     * @param x: the location of one of the black pixels
     * @param y: the location of one of the black pixels
     * @return: an integer
     */
    public int minArea(char[][] image, int x, int y) {
        // write your code here
        //线性时间复杂度肯定会超时。
        //正确思路是找到矩阵的上下左右边界，然后计算面积
        if(image.length == 0) return 0;
        int left = findFirst(y, image, 1); // 1表示查找的是列
        int right = findLast(y, image, 1);
        int up = findFirst(x, image, 0); // 0表示查找的是行
        int bottom = findLast(x, image, 0);
        return (right - left) * (bottom - up);
    }

    //左边界是包含边界
    private int findFirst(int x, char[][] image, int type) {
        //int bound = type == 0? image.length : image[0].length;
        int l = 0;
        int r = x;
        while(l < r) {
            int mid = l + (r - l) / 2;
            if(checkSuccess(mid, image, type)) {
                r = mid;
            }else {
                l = mid + 1;
            }
        }
        return l;
    }

    //右边界是不包含的边界
    private int findLast(int x, char[][] image, int type) {
        int bound = type == 0? image.length : image[0].length;
        int l = x;
        int r = bound;
        while(l < r) {
            int mid = l + (r - l) / 2;
            if(checkSuccess(mid, image, type)) {
                l = mid + 1;
            }else {
                r = mid;
            }
        }
        return l;
    }

    //判断mid处投影是包含1的
    private boolean checkSuccess(int mid, char[][] image, int type) {
        int bound = type == 1? image.length : image[0].length;
        if(type == 0) { //行边界
            for(int i = 0; i < bound; i++) {
                if(image[mid][i] == '1') {
                    return true;
                }
            }
        } else {
            for(int i = 0; i < bound; i++) {
                if(image[i][mid] == '1') {
                    return true;
                }
            }
        }
        return false;
    }
}
