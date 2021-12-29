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

    public int largestRectangleAreaII(int[] heights) {
        //单调栈解法:需要给heights最后一位补个0
        List<Integer> h = new ArrayList<>();
        for(int height : heights) {
            h.add(height);
        }
        h.add(0);
        Stack<Integer> s = new Stack<>();
        int n = h.size();
        int res = 0;
        int i = 0;
        while(i < n) {
            if(s.empty() || h.get(s.peek()) <= h.get(i)) {
                s.push(i);
                i++;
            }else{
                int tmp = s.pop();
                res = Math.max((s.empty()? i : (i-s.peek()-1)) * h.get(tmp), res);
            }
        }
        return res;
    }
}
