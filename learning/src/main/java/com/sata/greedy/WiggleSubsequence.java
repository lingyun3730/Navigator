package com.sata.greedy;

/**
 * LC 376
 */
public class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        //可以使用贪心，统计峰值的个数就好了
        int n = nums.length;
        int curDiff = 0;
        int preDiff = 0;
        int res = 1; //default the right one should be cnt.
        for(int i = 1; i < n; i++) {
            curDiff = nums[i] - nums[i-1];
            if((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                preDiff = curDiff;
                res ++;
            }
        }
        return res;
    }

    public int wiggleMaxLengthI(int[] nums) {
        //dp 解法
        //up[i]表示以nums[i]结尾的序列以上升沿结尾的最大摆动序列
        //down[i]表示以nums[i]结尾的序列以下降沿结尾的最大摆动序列
        //因为有可能一个元素是处于上升沿，也可能处于下降沿，因此结果取的是max(up[nums.length - 1], down[nums.length - 1])
        int n = nums.length;
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = 1;
        down[0] = 1;
        for(int i = 1; i < n; i++) {
            if(nums[i] > nums[i-1]) {
                down[i] = up[i-1] + 1;
                up[i] = up[i-1];
            }else if(nums[i] < nums[i-1]) {
                up[i] = down[i-1] + 1;
                down[i] = down[i-1];
            }else{
                up[i] = up[i-1];
                down[i] = down[i-1];
            }
        }
        return Math.max(up[n-1], down[n-1]);
    }

    public int wiggleMaxLengthII(int[] nums) {
        //dp 解法 简化
        int n = nums.length;
        int up = 1, down = 1;
        for(int i = 1; i < n; i++) {
            if(nums[i] > nums[i-1]) {
                down = up + 1;
            }else if(nums[i] < nums[i-1]) {
                up = down + 1;
            }
        }
        return Math.max(up, down);
    }
}
