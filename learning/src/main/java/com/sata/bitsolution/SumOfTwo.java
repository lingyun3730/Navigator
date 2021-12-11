package com.sata.bitsolution;

/**
 * LC 371
 */
public class SumOfTwo {
    public int getSum(int a, int b) {
        /**
         * 不考虑进位的加，0+0=0，0+1=1, 1+0=1，1+1=0，这就是异或的运算规则，
         * 如果只考虑进位的加 0+0=0, 0+1=0, 1+0=0, 1+1=1，而这其实这就是'与'的运算，
         * 而第三步在将两者相加时，再递归调用这个算法，终止条件是当进位为0时，直接返回第一步的结果。
         */
        while(b != 0) { //进位为0时退出
            int sum = a ^ b; //不考虑进位的加
            int carry = (a & b) << 1; //只考虑进位的加
            a = sum;
            b = carry;
        }
        return a;
    }
}
