package com.sata.string;

/**
 * LC 151: intuitive solution without any String method
 */
public class RerverseString {

    public static String reverseWords(String s) {
        //step1: delete excessive space
        StringBuilder sb = deleteExcessiveSpace(s);
        //step2: reverse the whole string
        reverseString(sb, 0, sb.length() - 1);
        //step3: reverse each word
        reverseEachWord(sb);
        return sb.toString();
    }

    private static  StringBuilder deleteExcessiveSpace(String s) {
        int start = 0;
        int end = s.length() - 1;

        //去掉两头的
        while(s.charAt(start) == ' '){
            start++;
        }
        while(s.charAt(end) == ' '){
            end--;
        }
        //去掉中间的
        StringBuilder sb = new StringBuilder();
        while(start <= end) {
            char c = s.charAt(start);
            if(c == ' ' && sb.charAt(sb.length() - 1) == ' '){ // keep only one space internal String
                start ++;
                continue;
            }
            sb.append(c);
            start ++;
        }
        return sb;
    }

    //这个简单
    private static void reverseString(StringBuilder sb, int start, int end) {
        while(start < end) {
            char tmp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, tmp);
            start ++;
            end --;
        }
    }

    private static void reverseEachWord(StringBuilder s) {
        int start = 0, end = 0;
        int n = s.length();
        while(start < n) {
            while(end < n && s.charAt(end) != ' ') {
                end ++;
            }
            reverseString(s, start, end-1);
            start = end + 1;
            end = start;
        }
    }

    public static void main(String[] args) {
        String s = reverseWords("the  sky is blue ");
        System.out.println(s);
    }

}
