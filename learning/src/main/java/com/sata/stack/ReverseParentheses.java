package com.sata.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReverseParentheses {
    public static String reverseParentheses(String s) {
        Stack<Character> st=  new Stack<Character>();
        Queue<Character> q = new LinkedList<>();
        for(char c : s.toCharArray()) {
            if (c != ')') {
                st.push(c);
            } else {
                while(!st.empty() && st.peek() != '(') {
                    q.add(st.pop());
                }
                if(!st.empty() && st.peek() == '(') st.pop(); // '(' pop
                while(! q.isEmpty()) {
                    st.push(q.poll());
                }
            }
        }
        StringBuilder res = new StringBuilder();
        while(!st.empty() && st.peek() != '(') {
            res.insert(0, st.pop());
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String res = reverseParentheses("(ed(et(oc))el)");
        System.out.println(res);
    }
}
