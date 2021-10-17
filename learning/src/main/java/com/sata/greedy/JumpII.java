package com.sata.greedy;

/**
 * LC 45: 当下标移动到当前所能找到的最大覆盖边界时，而且还没有到达终点，就需要
 * 增加一步, 维护两个变量，一个是当前所能覆盖的最大范围的下标边界，另一个是下一步所能覆盖的最大范围的
 * 下标边界
 */
public class JumpII {

    public int jump(int[] nums) {
        //所有的题目都要有套路和思路，记住思路和难点
        //当下标移动到当前所能找到的最大覆盖边界时，而且还没有到达终点，就需要
        //增加一步
        //维护两个变量，一个是当前所能覆盖的最大范围的下标边界，另一个是下一步所能覆盖的最大范围的
        //下标边界
        int curDistance = 0;
        int nextDistance = 0;
        int n = nums.length;
        int res = 0;
        for(int i = 0; i < n; i++) {
            nextDistance = Math.max(nextDistance, i + nums[i]);
            if(curDistance == i)  {
                if(i != n-1) {
                    res++;
                    curDistance = nextDistance;
                    if(nextDistance >= n-1) return res;
                }else{
                    break;
                }
            }
        }
        return res;
    }
}
