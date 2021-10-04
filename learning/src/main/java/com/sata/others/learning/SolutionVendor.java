package com.sata.others.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SolutionVendor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<Integer>> strList = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int verdorTypes = sc.nextInt();
        int workVolume = sc.nextInt();
        strList.add(temp);
        for(int i = 0; i < verdorTypes * 2; i++) {
            if(i % 2 == 0) {
                temp = new ArrayList<>();
            }
            if(sc.hasNext()) temp.add(sc.nextInt());
            if(i % 2 == 1) strList.add(temp);
        }
        System.out.println(vendorSolving(verdorTypes, workVolume, strList));
    }

    private static int vendorSolving(int verdorTypes, int workVolume, List<List<Integer>> input) {
        return input.size();
    }
}

