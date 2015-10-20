package LeetCode;

import java.util.Arrays;

/**
 * Created by Thanakorn on 10/18/15.
 *
 * http://www.geeksforgeeks.org/find-the-longest-path-in-a-matrix-with-given-constraints/
 *
 * Input:  mat[][] = {{1, 2, 9}
                      {5, 3, 8}
                      {4, 6, 7}}
 Output: 4
 The longest path is 6-7-8-9.
 */
public class LongestPathInMatrixWithConstraint {


    public static void main(String[] args) {

        int[][] mat = new int[][] {
                                    {1,2,9},
                                    {5,3,8},
                                    {4,6,7}
                                  };

        System.out.println(longestPath(mat));

    }

    private static int longestPath(int[][] mat) {

        if (mat == null) {
            return -1;
        }

        final int ROW = mat.length;
        final int COL = mat[0].length;

        if (ROW == 0 || COL == 0) {
            return -1;
        }

        int[][] memoization = new int[ROW][COL];
        for (int row = 0; row < ROW; row++) {
            Arrays.fill(memoization[row], -1);
        }

        int maxLen = Integer.MIN_VALUE;

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (memoization[row][col] == -1) {
                    memoization[row][col] = longestPathHelper(mat, memoization, row, col);
                }
                maxLen = Math.max(maxLen, memoization[row][col]);
            }
        }

        return maxLen;
    }

    private static int longestPathHelper(int[][] mat, int[][] memoization, int row, int col) {

        // check left
        if (col-1 >= 0 && mat[row][col] + 1 == mat[row][col-1]) {
            if (memoization[row][col-1] == -1) {
                memoization[row][col-1] = longestPathHelper(mat, memoization, row, col);
            }
            return 1 + memoization[row][col-1];
        }

        // check right
        if (col+1 < mat[row].length && mat[row][col] + 1 == mat[row][col+1]) {
            if (memoization[row][col+1] == -1) {
                memoization[row][col+1] = longestPathHelper(mat, memoization, row, col+1);
            }
            return 1 + memoization[row][col+1];
        }

        // check up
        if (row-1 >= 0 && mat[row][col] + 1 == mat[row-1][col]) {
            if (memoization[row-1][col] == -1) {
                memoization[row-1][col] = longestPathHelper(mat, memoization, row-1, col);
            }
            return 1 + memoization[row-1][col];
        }

        // check bottom
        if (row+1 < mat.length && mat[row][col] + 1 == mat[row+1][col]) {
            if (memoization[row+1][col] == -1) {
                memoization[row+1][col] = longestPathHelper(mat, memoization, row+1, col);
            }
            return 1+ memoization[row+1][col];
        }

        return 1;
    }
}
