package com.sata.dfs.basicquestions;

import java.util.ArrayList;
import java.util.List;

/**
 * 母题 2： 穷举 + 剪枝
 * Given N pairs of parentheses “()”, return a list with all the valid permutations.
 * Assumptions
 * N >= 0
 * Examples
 * N = 1, all valid permutations are ["()"]
 * N = 3, all valid permutations are ["((()))", "(()())", "(())()", "()(())", "()()()"]
 * N = 0, all valid permutations are [""]
 */
public class AllParentheses {

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(res, 0, 0, "", n);
        return res;
    }

    public static void helper(List<String> res, int left, int right, String tmp, int total) {
        if(left == total && right == total) {
            res.add(tmp);
            return;
        }
        if(left < total) { // left ( can be added case
            helper(res, left + 1, right, tmp + "(", total);
        }
        //restrictions: left added number so far > right added number so far
        if(left > right) { //right ) can be added case
            helper(res, left, right + 1, tmp + ")", total);
        }
    }

    public static void main(String[] args) {
        List<String> res = generateParenthesis(3);
        res.forEach(System.out :: println);
    }
}
