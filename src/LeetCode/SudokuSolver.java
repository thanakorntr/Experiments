package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.

 Empty cells are indicated by the character '.'.

 You may assume that there will be only one unique solution.
 *
 * Created by Thanakorn on 6/12/16.
 */
public class SudokuSolver {

    public static void main(String[] args) {
        SudokuSolver sudokuSolver = new SudokuSolver();
        char[][] sudoku = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        sudokuSolver.solveSudoku(sudoku);

        for (char[] row : sudoku) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return;
        }
        solveSudokuHelper(board, 0);
    }

    private boolean solveSudokuHelper(char[][] board, int row) {
        for (int i = row; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (int num = 1; num <= 9; num++) {
                        char c = (char)('1' + (num - 1));
                        if (!isValidPlace(board, c, i, j)) {
                            continue;
                        }
                        board[i][j] = c;
                        int nextRow = j < 8 ? row : row + 1;
                        if (solveSudokuHelper(board, nextRow)) {
                            return true;
                        }
                    }
                    board[i][j] = '.';
                    return false;
                }
            }
        }
        return isValid(board);
    }

    private boolean isValid(char[][] board) {
        for (int row = 0; row < 9; row++) {
            Set<Character> visitedChar = new HashSet<>();
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != '.') {
                    if (visitedChar.contains(board[row][col])) {
                        return false;
                    }
                    visitedChar.add(board[row][col]);
                }
            }
        }

        for (int col = 0; col < 9; col++) {
            Set<Character> visitedChar = new HashSet<>();
            for (int row = 0; row < 9; row++) {
                if (board[row][col] != '.') {
                    if (visitedChar.contains(board[row][col])) {
                        return false;
                    }
                    visitedChar.add(board[row][col]);
                }
            }
        }

        for (int startRow = 0; startRow <= 6; startRow += 3) {
            for (int startCol = 0; startCol <= 6; startCol += 3) {
                Set<Character> visitedChar = new HashSet<>();
                for (int row = startRow; row < startRow + 3; row++) {
                    for (int col = startCol; col < startCol + 3; col++) {
                        if (board[row][col] != '.') {
                            if (visitedChar.contains(board[row][col])) {
                                return false;
                            }
                            visitedChar.add(board[row][col]);
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean isValidPlace(char[][] board, char c, int row, int col) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) {
                return false;
            }
        }
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == c) {
                    return false;
                }
            }
        }
        return true;
    }

}
