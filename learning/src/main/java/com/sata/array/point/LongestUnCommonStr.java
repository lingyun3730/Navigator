package com.sata.array.point;

import java.util.Arrays;
import java.util.Comparator;

public class LongestUnCommonStr {
    public int findLUSlength(String[] strs) {
        // write your code here
        // sort + check each sequence
        Arrays.sort(strs, (o1, o2) -> o2.length() - o1.length()); //从长到短排列，找到最长的非公共子序列
        for(int i = 0; i < strs.length; i++) {
            boolean tag = true;
            for(int j = 0; j < strs.length; j++) {
                if(j == i) continue;
                if(isSubsequence(strs[i], strs[j])) { //strs[i] is a sub seq of strs[j]
                    tag = false;
                    break;
                }
            }
            if(tag) return strs[i].length(); //strs[i]不是任何其他一个字符串的子序列
        }
        return -1;
    }
    //to judge if x is a substr of y.
    public boolean isSubsequence(String x, String y) {
        int j = 0;
        for (int i = 0; i < y.length() && j < x.length(); i++)
            if (x.charAt(j) == y.charAt(i))
                j++;
        return j == x.length();
    }
}
