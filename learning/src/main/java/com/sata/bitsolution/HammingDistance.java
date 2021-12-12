package com.sata.bitsolution;

/**
 * LC 461
 */
public class HammingDistance {
    public static int hammingDistance(int x, int y) {
        int count = 0;
        for(int i=31; i >= 0; i--) {
            count += (((x >> i) & 1) ^ ((y >> i) & 1));
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingDistance(4, 1));
    }
}
