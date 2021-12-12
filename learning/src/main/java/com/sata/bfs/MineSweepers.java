package com.sata.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC 529 :扫雷游戏
 */
public class MineSweepers {
    public char[][] updateBoard(char[][] board, int[] click) {
        //bfs
        int[][] dirs = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, -1},
                {-1, 1}, {1, -1}, {1, 1}};
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int[] next = q.poll();
                char c = board[next[0]][next[1]];
                if(c == 'M') {
                    board[next[0]][next[1]] = 'X';
                    return board;
                }
                if(c == 'E') {
                    int adjMineNumber = 0;
                    for(int[] dir : dirs) {
                        int[] newPos = new int[] {next[0] + dir[0], next[1] + dir[1]};
                        if(isValidPos(newPos, board) && board[newPos[0]][newPos[1]] == 'M') {
                            adjMineNumber ++;
                        }
                    }
                    if(adjMineNumber > 0) {
                        board[next[0]][next[1]] = (char) (adjMineNumber + '0');
                    }else {
                        board[next[0]][next[1]] = 'B';
                        for(int[] dir : dirs) {
                            int[] newPos = new int[] {next[0] + dir[0], next[1] + dir[1]};
                            if(isValidPos(newPos, board)) q.add(newPos);
                        }
                    }
                }
            }
        }
        return board;
    }
    private boolean isValidPos(int[] newPos, char[][] board) {
        return newPos[0] >= 0 && newPos[0] < board.length && newPos[1] >= 0 && newPos[1] < board[0].length;
    }
}
