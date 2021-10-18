package com.sata.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 904
 */
public class FruitIntoBasket {
    public static int totalFruit(int[] fruits) {
        //sliding window: 只能拿到最多两种水果，其实是选出连续的子数组中包含哪两种水果树最多
        int n = fruits.length;
        int i = 0;
        int j = 0;
        Map<Integer, Integer> mp = new HashMap<>(); //<type : number> map, 记录滑动窗口内的状态
        int res = Integer.MIN_VALUE;
        for(; j < n; j++) {
            if(! mp.containsKey(fruits[j])) {
                if(mp.keySet().size() < 2) {
                    mp.put(fruits[j], 1);
                }else{
                    //滑动窗口
                    res = res < j-i? j-i : res; // do not contain j (i -> j-1)
                    while(mp.keySet().size() >= 2) {
                        mp.put(fruits[i], mp.get(fruits[i]) - 1);
                        if(mp.get(fruits[i]) == 0) {
                            mp.remove(fruits[i]); //移除一个key
                        }
                        i++;
                    }
                    mp.put(fruits[j], 1);
                }
            }else{
                mp.put(fruits[j], mp.get(fruits[j]) + 1);
            }
        }
        return res < j-i ? j-i : res; //最后处理需要注意
    }

    public static void main(String[] args) {
        int[] fruits = {0, 1, 2, 2};
        int res = totalFruit(fruits);
        System.out.println(res);
    }
}
