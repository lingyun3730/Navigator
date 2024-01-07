package com.sata.contest;

import java.util.*;

public class NumbersWithRepeatedDigits {
    public static int numDupDigitsAtMostN(int n) {
        List<Integer> digits = new ArrayList<>();
        int cpy = n + 1;
        while(cpy != 0) {
            digits.add(cpy % 10);
            cpy = cpy / 10;
        }
        Collections.reverse(digits);
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        //for numbers with less length of digits.size().
        for(int i = 1;  i < digits.size(); i++) {
            res += 9 * A(9, i - 1);
        }

        //for numbers with length of digits.size().
        for(int i = 0; i < digits.size(); i++) {
            for(int j = i > 0? 0 : 1; j < digits.get(i); j++) {
                //到第i位不选择n的第i位作为可选数字的当前位，
                //这样就可以对于小于n的第i位的其它满足条件的数组，对i之后的位置上进行全排列。
                if(visited.contains(j)) continue;
                res += A(9 - i, digits.size() - i - 1);
            }
            if(visited.contains(digits.get(i))) break; //如果前缀中已经出现了重复数字，那么其实也不需要再继续下去算后续位置上的全排列了，结束。
            visited.add(digits.get(i)); // 选择n的第i位作为后续可选数字的前缀数字之一
        }
        return n - res;
    }

    private static int A(int m, int n) {
        if(n == 0) return 1;
        return A(m, n-1) * (m - n + 1);
    }

    public static void main(String[] args) {
        int res = numDupDigitsAtMostN(20);
        System.out.println(res);
    }
}
