package com.sata.dfs.dfsmemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.lintcode.com/problem/1114/?showListFe=true&page=4&companyTagId=63&pageSize=50
 * 记忆化搜索，每个promotion方案可以重复使用，可以先写纯的带返回值的dfs, 然后再改造成记忆化搜索。
 */
public class SalePromotion {
    //dfs with return value.

    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // write your code here
        //暴力搜索穷尽所有的方案，用记忆化搜索来加快速度。
        Map<String, Integer> memo = new HashMap<>();
        return helper(price, special, needs, memo);
    }

    private static int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<String, Integer> memo) {
        if(memo.containsKey(toStr(needs))) return memo.get(toStr(needs));
        else {
            int res = 0;
            for (int i = 0; i < needs.size(); i++) {
                res += needs.get(i) * price.get(i);  // no promotion
            }
            for (int i = 0; i < special.size(); i++) {
                List<Integer> sp = special.get(i);
                boolean canUsePromotion = true;
                for (int j = 0; j < sp.size() - 1; j++) {
                    if (sp.get(j) > needs.get(j)) {
                        canUsePromotion = false;
                        break; // exceed needs, can not use this promotion way
                    }
                }
                //use promotion
                for (int k = 0; k < needs.size(); k++) {
                    needs.set(k, needs.get(k) - sp.get(k));
                }
                if (canUsePromotion) {
                    res = Math.min(res, shoppingOffers(price, special, needs) + sp.get(sp.size() - 1));
                }
                //restore
                for (int k = 0; k < needs.size(); k++) {
                    needs.set(k, needs.get(k) + sp.get(k));
                }
            }
            memo.put(toStr(needs), res);
            return res;
        }
    }

    private static String toStr(List<Integer> needs) {
        StringBuilder res = new StringBuilder();
        for(int need: needs) {
            res.append(need).append("_");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        List<Integer> price = new ArrayList<Integer>();
        price.add(2);
        price.add(3);
        List<Integer> sp = new ArrayList<Integer>();
        sp.add(1);
        sp.add(0);
        sp.add(1);
        List<Integer> sp1 = new ArrayList<Integer>();
        sp1.add(0);
        sp1.add(1);
        sp1.add(2);
        List<List<Integer>> special = new ArrayList<>();
        special.add(sp);
        special.add(sp1);
        List<Integer> needs = new ArrayList<>();
        needs.add(1);
        needs.add(1);
        int res = shoppingOffers(price, special, needs);
        System.out.println(res);
    }
}
