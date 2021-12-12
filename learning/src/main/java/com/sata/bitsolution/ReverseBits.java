package com.sata.bitsolution;

/**
 * LC 190
 */
public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int x = 0;
        for(int i = 31; i >= 0; i--) {
            x += (((n >> i) & 1) << (31 - i)); //从最高位取起
        }
        return x;
    }
}
