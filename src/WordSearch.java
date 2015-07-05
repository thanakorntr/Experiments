/**
 * Created by Thanakorn on 6/29/15.
 */
public class WordSearch {

    public static void main(String[] args) {

        char[][] board = {
                            {'A','B','C','E'},
                            {'S','F','C','S'},
                            {'A','D','E','E'}
                         };

        System.out.println(exist(board, "ABCCED"));  // true
        System.out.println(exist(board, "SEE"));  // true
        System.out.println(exist(board, "ABCB"));  // false

//        char[][] board = {
//                {'A'}
//        };
//
//        System.out.println(exist(board, "A"));  // true
    }


    public static boolean exist(char[][] board, String word) {

        char[] charArray = word.toCharArray();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == charArray[0]) {
                    boolean[][] visited = new boolean[board.length][board[row].length];
                    boolean exist = existHelper(board, charArray, word, row, col, 0, visited);
                    if (exist) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean existHelper(char[][] board, char[] charArray, String word, int row, int col, int startindex, boolean[][] visited) {
        if (startindex == word.length() -1 && charArray[startindex] == board[row][col]) {
            return true;
        }

        if (charArray[startindex] != board[row][col]) {
            visited[row][col] = false;
            return false;
        }

        visited[row][col] = true;

        boolean exist = false;

        if (isValidNextMove(board, row-1, col) && !visited[row-1][col]) {
            exist |= existHelper(board, charArray, word, row-1, col, startindex+1, visited);
            if (exist) {
                return true;
            }
        }
        if (isValidNextMove(board, row+1, col) && !visited[row+1][col]) {
            exist |= existHelper(board, charArray, word, row+1, col, startindex+1, visited);
            if (exist) {
                return true;
            }
        }
        if (isValidNextMove(board, row, col-1) && !visited[row][col-1]) {
            exist |= existHelper(board, charArray, word, row, col-1, startindex+1, visited);
            if (exist) {
                return true;
            }
        }
        if (isValidNextMove(board, row, col+1) && !visited[row][col+1]) {
            exist |= existHelper(board, charArray, word, row, col+1, startindex+1, visited);
            if (exist) {
                return true;
            }
        }

        if (!exist) {
            visited[row][col] = false;
        }
        return exist;
    }

    public static boolean isValidNextMove(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }

        return true;
    }
}
