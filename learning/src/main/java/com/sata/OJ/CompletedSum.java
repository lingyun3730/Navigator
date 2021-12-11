package com.sata.OJ;

import java.util.Scanner;

public class CompletedSum { //提交的时候把类名改成Main
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        String s = in.nextLine();
        String num1 = in.nextLine();
        String num2 = in.nextLine();
        int base = Integer.valueOf(s);
        long number1 = Long.parseLong(num1, base);
        long number2 = Long.parseLong(num2, base);
        long res = number1 + number2;
        System.out.println(Long.toString(res, base));
    }
}
