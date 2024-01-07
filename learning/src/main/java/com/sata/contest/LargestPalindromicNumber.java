package com.sata.contest;

public class LargestPalindromicNumber {
    public static String largestPalindromic(String num) {
        int[] fre = new int[10]; // 0 -> 9
        for(char c : num.toCharArray()) {
            int cur = (c - '0');
            fre[cur] ++;
        }
        if(num.toCharArray().length == fre[0]) return "0";
        boolean odd = false;
        int firstBiggerThan1 = 9;
        boolean findFirst = false;
        for(int i = 9; i >= 0; i--) {
            if(fre[i] > 1 && ! findFirst)  {
                firstBiggerThan1 = i;
                findFirst = true;
            }
            if(fre[i] == 0) continue;
            if((fre[i] % 2) != 0 && !odd) {
                odd = true;
                continue;
            }
            if(odd && (fre[i] % 2) != 0) fre[i] = fre[i] - 1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 9; i >= 0; i--) {
            if(fre[i] == 0) continue;
            if(i == 0 && firstBiggerThan1 == 0) break;
            int x = fre[i] / 2;
            while(x > 0) {
                sb.append(i);
                x--;
            }
            fre[i] = fre[i] % 2;
        }

        boolean tag = false;
        for(int i = 9; i >= 0; i--) {
            if((fre[i] % 2) != 0) {
                tag = true;
                sb.append(i);
                break;
            }
        }
        StringBuilder sbReverse = new StringBuilder(sb);
        if(tag) sbReverse.deleteCharAt(sbReverse.length() - 1);
        sbReverse.reverse();
        sb.append(sbReverse);
        return sb.toString();
    }

    public static void main(String[] args) {
        String res = largestPalindromic("00");
        System.out.println(res);
    }
}
