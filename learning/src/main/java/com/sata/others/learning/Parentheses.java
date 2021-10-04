package com.sata.others.learning;

import java.util.*;
import java.util.stream.Collectors;

public class Parentheses {
    public static List<String> removeInvalidParentheses(String s) {
        Set<String> res = new HashSet<>();
        String tmp = "";
        dfs(s, 0, tmp, res);
        int maxLen = 0;
        for(String str : res) {
            if(str.length() > maxLen) {
                maxLen = str.length();
            }
        }
        final int t = maxLen;
        List<String> ret = res.stream().filter(str -> {return str.length() == t;}).collect(Collectors.toList());
        if(ret.size() == 0) {
            ret.add("");
        }
        return ret;
    }
    private static void dfs(String s, int i, String tmp,  Set<String> res) {
        if(i == s.length()) {
            if(isValid(tmp)){
                res.add(tmp);
            }
            return;
        }
        tmp = tmp + s.charAt(i);
        dfs(s, i+1, tmp, res);
        if(s.charAt(i) == '(' || s.charAt(i) == ')') {
            tmp = tmp.substring(0, tmp.length()-1);
            dfs(s, i+1, tmp, res);
        }
    }
    private static boolean isValid(String tmp) {
        Stack<Character> s = new Stack<Character>();
        for(char c : tmp.toCharArray()) {
            if(c == '('){
                s.push(c);
            }else if(c == ')'){
                if(!s.isEmpty()){
                    s.pop();
                }else{
                    return false;
                }
            }else{
                continue;
            }
        }
        return s.isEmpty();
    }

    public static void main(String[] args) {
        List<String> res = removeInvalidParentheses(")(f");
        res.forEach(System.out::println);
    }
}
