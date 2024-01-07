package com.sata.OJ.shudu;

import java.util.*;

public class Main {
    private static int res = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        char[][] p = new char[9][9];
        List<Integer> rest = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            String x = in.nextLine();
            String[] strs = x.split(" ");
            int j = 0;
            for(String str : strs) {
                p[i][j] = str.charAt(0);
                if(p[i][j] == '*') {
                    rest.add(i * 9 + j);
                }
                j++;
            }
        }
        helper(p, 0, rest);
        System.out.println(res);
    }

    private static boolean isValid(char[][] board, int row, int col, char k) {
        for(int i = 0; i < board.length; i++) {
            if(board[row][i] == k) return false;
        }
        for(int i = 0; i < board.length; i++) {
            if(board[i][col] == k) return false;
        }
        int startRow = (row/3) *3;
        int startCol = (col/3) *3;
        for(int i = startRow; i < startRow + 3; i++) {
            for(int j = startCol; j < startCol + 3; j++) {
                if(board[i][j] == k) return false;
            }
        }
        return true;
    }


    private static void helper(char[][] p, int x, List<Integer> rest) {
        if(x >= rest.size()){
            if(allFilled(p)) {
                res++;
            }
            return;
        }
        int i = rest.get(x);
        for(int l = 1; l <= 9; l++) {
            if(isValid(p, i / 9, i % 9, String.valueOf(l).charAt(0))) {
                p[i/9][i % 9] = String.valueOf(l).charAt(0);
                helper(p, x + 1, rest);
                p[i/9][i % 9] = '*';
            }
        }
    }

    private static boolean allFilled(char[][] p) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(p[i][j] == '*') return false;
            }
        }
        return true;
    }
}
