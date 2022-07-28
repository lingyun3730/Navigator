package com.sata.stack;

import java.util.Stack;

public class BasicCalculation {
    /**
     * @param s: the expression string
     * @return: the answer
     */
    public static int calculate(String s) {
        // Write your code here
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> nums = new Stack<>();   //数字栈
        Stack<Character> ops = new Stack<>();   //操作符栈
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {            //字符串转化数字
                num = c - '0';
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                nums.push(num);            //数字直接存入栈中
                num = 0;
            } else if (c == '(') {        //左括号直接存入
                ops.push(c);
            } else if (c == ')') {        //遇到右括号
                while (ops.peek() != '(') {        //对栈顶两数字进行运算
                    nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {   //遇到操作符
                while (!ops.isEmpty() && precedence(c, ops.peek())) {        //对栈顶两数字进行运算
                    nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {    //取出栈顶的数字进行操作
            nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
        }
        return nums.pop();
    }

    private static int operation(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;   //加法
            case '-':
                return a - b;      //减法
            case '*':
                return a * b;   //乘法
            case '/':
                return a / b;   //除法
        }
        return 0;
    }

    private static boolean precedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    //Way 2
    /**
     * 把所有的计算当成加法，减法当成加一个负数
     * 遇到乘除法将其结果算出来作为一个被加数
     * 遇到括号递归地算出结果来，同样作为被加数
     * @param s
     * @return
     */
    public static int calculateI(String s) {
        //replace " " with ""
        s = s.replace(" ", "");
        if (s.length() == 0) return 0;
        Stack<Integer> st = new Stack<>();
        int i = 0;
        int num = 0;
        char sign = '+'; //默认第一个符号是 +
        while(i < s.length()) {
            char c = s.charAt(i);
            if(c >= '0' && c <= '9') {
                num = num * 10  + c - '0';
            }
            if(c == '(') { //左括号找右括号，遇到右括号不做任何事情
                int j = findClosing(s.substring(i)); //找到对应的右括号的位置
                num = calculateI(s.substring(i + 1, i + j)); //去除括号，并且递归地计算出括号内的数值
                i += j;
            }
            if(i == s.length() - 1 || ! Character.isDigit(c)) { //到表达式结尾或者遇到下一个符号
                switch(sign) {
                    case '+':
                        st.push(num);
                        break;
                    case '-':
                        st.push(-num);
                        break;
                    case '*':
                        st.push(st.pop() * num);
                        break;
                    case '/':
                        st.push(st.pop() / num);
                        break;
                }
                num = 0; //当前操作数归零
                sign = c; //更新当前的符号
            }
            i++; //指针后移
        }
        int res = 0;
        while(! st.isEmpty()) {
            res += st.pop();
        }
        return res;
    }

    private static int findClosing(String s) {
        int level = 0;
        int i = 0;
        for(; i < s.length(); i++) {
            if(s.charAt(i) == '(') level ++;
            else if(s.charAt(i) == ')'){
                level--;
                if(level == 0) break;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int res = calculateI("3 + 3 * (6 - 20 / (7-2) + 1)");
        System.out.println(res);
    }
}
