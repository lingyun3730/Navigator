package com.sata.contest;

import java.util.Stack;

public class ConstructSmallestNumber {
    public static String smallestNumber(String pattern) {
        Stack<Integer> s = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int curIndex = 1;
        for(int i = 0; i < pattern.length(); i++) {
            if(pattern.charAt(i) == 'I') {
                if(s.isEmpty()) {
                    sb.append(curIndex);
                    curIndex ++;
                } else {
                    sb.append(curIndex);
                    while(!s.isEmpty()) {
                        sb.append(s.pop());
                    }
                    curIndex ++;
                }
            } else {
                s.push(curIndex);
                curIndex ++;
            }
        }
        sb.append(curIndex);
        while(!s.isEmpty()) {
            sb.append(s.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String res = smallestNumber("DIDI");
        System.out.println(res);
    }
}
