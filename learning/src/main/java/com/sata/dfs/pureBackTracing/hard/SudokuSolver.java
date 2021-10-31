package com.sata.dfs.pureBackTracing.hard;

/**
 * LC 37
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        helper(board);
    }

    private boolean helper(char[][] board) {
        //结束的标志就是填完整个board,要么在某个位置上没有合适的答案，返回false，要么就填好了找到一个答案立即返回true.
        for(int i = 0; i < board.length; i++) { //二维回溯，找到一个答案就返回，所以不需要递归终止条件，并不需要遍历整个树
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] != '.') continue;
                for(char k = '1'; k <= '9'; k++) {
                    if(isValid(board, i, j, k)) {
                        board[i][j] = k;
                        if(helper(board)) return true; //以新的board为起点，如果找到一个解，立即返回
                        board[i][j] = '.';
                    }
                }
                return false; //9个数字都不合法，返回false;
            }
        }
        return true; //遍历完整个board都没有返回false，说明填好board，返回true.
    }

    private boolean isValid(char[][] board, int row, int col, char k) {
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
}
