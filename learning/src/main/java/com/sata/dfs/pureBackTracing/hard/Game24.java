package com.sata.dfs.pureBackTracing.hard;

/**
 * LC 679
 */
public class Game24 {
    public boolean judgePoint24(int[] cards) {
        //只需要找到一条通路即可
        double[] game = new double[] {cards[0], cards[1], cards[2], cards[3]};
        return helper(game);
    }
    private boolean helper(double[] game) {
        if(game.length == 1) return Math.abs(game[0] - 24) < 0.0001; //数组里只剩下一个数字，那么判断下这个数字和24的大小即可。
        //遍历两个数字的组合的所有可能性
        for(int i = 0; i < game.length; i++) {
            for(int j = i + 1; j < game.length; j++) {
                double[] tmp = new double[game.length - 1]; //新的临时数组
                for(int k = 0, index = 0; k < game.length; k++) {
                    if(k != i && k != j) {
                        tmp[index] = game[k];  //除了做运算的两个数字之外，其他的数字放到新的数组里
                        index ++;
                    }
                }
                for(double res : compute(game[i], game[j])) {
                    tmp[tmp.length - 1] = res; //做运算的两个数字的结果放在新的数组结尾
                    if(helper(tmp)) return true;
                }
            }
        }
        return false;
    }
    private double[] compute(double x, double y) { //两个数字做运算，只有6种可能性
        return new double[] {x - y, y - x, x + y, x * y, x / y, y / x};
    }
}
