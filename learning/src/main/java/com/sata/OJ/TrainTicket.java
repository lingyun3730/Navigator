package com.sata.OJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TrainTicket {
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        String s = in.nextLine();
        int num = Integer.parseInt(s);
        int[] rest = new int[63];
        Arrays.fill(rest, 4000);
        boolean res = true;
        List<String[]> input = new ArrayList<>();
        for(int i = 0; i < num; i++) {
            String x = in.nextLine();
            String[] strVec = x.split(" ");
            input.add(strVec);
        }
        for(String[] strVec : input) {
            if(Integer.parseInt(strVec[0]) > 4000 || Integer.parseInt(strVec[0]) <= 0
                    || Integer.parseInt(strVec[1]) >= 64 || Integer.parseInt(strVec[1]) < 0
                    || Integer.parseInt(strVec[2]) >= 64 || Integer.parseInt(strVec[2]) < 0
                    || Integer.parseInt(strVec[2]) <= Integer.parseInt(strVec[1])) {
                res = false;
                break;
            }
            for(int j = Integer.parseInt(strVec[1]); j <= Integer.parseInt(strVec[2]) - 1; j++) {
                rest[j] = rest[j] - Integer.parseInt(strVec[0]);
                if(rest[j] < 0) {
                    res = false;
                    break;
                }
            }
        }
        System.out.println(res ? "true" : "false");
    }
}
