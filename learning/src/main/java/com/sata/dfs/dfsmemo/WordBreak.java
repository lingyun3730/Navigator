package com.sata.dfs.dfsmemo;
import java.util.Arrays;
import java.util.List;

/**
 * LC 139: dfs + memo
 * dp 完全背包问题
 */
public class WordBreak {
    /**
     * dfs + memo
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        //可以用记忆化搜索或者背包问题解决
        int[] memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        return dfs(s, 0, wordDict, memo);
    }
    public static boolean dfs(String s, int start, List<String> wordDict, int[] memo) {
        if(start >= s.length()){
            return true;
        }
        if(memo[start] != -1)  return memo[start] == 1;
        for(int i = start; i <= s.length(); i++) {
            String substr = s.substring(start, i);
            if(wordDict.contains(substr) && dfs(s, i , wordDict, memo)) {
                memo[start] = 1;
                return true;
            }
        }
        memo[start] = 0;
        return false;
    }

    /**
     * 完全背包解法：字符串本身是背包，其中可以分解出子串就是物品，物品重复的，但是必须在wordDict里，遍历顺序无所谓
     */
    public static boolean wordBreakII(String s, List<String> wordDict) {
        //dp 完全背包问题
        //dp[j]表示从0-j之间的字符串是否可以划分成若干个在wordDict中能找到的字符串。
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for(int  j = 0; j < s.length(); j++) { //遍历物品
            for(int i = 1; i <= s.length();  i++) { //遍历背包容量
                if(j < i) {
                    String str = s.substring(j, i);
                    if(wordDict.contains(str) && dp[j]) {
                        dp[i] = true;
                    }
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        List<String> wordDict = Arrays.asList("leet", "code");
        boolean res = wordBreak("leetcode", wordDict);
        System.out.println(res);
    }
}
