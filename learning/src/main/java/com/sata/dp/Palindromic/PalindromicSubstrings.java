package com.sata.dp.Palindromic;

/**
 * LC 647 回文串问题，还是两种遍历方式，沿着对角线和从下往上
 */
public class PalindromicSubstrings {
    //dp
    public int countSubstrings(String s) {
        //注意这个题是sub string不是sub array
        //dp[i][j] 表示 i-j之间是否构成回文串
        //dp[i][j] = true (if(s[i] == s[j] && j - i <= 1))
        //dp[i][j] = true (if(s[i] == s[j] && dp[i+1][j-1]))
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int res = 0;
        for(int i = n-1; i >=0; i--) {
            for(int j = i; j <n; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    if(j - i <= 1) {
                        res ++;
                        dp[i][j] = true;
                    }else if(dp[i+1][j-1]) {
                        res ++;
                        dp[i][j] = true;
                    }
                }
            }
        }
        return res;
    }

    //双指针法
    int res = 0;
    public int countSubstringsII(String s) {
        //也可用双指针法
        for(int i = 0; i < s.length(); i++) {
            helper(s, i, i); //中心只有一个字符
            helper(s, i, i+1); //中心有两个字符
        }
        return res;
    }
    private void helper(String s, int i, int j) {
        while(i >=0 && j < s.length() && s.charAt(i) == s.charAt(j))  {
            res ++;
            i --;
            j ++;
        }
    }
}
