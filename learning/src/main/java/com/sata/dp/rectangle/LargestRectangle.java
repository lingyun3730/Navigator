package com.sata.dp.rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * LC 84 DP or 单调栈
 */
public class LargestRectangle {
    /**
     * DP
     */
    public int largestRectangleAreaI(int[] heights) {
        //动态规划：
        int n = heights.length;
        int[] lh = new int[n]; //lh[i]存的是i向左第一个比i位置高度小的柱子的下标
        int[] rh = new int[n]; //rh[i]存的是i向右第一个比i位置高度小的柱子的下标
        //这两个初始化很重要。
        lh[0] = -1; //防止越界
        rh[n-1] = n; //防止越界
        for(int i = 1; i < n; i++) {
            int tmp = i - 1; //初始化为i-1,往左边找
            while(tmp >= 0 && heights[tmp] >= heights[i]) tmp = lh[tmp]; //向左找， 注意不能 tmp--，否则会超时;
            lh[i] = tmp; //找到了！
        }
        for(int i = n-2; i >= 0; i--) {
            int tmp = i + 1;
            while(tmp <= n-1 && heights[tmp] >= heights[i]) tmp = rh[tmp]; //注意不能 tmp ++，否则会超时
            rh[i] = tmp;
        }
        //求面积
        int res = 0;
        for(int i = 0; i < n; i++) {
            int area = (rh[i] - lh[i] - 1)*heights[i];
            res = Math.max(res, area);
        }
        return res;
    }

    /**
     * 单调栈做法
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        //左右两边需要添加一个0，这样做是为了让每个柱子都有可能被遍历到
        int n = heights.length;
        int[] cpy = new int[n + 2];
        cpy[0] = 0;
        cpy[n + 1] = 0;
        for(int i = 0; i < n; i++) {
            cpy[i + 1] = heights[i];
        }
        Stack<Integer> st = new Stack<>();
        st.push(0);
        int res = 0;
        for(int i = 0; i < n + 2; i++) {
            if(cpy[i] >= cpy[st.peek()]) {
                st.push(i);
            }else{
                while(!st.isEmpty() && cpy[st.peek()] > cpy[i]) {
                    int mid = st.pop();
                    int left = st.peek();
                    int right = i;
                    int area = (right - left - 1) * cpy[mid];
                    res = Math.max(res, area);
                }
                st.push(i);
            }
        }
        return res;
    }
}
