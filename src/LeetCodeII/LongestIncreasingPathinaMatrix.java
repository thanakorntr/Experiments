package LeetCodeII;

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
 * Created by Thanakorn on 3/27/16.
 */
public class LongestIncreasingPathinaMatrix {

    private int[][] deltas = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}};

    public int longestIncreasingPath(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[][] memo = new int[matrix.length][matrix[0].length];
        for (int row = 0; row < memo.length; row++) {
            for (int col = 0; col < memo[row].length; col++) {
                memo[row][col] = -1;
            }
        }

        int longestIncreasingPath = 1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (memo[row][col] == -1) {
                    dfs(matrix, row, col, memo);
                }
                longestIncreasingPath = Math.max(longestIncreasingPath, memo[row][col]);
            }
        }

        return longestIncreasingPath;
    }

    private void dfs(int[][] matrix, int row, int col, int[][] memo) {

        memo[row][col] = 1;

        for (int[] delta : deltas) {
            int nextRow = row + delta[0];
            int nextCol = col + delta[1];
            if (nextRow >= 0 && nextRow < matrix.length
                 && nextCol >= 0 && nextCol < matrix[0].length) {
                if (matrix[nextRow][nextCol] > matrix[row][col]) {
                    if (memo[nextRow][nextCol] == -1) {
                        dfs(matrix, nextRow, nextCol, memo);
                    }
                    memo[row][col] = Math.max(memo[row][col], 1 + memo[nextRow][nextCol]);
                }
            }
        }

    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {9,9,4},
                {6,6,8},
                {2,1,1}
        };
        int len = new LongestIncreasingPathinaMatrix().longestIncreasingPath(matrix);
        System.out.println(len);
    }
}
