package com.sata.dfs.pureBackTracing.splitProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 93
 */
public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if(s.length() < 4 || s.length() > 12) return res;
        helper(s, res, 0, 0);
        return res;
    }

    private void helper(String s, List<String> res, int startIndex, int currentNum) {
        if(currentNum == 3) { //递归结束条件
            if(isValid(s, startIndex, s.length() -1)) {
                res.add(s);
            }
            return;
        }
        //本层进行遍历
        for(int i = startIndex; i < s.length(); i++) {
            if(isValid(s, startIndex, i)) {
                StringBuilder sb = new StringBuilder(s);
                helper(sb.insert(i + 1, '.').toString(), res, i + 2, currentNum + 1); //插入小数点继续向下层遍历
            }else{
                break;
            }
        }
    }

    private boolean isValid(String s, int startIndex, int endIndex) {
        if(startIndex > endIndex) return false;
        //这个条件非常关键，这个不处理的话，这道题不能ac的，0是不合法的
        if(s.charAt(startIndex) == '0' && startIndex != endIndex) return false;
        String numStr = s.substring(startIndex, endIndex + 1);
        return Integer.parseInt(numStr) >= 0 && Integer.parseInt(numStr) <= 255;
    }
}
