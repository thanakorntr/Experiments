package LeetCode;

/**
 * Created by Thanakorn on 11/11/15.
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined
 * by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Given matrix = [
 [3, 0, 1, 4, 2],
 [5, 6, 3, 2, 1],
 [1, 2, 0, 1, 5],
 [4, 1, 0, 1, 7],
 [1, 0, 3, 0, 5]
 ]

 sumRegion(2, 1, 4, 3) -> 8
 sumRegion(1, 1, 2, 2) -> 11
 sumRegion(1, 2, 2, 4) -> 12
 Note:
 You may assume that the matrix does not change.
 There are many calls to sumRegion function.
 You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class NumMatrix {

    private int[][] accumulatedSum;

    public NumMatrix(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        final int ROW = matrix.length;
        final int COL = matrix[0].length;

        accumulatedSum = new int[matrix.length][matrix[0].length];

        for (int row = 0; row < ROW; row++) {
            if (row == 0) {
                accumulatedSum[row][0] = matrix[row][0];
            } else {
                accumulatedSum[row][0] = accumulatedSum[row - 1][0] + matrix[row][0];
            }
        }

        for (int col = 0; col < COL; col++) {
            if (col == 0) {
                accumulatedSum[0][col] = matrix[0][col];
            } else {
                accumulatedSum[0][col] = accumulatedSum[0][col - 1] + matrix[0][col];
            }
        }

        for (int row = 1; row < ROW; row++) {
            for (int col = 1; col < COL; col++) {
                accumulatedSum[row][col] = accumulatedSum[row - 1][col]
                                            + accumulatedSum[row][col - 1]
                                            - accumulatedSum[row - 1][col - 1]
                                            + matrix[row][col];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        if (accumulatedSum == null) {
            return 0;
        }

        int leftArea = 0;
        int upperArea = 0;
        int sharedArea = 0;

        int leftCol = col1 - 1;
        if (leftCol >= 0) {
            leftArea = accumulatedSum[row2][leftCol];
        }

        int upperRow = row1 - 1;
        if (upperRow >= 0) {
            upperArea = accumulatedSum[upperRow][col2];
        }

        if (upperRow >= 0 && leftCol >= 0) {
            sharedArea = accumulatedSum[upperRow][leftCol];
        }

        return accumulatedSum[row2][col2] - leftArea - upperArea + sharedArea;
    }


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);
}
