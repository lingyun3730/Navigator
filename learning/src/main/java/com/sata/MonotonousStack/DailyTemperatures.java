package com.sata.MonotonousStack;

import java.util.Stack;

/**
 * LC 739
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        //单调栈，保持栈是单调增长的。
        Stack<Integer> s = new Stack<>(); //store i
        int[] res = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            while(! s.empty() && temperatures[s.peek()] < temperatures[i]) {
                int tmp = s.pop();
                res[tmp] = i - tmp;

            }
            s.push(i);
        }
        return res;
    }
}
