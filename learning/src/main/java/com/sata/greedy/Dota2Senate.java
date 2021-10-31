package com.sata.greedy;

/**
 * LC 649
 */
public class Dota2Senate {

    public static String predictPartyVictory(String senate) {
        //贪心策略：每一个参议员都去禁止后面第一个未被禁止的敌对参议员的权利，经过若干轮，直到有权利的参议员都属于同一个阵营，则改阵营获胜。
        int n = senate.length();
        int rn = 0;
        for(char c : senate.toCharArray()) {
            if(c == 'R') rn ++;
        }
        int dn = n - rn;
        int flag = 0;
        char[] chs = senate.toCharArray();
        while(rn > 0 && dn > 0) {
            for(int i = 0; i < chs.length; i++) {
                if(chs[i] == 'R') {
                    if(flag < 0) {
                        chs[i] = 'O';
                        rn --;
                    }
                    flag ++;
                }else if(chs[i] == 'D') {
                    if(flag > 0) {
                        chs[i] = 'O';
                        dn --;
                    }
                    flag --;
                }
                if(rn == 0) return "Dire";
                if(dn == 0) return "Radiant";
            }
        }
        return rn == 0 ? "Dire" : "Radiant";
    }
}
