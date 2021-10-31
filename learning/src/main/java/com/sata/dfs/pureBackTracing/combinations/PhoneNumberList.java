package com.sata.dfs.pureBackTracing.combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 17 要记住使用StringBuilder
 */
public class PhoneNumberList {
    String[] strs = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits == null || digits.length() == 0) return res;
        helper(res, digits, new StringBuilder(),  0);
        return res;
    }
    private void helper(List<String> res, String digits, StringBuilder tmp,  int indexInDigits) {
        if(indexInDigits == digits.length()) {
            res.add(tmp.toString());
            return;
        }
        int number = digits.charAt(indexInDigits) -'0';
        for(char c : strs[number].toCharArray()){
            tmp.append(c);
            helper(res, digits, tmp, indexInDigits + 1);
            tmp.deleteCharAt(tmp.length() - 1);
        }
    }
}
