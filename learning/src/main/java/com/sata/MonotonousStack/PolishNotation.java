package com.sata.MonotonousStack;

import java.util.Stack;

public class PolishNotation {

    public static int evalRPN(String[] tokens) {
        //后缀表达式是一种常见的计算机运算表达式方式
        Stack<Integer> s = new Stack<>();
        for(String token : tokens) {
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int num1 = s.pop();
                int num2 = s.pop();
                if(token.equals("+")) {
                    s.push(num1 + num2);
                }else if(token.equals("-")) {
                    s.push(num2 - num1);
                }else if(token.equals("*")) {
                    s.push(num1 * num2);
                }else{
                    s.push(num2 / num1);
                }
            } else{
                s.push(Integer.parseInt(token));
            }
        }
        return s.pop();
    }


    public static void main(String[] args) {
//        int res = evalRPN(new String[] {"10","6","9","3","+","-11","*","/","*","17","+","5","+"});
//        System.out.println(res);
        int res1 = evalRPN(new String[] {"4","13","5","/","+"});
        System.out.println(res1);
    }
}
