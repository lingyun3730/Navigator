package com.sata.dp.rectangle;

/**
 * LC 42
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        //双指针 - 动态规划都可以做这个题
        //双指针 time complexity O(n ^2) 空间复杂度 O(1)
        //动态规划以空间换时间，time complexity O(n) 空间复杂度 O(n)
        int n = height.length;
        int[] lh = new int[n]; //存储i左边第一个比height[i]小的矩形高度
        int[] rh = new int[n]; //存储i右边第一个比height[i]小的矩形高度
        lh[0] = height[0]; //初始化
        rh[n-1] = height[n-1];
        for(int i = 1; i < n; i++) {
            lh[i] = Math.max(lh[i-1], height[i]);
        }
        for(int j = n-2; j >= 0; j--) {
            rh[j] = Math.max(rh[j + 1], height[j]);
        }
        int sum = 0;
        for(int i = 1; i <= n-2; i++) {
            sum += Math.min(lh[i], rh[i]) - height[i];
        }
        return sum;
    }
}
