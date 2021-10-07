package com.sata.dfs.basicquestions;

import java.util.ArrayList;
import java.util.List;

/**
 * 母题3：组合问题。
 * Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents), get all the possible ways to pay a target number of cents.
 * Arguments
 * coins - an array of positive integers representing the different denominations of coins, there are no duplicate numbers and the numbers are sorted by descending order, eg. {25, 10, 5, 2, 1}
 * target - a non-negative integer representing the target number of cents, eg. 99
 * Assumptions
 * coins is not null and is not empty, all the numbers in coins are positive
 * target >= 0
 * You have infinite number of coins for each of the denominations, you can pick any number of the coins.
 * Return
 * a list of ways of combinations of coins to sum up to be target.
 * each way of combinations is represented by list of integer, the number at each index means the number of coins used for the denomination at corresponding index.
 * Examples
 * coins = {2, 1}, target = 4, the return should be
 * [
 *   [0, 4],   (4 cents can be conducted by 0 2 cents + 4 1 cents)
 *   [1, 2],   (4 cents can be conducted by 1 2 cents + 2 1 cents)
 *   [2, 0]    (4 cents can be conducted by 2 2 cents + 0 1 cents)
 * ]
 */
public class CoinCombination {

    /**
     * Think about that:
     * 1.What does each level store and how many levels should we have.
     * 2.How many states should we try to put on each level.
     * @param coins
     * @param target
     * @return
     */
    public static List<List<Integer>> getAllCoinCombinations(List<Integer> coins, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        helper(coins, target, res, 0, tmp);
        return res;
    }

    public static void helper(List<Integer> coins,
                              int target,
                              List<List<Integer>> res,
                              int level,
                              List<Integer> tmp) {
        if(level == coins.size()) { //the last level.
            if(target == 0){
                res.add(new ArrayList<>(tmp));
            }
            return ;
        }
        //list all states in each level, each coin can be retrieved many times.
        for(int i = 0; target >= i * coins.get(level); i++) {
            tmp.add(i);
            helper(coins, target - i * coins.get(level), res, level + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<Integer> coins = new ArrayList<>();
        coins.add(2);
        coins.add(1);
        List<List<Integer>> res = getAllCoinCombinations(coins, 4);
        System.out.println(res.size());
    }

}
