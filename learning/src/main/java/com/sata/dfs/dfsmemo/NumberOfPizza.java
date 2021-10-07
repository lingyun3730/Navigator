package com.sata.dfs.dfsmemo;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 1444
 * dfs + memo: dfs深度搜索的时候，把一些状态记录下来，避免重复计算
 * 记忆化搜索的好处是，减少递归深度，避免重复计算，空间换时间。
 */
public class NumberOfPizza {

    public static int ways(String[] pizza, int k) {
        if (pizza == null || pizza.length == 0) return 0;
        Map<String, Long> memo = new HashMap<>();
        return (int) dfs(pizza, k, memo, 0, pizza.length - 1, 0, pizza[0].length() - 1) % (10 ^ 9 + 7);
    }

    public static long dfs(String[] pizza, int k, Map<String, Long> memo, int rowS, int rowL, int colS, int colL) {
        if (rowS > rowL || colS > colL || k <= 0) return 0;
        String curStatus = rowS + "#" + colS + "#" + k;
        if (memo.containsKey(curStatus)) {
            return memo.get(curStatus);
        }
        long res = 0; //当前这个区域【rowS, rowL, colS, colL]框定的区域内切k刀所有可能的合法的切法
        if (k == 1) {
            res = isValid(pizza, rowS, rowL, colS, colL) ? 1 : 0;
        } else {
            /**
             * 所有横着切的可能性，这一刀下去能切出来有苹果的一块pizza, 就切，然后继续向下走dfs
             */
            for(int i = rowS; i <= rowL; i++) {
                if(isValid(pizza, rowS, i, colS, colL)) { //[rowS, i, colS, colL] 区间内的合法性
                    res += dfs(pizza, k-1, memo, i + 1, rowL, colS, colL);
                }
            }
            /**
             * 所有竖着切的可能性，这一刀下去能切出来有苹果的一块pizza, 就切，然后继续向下走dfs
             */
            for(int j = colS; j <= colL; j++) {
                if(isValid(pizza, rowS, rowL, colS, j)) { //[rowS, colL, colS, j] 区间内的合法性
                    res += dfs(pizza, k-1, memo, rowS, rowL, j + 1, colL);
                }
            }
        }
        memo.put(curStatus, res); //当前这个区域【rowS, rowL, colS, colL]框定的区域内切k刀所有可能的合法的切法
        return res;
    }

    public static boolean isValid(String[] pizza, int rowS, int rowL, int colS, int colL) {
        for (int i = rowS; i <= rowL; i++) {
            for (int j = colS; j <= colL; j++) {
                if (pizza[i].charAt(j) == 'A') {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] pizza = {"A..","AAA","..."};
        int res = ways(pizza, 3);
        System.out.println(res);
    }
}
