package com.sata.greedy;

/**
 * LC 738 : char array to String ： new String(charArray)
 */
public class MonotoneIncreasingDigits {
    public static int monotoneIncreasingDigits(int n) {
        //从后往前遍历
        String nStr = String.valueOf(n);
        char[] charArray = nStr.toCharArray(); //用toCharArray
        int flag = n; //找到第一个需要被变化为9的位置，后面全部填充为9
        for(int i = charArray.length-2; i >= 0; i--) {
            if(charArray[i] > charArray[i+1]) {
                flag = i;
                charArray[i] = (char) (charArray[i] - 1);
            }
        }
        for(int i = flag + 1; i < charArray.length; i++) {
            charArray[i] = '9';
        }
        return Integer.parseInt(new String(charArray));
    }

    public static void main(String[] args) {
        int res = monotoneIncreasingDigits(332);
        System.out.println(res);
    }
}
