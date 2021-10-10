package com.sata.MonotonousStack;

import java.util.Arrays;
import java.util.Stack;

/**
 * LC 503 ： 数组成了环，循环遍历两遍， 下标 mod n
 */
public class NextGreaterElement {
    public int[] nextGreaterElements(int[] nums) {
        //将遍历两次这个数组nums
        int n = nums.length;
        int [] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < 2 * n; i++) {
            while(!s.empty() && nums[s.peek()] < nums[i % n]) {
                int tmp = s.pop();
                res[tmp%n] = nums[i%n];
            }
            s.push(i%n);
        }
        return res;
    }
}
