package com.sata.string;

import java.util.Stack;

/**
 * LC 394 recursive solution.
 */
public class DecodingString {

    static int i = 0; //global
    public static String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while(i < s.length()) {
            char c = s.charAt(i);
            i++;
            if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                sb.append(c);
            }else if (c >= '0' && c <= '9') {
                count = count * 10  + (c - '0'); //对于数字的处理
            }else if(c == ']') break;
            else if(c == '[') {
                String repeated = decodeString(s);
                while(count > 0) {
                    sb.append(repeated);
                    count--;
                }
            }
        }
        return sb.toString();
    }
    public static String decodeStringI(String s) {
        //迭代法
        Stack<Integer> snum = new Stack<>();//number
        Stack<String> sstr = new Stack<>();//substring
        int number = 0;
        String t = "";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if((c >= 'A' && c <='Z') ||(c >='a' && c <= 'z')) {
                t += c;
            }else if(c >='0' && c <='9') {
                number = number * 10 + c - '0';
            }else if(c == '[') {
                snum.push(number);
                sstr.push(t);
                number = 0;
                t="";
            }else{ //]
                int repeat = snum.pop();
                String cur = sstr.pop();
                while(repeat > 0) {
                    cur += t;
                    repeat --;
                }
                t = cur;
            }
        }
        return t;
    }

    public static void main(String[] args) {
        String res = decodeStringI("abc3[a]");
        System.out.println(res);
    }

}
