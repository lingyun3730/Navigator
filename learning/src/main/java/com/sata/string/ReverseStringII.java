package com.sata.string;

/**
 * LC 541
 */
public class ReverseStringII {

    public static String reverseStr(String s, int k) {
        int n = s.length();
        int start = 0;
        int end = k-1;
        int confine = 2 * k - 1;
        StringBuilder sb = new StringBuilder(s);
        while(end < n) {
            reverseHelper(sb, start, end);
            start = confine + 1;
            end = start + k -1;
            confine = confine + 2 * k;
        }
        if(start < n && end >= n){
            //reverse all;
            reverseHelper(sb, start, n - 1);
        }
        return sb.toString();
    }

    private static void reverseHelper(StringBuilder sb, int start, int end) {
        while(start < end) {
            char tmp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, tmp);
            start ++;
            end --;
        }
    }

    public static void main(String[] args) {
        String res = reverseStr("krmyfshbspcgtesxnnljhfursyissjnsocgdhgfxubewllxzqhpasguvlrxtkgatzfybprfmmfithphckksnvjkcvnsqgsgosfxc"
                ,20);
        System.out.println(res);
    }
}
