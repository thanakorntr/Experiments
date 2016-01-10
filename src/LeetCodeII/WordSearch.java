package LeetCodeII;

/**
 * Given a 2D board and a word, find if the word exists in the grid.

 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

 For example,
 Given board =

 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]
 word = "ABCCED", -> returns true,
 word = "SEE", -> returns true,
 word = "ABCB", -> returns false.

 *
 * Created by Thanakorn on 1/10/16.
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0
                || board[0].length == 0 || word == null
                || word.isEmpty()) {
            return false;
        }

        final int ROW = board.length;
        final int COL = board[0].length;
        char[] wordArray = word.toCharArray();

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col] == wordArray[0]) {
                    boolean[][] visited = new boolean[ROW][COL];
                    boolean exist = existHelper(board, wordArray, 0, row, col, visited);
                    if (exist) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean existHelper(char[][] board, char[] wordArray, int charIndex,
                                int row, int col,
                                boolean[][] visited) {

        visited[row][col] = true;

        if (charIndex == wordArray.length - 1) {
            visited[row][col] = false;
            return board[row][col] == wordArray[wordArray.length - 1];
        }

        // upper
        if (row - 1 >= 0
                && !visited[row - 1][col]
                && board[row - 1][col] == wordArray[charIndex + 1]) {
            if (existHelper(board, wordArray, charIndex + 1, row - 1, col, visited)) {
                return true;
            }
        }
        // lower
        if (row + 1 < board.length
                && !visited[row + 1][col]
                && board[row + 1][col] == wordArray[charIndex + 1]) {
            if (existHelper(board, wordArray, charIndex + 1, row + 1, col, visited)) {
                return true;
            }
        }
        // left
        if (col - 1 >= 0
                && !visited[row][col - 1]
                && board[row][col - 1] == wordArray[charIndex + 1]) {
            if (existHelper(board, wordArray, charIndex + 1, row, col - 1, visited)) {
                return true;
            }
        }
        // right
        if (col + 1 < board[row].length
                && !visited[row][col + 1]
                && board[row][col + 1] == wordArray[charIndex + 1]) {
            if (existHelper(board, wordArray, charIndex + 1, row, col + 1, visited)) {
                return true;
            }
        }

        visited[row][col] = false;
        return false;
    }

}
