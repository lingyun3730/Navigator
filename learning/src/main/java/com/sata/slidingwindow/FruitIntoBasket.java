package com.sata.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 904
 */
public class FruitIntoBasket {
    public static int totalFruit(int[] fruits) {
        int n = fruits.length;
        int i = 0;
        int j = 0;
        Map<Integer, Integer> mp = new HashMap<>(); //fruit type : number in sliding window
        int res = 0;
        //the key point of the constraint is that the sliding window has only less than 2 types of fruits
        for(; j < n; j++) {
            //meet a new fruit type
            if(! mp.containsKey(fruits[j])) {
                if(mp.keySet().size() < 2) {
                    mp.put(fruits[j], 1);
                }else{
                    //adjust sliding window
                    //current map has more than 2 types of fruits, to retire the front fruit in the sliding window
                    while(mp.keySet().size() >= 2) {
                        mp.put(fruits[i], mp.get(fruits[i]) - 1);
                        if(mp.get(fruits[i]) == 0) {
                            mp.remove(fruits[i]);
                        }
                        i++;
                    }
                    mp.put(fruits[j], 1);
                }
            }else{
                mp.put(fruits[j], mp.get(fruits[j]) + 1);
            }
            res = j - i + 1 > res ? j - i + 1: res;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] fruits = {0, 1, 2, 2};
        int res = totalFruit(fruits);
        System.out.println(res);
    }
}
