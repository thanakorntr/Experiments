package LeetCode;

/**
 * Created by Thanakorn on 10/3/15.
 *
 *  According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

 Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

 Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 Any live cell with two or three live neighbors lives on to the next generation.
 Any live cell with more than three live neighbors dies, as if by over-population..
 Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

 Write a function to compute the next state (after one update) of the board given its current state.

 Follow up:

 Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?

 */
public class GameOfLife {

    public static void main(String[] args) {

        int[][] board = new int[][] {
                                      {1,1},
                                      {1,0}
                                    };

        gameOfLife(board);
    }

    private static void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        final int ROW = board.length, COL = board[0].length;
        int[][] tmp = new int[ROW][COL];

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                int liveNeighbors = countLiveNeighbors(board, row, col, ROW, COL);
                if (board[row][col] == 1) {  // live cell
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        tmp[row][col] = 0;
                    } else  {
                        tmp[row][col] = 1;
                    }
                } else {  // dead cell
                    if (liveNeighbors == 3) {
                        tmp[row][col] = 1;
                    } else {
                        tmp[row][col] = 0;
                    }
                }
            }
        }

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = tmp[row][col];
            }
        }
    }

    private static int countLiveNeighbors(int[][] board, int row, int col, final int ROW, final int COL) {
        int count = 0;
        for (int i = row-1; i <= row+1; i++) {
            for (int j = col-1; j <= col+1; j++) {
                if (i >= 0 && i < ROW && j >= 0 && j < COL) {
                    if (i != row || j != col) {
                        if (board[i][j] == 1) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
