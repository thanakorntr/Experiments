package LeetCodeII;

/**
 *  Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

 click to show follow up.
 Follow up:

 Did you use extra space?
 A straight forward solution using O(mn) space is probably a bad idea.
 A simple improvement uses O(m + n) space, but still not the best solution.
 Could you devise a constant space solution?

 *
 * Created by Thanakorn on 2/19/16.
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        final int ROWS = matrix.length;
        final int COLS = matrix[0].length;

        boolean isFirstColZero = false;

        for (int row = 0; row < ROWS; row++) {
            if (matrix[row][0] == 0) {
                isFirstColZero = true;
            }
            for (int col = 1; col < COLS; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        for (int row = ROWS - 1; row >= 0; row--) {
            for (int col = COLS - 1; col >= 1; col--) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
            if (isFirstColZero) {
                matrix[row][0] = 0;
            }
        }

    }

}
