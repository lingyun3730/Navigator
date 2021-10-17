package com.sata.greedy;

import java.util.Arrays;

/**
 * LC 135
 * 那么本题我采用了两次贪心的策略：
 *
 * 一次是从左到右遍历，只比较右边孩子评分比左边大的情况。
 * 一次是从右到左遍历，只比较左边孩子评分比右边大的情况。
 * 这样从局部最优推出了全局最优，即：相邻的孩子中，评分高的孩子获得更多的糖果。
 *
 */
public class Candy {

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for(int i = 1; i < n; i++) {
            if(ratings[i] > ratings[i-1]) candies[i] = candies[i-1] + 1;
        }
        for(int i = n-2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1]) candies[i] = Math.max(candies[i+1] + 1, candies[i]);
        }
        int res = 0;
        for(int i = 0; i < n; i++) {
            res += candies[i];
        }
        return res;
    }
}
