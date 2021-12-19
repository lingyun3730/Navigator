package com.sata.dfs.dfsmemo.complete_backpack_dfs_memo;

import java.util.Arrays;

/**
 * LC 279
 */
public class PerfectSquares {
    public int numSquares(int n) {
        //back tracking
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        int res = dfs(0, memo, n);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    int dfs(int index, int[] memo, int n) {
        if(index == n){
            return 0;
        }
        if(index > n) return Integer.MAX_VALUE; //此路不通，标记为最大值
        if(memo[index] != -1) return memo[index];
        int res = Integer.MAX_VALUE;
        for(int i = 1; i * i <= n; i++) {
            int tmp = dfs(index + i * i, memo, n);
            if(tmp != Integer.MAX_VALUE) { //路通了才比较大小，求出结果
                res = Math.min(tmp + 1, res);
            }
        }
        memo[index] = res;
        return res;
    }
}
