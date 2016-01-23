package LeetCode;

/**
 * Given an integer matrix, find the length of the longest increasing path.

 From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

 Example 1:

 nums = [
 [9,9,4],
 [6,6,8],
 [2,1,1]
 ]
 Return 4
 The longest increasing path is [1, 2, 6, 9].

 Example 2:

 nums = [
 [3,4,5],
 [3,2,6],
 [2,2,1]
 ]
 Return 4
 The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 *
 * Created by Thanakorn on 1/23/16.
 */
public class LongestIncreasingPathinaMatrix {


    public static void main(String[] args) {

        int[][] matrix = getMatrix();
        System.out.println(longestIncreasingPath(matrix));

    }

    public static int longestIncreasingPath(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        final int ROW = matrix.length;
        final int COL = matrix[0].length;

        int lip = 1;

        int[][] memo = new int[ROW][COL];

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                int len = lipHelper(matrix, row, col, memo);
                lip = Math.max(lip, len);
            }
        }

        return lip;
    }

    private static int lipHelper(int[][] matrix, int row, int col, int[][] memo) {

        if (memo[row][col] > 0) {
            return memo[row][col];
        }

        int max = 1;

        // upper
        if (row - 1 >= 0 && matrix[row - 1][col] > matrix[row][col]) {
            int len = 1 + lipHelper(matrix, row - 1, col, memo);
            max = Math.max(max, len);
        }

        // lower
        if (row + 1 < matrix.length && matrix[row + 1][col] > matrix[row][col]) {
            int len = 1 + lipHelper(matrix, row + 1, col, memo);
            max = Math.max(max, len);
        }

        // left
        if (col - 1 >= 0 && matrix[row][col - 1] > matrix[row][col]) {
            int len = 1 + lipHelper(matrix, row, col - 1, memo);
            max = Math.max(max, len);
        }

        // right
        if (col + 1 < matrix[row].length && matrix[row][col + 1] > matrix[row][col]) {
            int len = 1 + lipHelper(matrix, row, col + 1, memo);
            max = Math.max(max, len);
        }

        memo[row][col] = max;
        return max;
    }

    private static int[][] getRandomMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int)(1 + 10 * Math.random());
            }
        }
        return matrix;
    }

    private static int[][] getMatrix() {
        int[][] matrix = new int[][]{
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {19, 18, 17, 16, 15, 14, 13, 12, 11, 10},
                {20, 21, 22, 23, 24, 25, 26, 27, 28, 29},
                {39, 38, 37, 36, 35, 34, 33, 32, 31, 30},
                {40, 41, 42, 43, 44, 45, 46, 47, 48, 49},
                {59, 58, 57, 56, 55, 54, 53, 52, 51, 50},
                {60, 61, 62, 63, 64, 65, 66, 67, 68, 69},
                {79, 78, 77, 76, 75, 74, 73, 72, 71, 70},
                {80, 81, 82, 83, 84, 85, 86, 87, 88, 89},
                {99, 98, 97, 96, 95, 94, 93, 92, 91, 90},
                {100, 101, 102, 103, 104, 105, 106, 107, 108, 109},
                {119, 118, 117, 116, 115, 114, 113, 112, 111, 110},
                {120, 121, 122, 123, 124, 125, 126, 127, 128, 129},
                {139, 138, 137, 136, 135, 134, 133, 132, 131, 130},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        return matrix;
    }
}
