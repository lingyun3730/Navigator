package com.sata.string;

/**
 * https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 */
public class LeftRotateString {

    public String reverseLeftWords(String s, int n) {
        //不额外开空间
        int m = s.length();
        int k = n % m;
        StringBuilder sb = new StringBuilder(s);
        sb = reverseHelper(sb, 0, k-1);
        sb = reverseHelper(sb, k, m-1);
        return sb.reverse().toString();
    }
    private StringBuilder reverseHelper(StringBuilder sb, int start, int end) {
        while(start < end) {
            char tmp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, tmp);
            start ++;
            end --;
        }
        return sb;
    }

}
