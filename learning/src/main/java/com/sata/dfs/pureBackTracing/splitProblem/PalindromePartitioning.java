package com.sata.dfs.pureBackTracing.splitProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 131
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        helper(res, tmp, s, 0);
        return res;
    }

    private void helper(List<List<String>> res, List<String> tmp,
                        String s, int index) {
        if(index == s.length()) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for(int i = index; i < s.length(); i++) {
            String str = s.substring(index, i + 1);
            if(! isPalindrome(str)) continue;
            tmp.add(str);
            helper(res, tmp, s, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }

    private boolean isPalindrome(String str) {
        int start = 0;
        int end = str.length() - 1;
        while(start <= end) {
            if(str.charAt(start) != str.charAt(end)) return false;
            start ++;
            end --;
        }
        return true;
    }


}
