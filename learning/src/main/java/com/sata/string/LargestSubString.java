package com.sata.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Lintcode 1648
 */
public class LargestSubString {
    /**
     * @param s: the matrix
     * @return: the last substring of s in lexicographical order
     */
    public String maxSubstring(String s) {
        // Write your code here.
        //一定是找到最大的字母，然后到最后，但是最大的字母会有多个，这个时候就需要继续比较
        List<Integer> allPos = new ArrayList<>();
        char maxChar = 'a';
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) >= maxChar) {
                if(s.charAt(i) > maxChar) {
                    maxChar = s.charAt(i);
                    allPos.clear();
                }
                allPos.add(i);
            }
        }
        if(allPos.size() == 1) {
            return s.substring(allPos.get(0));
        }
        int prePos = allPos.get(0);
        for(int i = 1; i < allPos.size(); i++) {
            int curPos = allPos.get(i);
            for(int j = 0; j < s.length() - curPos; j++) {
                if(s.charAt(prePos + j) > s.charAt(curPos + j)) {
                    break; // prePos开始的比curPos开始的子串大
                }
                if(s.charAt(prePos + j) < s.charAt(curPos + j)) {
                    prePos = curPos;
                    break;
                }
            }
        }
        return s.substring(prePos);
    }
}
