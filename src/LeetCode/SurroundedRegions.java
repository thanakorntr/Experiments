package LeetCode;

import java.util.*;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

 A region is captured by flipping all 'O's into 'X's in that surrounded region.

 For example,
 X X X X
 X O O X
 X X O X
 X O X X
 After running your function, the board should be:

 X X X X
 X X X X
 X X X X
 X O X X

 *
 * Created by Thanakorn on 12/30/15.
 */
public class SurroundedRegions {

    public static void main(String[] args) {

        char[][] board = new char[][] {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        new SurroundedRegions().solve(board);

        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }


    public void solve(char[][] board) {

        if (board == null || board.length < 3 || board[0].length < 3) {
            return;
        }

        final int ROW = board.length;
        final int COL = board[0].length;

        boolean[][] visited = new boolean[ROW][COL];

        for (int row = 0; row < ROW; row++) {
            if (!visited[row][0] && board[row][0] == 'O') {
                bfsOBoundaries(board, row, 0, visited);
            }
            if (!visited[row][COL - 1] && board[row][COL - 1] == 'O') {
                bfsOBoundaries(board, row, COL - 1, visited);
            }
        }
        for (int col = 0; col < COL; col++) {
            if (!visited[0][col] && board[0][col] == 'O') {
                bfsOBoundaries(board, 0, col, visited);
            }
            if (!visited[ROW - 1][col] && board[ROW - 1][col] == 'O') {
                bfsOBoundaries(board, ROW - 1, col, visited);
            }
        }

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col] == 'B') {
                    board[row][col] = 'O';
                } else if (board[row][col] == 'O') {
                    board[row][col] = 'X';
                }
            }
        }
    }

    private void bfsOBoundaries(char[][] board, int row, int col, boolean[][] visited) {

        Queue<Map.Entry<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new AbstractMap.SimpleEntry<>(row, col));

        while (!queue.isEmpty()) {
            Map.Entry<Integer, Integer> coordinate = queue.poll();
            int r = coordinate.getKey();
            int c = coordinate.getValue();
            visited[r][c] = true;
            board[r][c] = 'B';

            // upper
            if (r - 1 >= 0 && !visited[r - 1][c] && board[r - 1][c] == 'O') {
                queue.add(new AbstractMap.SimpleEntry<>(r - 1, c));
            }

            // lower
            if (r + 1 < board.length && !visited[r + 1][c] && board[r + 1][c] == 'O') {
                queue.add(new AbstractMap.SimpleEntry<>(r + 1, c));
            }

            // left
            if (c - 1 >= 0 && !visited[r][c - 1] && board[r][c - 1] == 'O') {
                queue.add(new AbstractMap.SimpleEntry<>(r, c - 1));
            }

            // right
            if (c + 1 < board[0].length && !visited[r][c + 1] && board[r][c + 1] == 'O') {
                queue.add(new AbstractMap.SimpleEntry<>(r, c + 1));
            }
        }
    }
}
