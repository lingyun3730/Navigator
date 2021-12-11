package com.sata.dp.gamedp;

import java.util.Arrays;

/**
 * LC 1406
 */
public class StoneGameIII {
    /**
     * DP 算法
     * @param s
     * @return
     */
    public String stoneGameIII(int[] s) {
        //我们定义一个dp数组，dp[i]代表，面对第i位到数组结尾间的数字，先手能拿到的最大分数.
        //面对i -> n-1的数字序列，dp[i]要想最大，那么其状态一定是从对手作为先手面对更少的数字区间[i+1, n-1],[i+2, n-1],[i+3, n-1]的
        //所能拿到的最小值，因为只有对手拿的少，自己才能拿的多。
        //dp[i] = sum[i, length-1]-min(dp[i+1],dp[i+2],dp[i+3])
        //https://leetcode.jp/leetcode-1406-stone-game-iii-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/
        int n = s.length;
        int[] dp = new int[n + 1]; //初始化为n + 1.
        dp[n-1] = s[n-1];
        int sum = s[n-1];
        for(int i = n-2; i >=0; i--) { //因为是从左向右拿，因此倒序遍历
            sum += s[i];
            int min = Integer.MAX_VALUE;
            //选出先手拿1个，2个，3个时，后手所能拿到的最少的数字
            for(int j = i+1; j <= i+3 && j <= n; j++) { //一定要包含n,dp[n] = 0,所以后三个一定是和0比较
                min = Math.min(min, dp[j]);
            }
            dp[i] = sum - min;
        }
        if(dp[0] > sum - dp[0]) return "Alice";
        else if (dp[0] < sum - dp[0]) return "Bob";
        else return "Tie";
    }

    //------------------- 以下这两种算法都是把dp[i]定义为先手面对i -> n-1，比后手多拿的部分 -------------
    /**
     * dfs + memo.  top -> down
     * @param stone
     * @return
     */
    public String stoneGameIII_(int[] stone) {
        int[] dp = new int[stone.length + 1]; //dp[i]表示面对i -> n-1，先手比后手多拿的
        Arrays.fill(dp, Integer.MIN_VALUE);
        int alice = find(stone, 0, dp);
        if(alice > 0)
            return "Alice";
        else if(alice < 0)
            return "Bob";
        else
            return "Tie";
    }

    public int find(int[] stones, int ptr, int[] dp){
        if(ptr == stones.length)
            return 0;
        if(dp[ptr] != Integer.MIN_VALUE)
            return dp[ptr];
        //先手拿了stones[ptr],后手面对[ptr+1, stones.length - 1]多拿的是find(stones, ptr + 1, dp)。
        int ans = stones[ptr] - find(stones, ptr + 1, dp);
        if(ptr + 1 < stones.length)
            ans = Math.max(ans, stones[ptr] + stones[ptr + 1] - find(stones, ptr + 2, dp));
        if(ptr + 2 < stones.length)
            ans = Math.max(ans, stones[ptr] + stones[ptr + 1] + stones[ptr + 2] - find(stones, ptr + 3, dp));
        return dp[ptr] = ans;
    }

    /**
     * DP solution.   bottom -> up
     * @param stone
     * @return
     */
    public String stoneGameIII__(int[] stone) {
        int[] dp = new int[stone.length + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[stone.length] = 0;
        for(int i = stone.length-1; i >= 0; i--) {
            dp[i] = stone[i] - dp[i + 1];
            if(i + 1 < stone.length) dp[i] = Math.max(dp[i], stone[i] + stone[i + 1] - dp[i + 2]);
            if(i + 2 < stone.length) dp[i] = Math.max(dp[i], stone[i] + stone[i + 1] + stone[i + 2] - dp[i + 3]);
        }
        if(dp[0] > 0)
            return "Alice";
        else if(dp[0] < 0)
            return "Bob";
        else
            return "Tie";
    }
}
