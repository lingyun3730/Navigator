package com.sata.contest;

public class Experience {
    public static int minNumberOfHours(int ig, int ie, int[] energy, int[] experience) {
        int hours = 0;
        int n = energy.length;
        for(int i = 0; i < n; i++) {
            if(ig <= energy[i]) {
                hours += (energy[i] - ig + 1); //学习增加能量
                ig += (energy[i] -ig + 1); //总的能量增加
            }
            if(ie <= experience[i]) {
                hours += (experience[i] - ie + 1); //学习增加经验
                ie += (experience[i] - ie + 1); //总的经验增加
            }
            ig -= energy[i]; //打完一个敌人，能量减少
            ie += experience[i]; //打完一个敌人，经验增加
        }
        return hours;
    }

    public static void main(String[] args) {
        int res = minNumberOfHours(1, 1, new int[]{1, 1, 1, 1}, new int[]{1, 1, 1, 50});
        System.out.println(res);
    }
}

