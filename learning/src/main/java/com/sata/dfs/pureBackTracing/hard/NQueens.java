package com.sata.dfs.pureBackTracing.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 51
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] chess = new char[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                chess[i][j] = '.';
            }
        }
        helper(res, chess, 0);
        return res;
    }

    private List<String> arrayToList(char[][] chess) {
        List<String> res = new ArrayList<>();
        for(char[] c : chess) {
            res.add(new String(c));
        }
        return res;
    }

    private void helper(List<List<String>> res, char[][] chess, int row) {
        if(row == chess.length) {
            res.add(arrayToList(chess));
            return;
        }
        //for each column
        for(int col = 0; col < chess.length; col++) {
            if(isValid(row, col, chess)) {
                chess[row][col] = 'Q';
                helper(res, chess, row + 1);
                chess[row][col] = '.';
            }
        }
    }

    private boolean isValid(int row, int col, char[][] chess) {
        //行列不冲突
        for(int i = 0; i < row; i++) {
            if(chess[i][col] == 'Q') return false;
        }
        //45度
        for(int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
            if(chess[i][j] == 'Q') return false;
        }
        //135
        for(int i = row -1, j = col - 1; i >=0 && j >= 0; i--, j--) {
            if(chess[i][j] == 'Q') return false;
        }
        return true;
    }
}
