package com.sata.dfs.dfsmemo;

import java.util.Arrays;

/**
 * LC 132
 */
public class PalindromePartitioningII {
    /**
     * DFS + memo : top - down
     * @param s
     * @return
     */
    public int minCut(String s) {
        //dfs+memo top-down
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return dfs(s, 0, memo);
    }

    private int dfs(String s, int index, int[] memo) {
        if(index >= s.length()) {
            return -1; //这个地方需要注意一下， 返回-1
        }
        if(memo[index] != -1) {
            return memo[index];
        }
        int min = Integer.MAX_VALUE;
        for(int i = index; i < s.length(); i++) { //向后尝试
            if(isPalindrome(s, index, i)) {
                min = Math.min(min, dfs(s, i + 1, memo) + 1); //更新memo
            }
        }
        memo[index] = min; //更新memo
        return memo[index];
    }

    private boolean isPalindrome(String s, int start, int end) {
        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

    /**
     * DP bottom - up
     * @param s
     * @return
     */
    public int minCutII(String s) {
        //bottom - up
        int n = s.length();
        boolean[][] pal = new boolean[n][n];
        for(int i = n-1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    if(j - i <= 1) pal[i][j] = true;
                    else {
                        pal[i][j] = pal[i + 1][j - 1];
                    }
                }
            }
        }
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            dp[i] = i; //初始化
        }
        for(int i = 0; i < n; i++) {
            if(pal[0][i]) {
                dp[i] = 0;
                continue;
            }else{
                for(int j = 1; j <= i; j++) {
                    if(pal[j][i]) { //在j位置切一次
                        dp[i] = Math.min(dp[i], dp[j-1] + 1);
                    }
                }
            }
        }
        return dp[n-1];
    }

}
