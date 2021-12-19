package com.sata.OJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RedPacket {

    static int max = -1;
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        String s = in.nextLine();
        String[] input = s.split(" ");
        List<Integer> packets = new ArrayList<>();
        int x = 0;
        for(int i = 0 ; i < input.length ; i ++){
            packets.add(Integer.valueOf(input[i]));
            x += packets.get(i);
        }
        //System.out.println(x);
        List<Integer> subSet = new ArrayList<>();
        helper(packets, 0, subSet);
        System.out.println(max);
    }

    private static int sum(List<Integer> subSet) {
        int s = 0;
        for(int val : subSet) {
            s += val;
        }
        return s;
    }

    //求子集问题
    private static void helper(List<Integer> packets, int index, List<Integer> subSet) {
        //处理子集
        int total = sum(subSet);
        if(partition(subSet, total)) {
            max = Math.max(total / 3, max);
        }
        if(index >= packets.size()) {
            return;
        }
        for(int i = index; i < packets.size(); i++) {
            subSet.add(packets.get(i));
            helper(packets, i + 1, subSet);
            subSet.remove(subSet.size() - 1);
        }
    }

    static boolean subsetSum(List<Integer> S, int n, int a, int b, int c) {
        // return true if the subset is found
        if (a == 0 && b == 0 && c == 0) {
            return true;
        }

        // base case: no items left
        if (n < 0) {
            return false;
        }

        // Case 1. The current item becomes part of the first subset
        boolean A = false;
        if (a - S.get(n) >= 0) {
            A = subsetSum(S, n - 1, a - S.get(n), b, c);
        }

        // Case 2. The current item becomes part of the second subset
        boolean B = false;
        if (!A && (b - S.get(n) >= 0)) {
            B = subsetSum(S, n - 1, a, b - S.get(n), c);
        }

        // Case 3. The current item becomes part of the third subset
        boolean C = false;
        if ((!A && !B) && (c - S.get(n) >= 0)) {
            C = subsetSum(S, n - 1, a, b, c - S.get(n));
        }

        // return true if we get a solution
        return A || B || C;
    }

    // Function for solving the 3–partition problem. It returns true if the given
    // set `S[0…n-1]` can be divided into three subsets with an equal sum.
    static boolean partition(List<Integer> S, int sum)
    {
        // total number of items in `S`
        int n = S.size();

        // base case
        if (n < 3) {
            return false;
        }

        // return true if the sum is divisible by 3 and the set `S` can
        // be divided into three subsets with an equal sum
        return (sum > 0 && sum % 3 == 0) && subsetSum(S, n - 1, sum/3, sum/3, sum/3);
    }
}
