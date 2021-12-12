package com.sata.bitsolution;

/**
 * LC 405
 */
public class ConvertNumberToHex {
    public String toHex(int num) {
        //思路就是从低到高四位四位取数值 (& 0xf)，转化为十六进制表达
        char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        String res = "";
        for(int i = 0; i < 8; i++) {
            res = hex[num & 0xf] + res;
            num >>= 4;
            if(num == 0) break; //当右移动四位等于0的时候，就break.
        }
        return res;
    }
}
