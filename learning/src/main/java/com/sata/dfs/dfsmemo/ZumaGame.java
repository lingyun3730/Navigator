package com.sata.dfs.dfsmemo;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 488
 */
public class ZumaGame {
    private static int maxVal = 1000;

    public static int findMinStep(String board, String hand) {
        // memo records the min step to eliminate board to "" from status of original board + "*" + hand
        Map<String, Integer> mp = new HashMap<>();
        int res = dfs(board, hand, mp); // top -> down, from whole board string to "".
        return res == maxVal ? -1 : res;
    }

    public static int dfs(String board, String hand, Map<String, Integer> mp) {
        if(board.length() > 0 && hand.length() == 0) return maxVal;
        StringBuilder boardBuilder = new StringBuilder(board);
        StringBuilder handBuilder = new StringBuilder(hand);
        String status = board + "*" + hand;
        if(mp.containsKey(status)) {
            return mp.get(status);
        }
        int boardLen = board.length();
        int handLen = hand.length();
        int score = maxVal;
        for(int i = 0; i <= boardLen; i++) {
            for(int j = 0; j < handLen; j++) {
                boardBuilder.insert(i, hand.charAt(j));
                String move = afterMove(boardBuilder.toString());
                if(move.length() == 0) {
                    mp.put(status, 1); // status -> clear only need 1 step.
                    return 1;
                }
                handBuilder.deleteCharAt(j);
                score = Math.min(score, dfs(move, handBuilder.toString(), mp) + 1);
                boardBuilder.deleteCharAt(i);
                handBuilder.insert(j, hand.charAt(j));
            }
        }
        mp.put(status, score);
        return score;
    }

    public static String afterMove(String board) {
        int n = board.length();
        for(int i = 0, j = 0; j <= n; j++) {
            if(j < n && board.charAt(j) == board.charAt(i)) continue;
            if(j - i >= 3) return afterMove(board.substring(0, i) + board.substring(j));
            else i = j;
        }
        return board;
    }

    public static void main(String[] args) {
        int res = findMinStep("WWRRBBWW", "WRBRW");
        System.out.println(res);
        assert res == 2;
    }
}
