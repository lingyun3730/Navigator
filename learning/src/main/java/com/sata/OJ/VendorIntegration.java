package com.sata.OJ;

import java.util.Arrays;
import java.util.Scanner;

public class VendorIntegration {

    static int maxEffort = 40000;
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        String s = in.nextLine();
        String[] input1 = s.split(" ");
        int n = Integer.parseInt(input1[0]); //vendor number, 物品个数
        int workLoad = Integer.parseInt(input1[1]); // workload, 背包重量
        int[] inte = new int[n]; //integration effort， 物品消耗
        int[] velo = new int[n];  //process velocity， 物品重量，（也带有消耗）
        for(int i = 0; i < n; i++) {
            String tmp = in.nextLine();
            String[] vendor = tmp.split(" ");
            inte[i] = Integer.parseInt(vendor[0]);
            velo[i] = Integer.parseInt(vendor[1]);
        }

        int[] dp = new int[maxEffort + 1]; //dp[i]表示i天最多能完成多少工作
        //dp[i] = max(dp[i], dp[i - itgrt[i]] + (i - itgrt[j]) * velo[j]) //前面表示不集成它，后面表示集成它
        for(int i = 0; i < maxEffort; i++) {//对 i 天, 遍历每个vendor, 然后选择集成它和不集成它
            for(int j = 0; j < n; j++) {
                if(i > inte[j]) dp[i] = Math.max(dp[i], dp[i - inte[j]] + (i - inte[j]) * velo[j]);
            }
            if(dp[i] >= workLoad) {
                System.out.println(i);
                break;
            }
        }

        //dfs直接解决
        //求的是能创造固定价值的最小背包容量，所以从小到大遍历背包容量，当刚好能达到固定价值的时候，就能求出最小的背包容量
//        for(int i = 0; i <= maxEffort; i++) {
//            //每一天作为背包容量，vendor消耗的天数作为物品，vendor创造的价值作为收益
//            int[][] memo = new int[maxEffort + 1][n];
//            for(int x = 0; x < memo.length; x ++) {
//                Arrays.fill(memo[x], -1);
//            }
//            int res = dfs(0, i, 0, inte, velo, n, memo);
//            if(res >= workLoad) {
//                System.out.println(i);
//                break;
//            }
//        }

    }

    //top-down solution: calculate days-nowDays 时间内所能创造的最大价值，递归到最后就是nowDays = days, 时间区间为0
    private static int dfs(int nowDays, int days, int index, int[] inte, int[] velo, int n, int[][] memo) {
        if(nowDays >= days || index >= n) return 0;
        if(memo[nowDays][index] >= 0) return memo[nowDays][index];
        //完全背包问题的递归写法。。。
        int res = 0;
//        if(days - nowDays - inte[index] >= 0) {
//            //集成，然后选择下一件
//            int tmp1 = dfs( nowDays + inte[index], days,index + 1, inte, velo, n, memo) + velo[index] * (days - nowDays - inte[index]);
//            //集成，下次还选这一件
//            int tmp2 = dfs(nowDays + inte[index], days, index, inte, velo, n, memo) + velo[index] * (days - nowDays - inte[index]);
//            //不集成这一件
//            int tmp3 = dfs(nowDays, days, index + 1, inte, velo, n, memo);
//            res = Math.max(tmp1, Math.max(tmp2, tmp3));
//        }else {
//            res = dfs(nowDays, days, index + 1, inte, velo, n, memo);
//        }

        for(int i = index; i < n; i++) {
            int tmp;
            if(days - nowDays - inte[i] >= 0) {
                tmp = dfs( nowDays + inte[i], days, i, inte, velo, n, memo) + velo[i] * (days - nowDays - inte[i]);
            }else{
                tmp = dfs(nowDays, days, i + 1, inte, velo, n, memo);
            }
            res = Math.max(res, tmp);
        }
        memo[nowDays][index] = res;
        return res;
    }
}
