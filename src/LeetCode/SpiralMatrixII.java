package LeetCode;

/**
 * Created by Thanakorn on 10/4/15.
 * <p>
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * For example,
 * Given n = 3,
 * <p>
 * You should return the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrixII {

    public enum DIRECTION {UP, DOWN, LEFT, RIGHT};

    public static void main(String[] args) {
        generateMatrix(3);
    }

    private static int[][] generateMatrix(int n) {

        if (n <= 0) {
            return new int[][]{};
        }

        int[][] matrix = new int[n][n];
        int count = 0;
        int total = n * n;
        int curRow = 0, curCol = 0;
        int curTopBound = 1, curRightBound = n-1, curBottomBound = n-1, curLeftBound = 0;
        DIRECTION curDirection = DIRECTION.RIGHT;
        while (count != total) {
            matrix[curRow][curCol] = ++count;
            if (curDirection.equals(DIRECTION.RIGHT)) {
                if (curCol == curRightBound) {
                    curDirection = DIRECTION.DOWN;
                    curRightBound--;
                    curRow++;
                } else {
                    curCol++;
                }
            } else if (curDirection.equals(DIRECTION.DOWN)) {
                if (curRow == curBottomBound) {
                    curDirection = DIRECTION.LEFT;
                    curBottomBound--;
                    curCol--;
                } else {
                    curRow++;
                }
            } else if (curDirection.equals(DIRECTION.LEFT)) {
                if (curCol == curLeftBound) {
                    curDirection = DIRECTION.UP;
                    curLeftBound++;
                    curRow--;
                } else {
                    curCol--;
                }
            } else if (curDirection.equals(DIRECTION.UP)){
                if (curRow == curTopBound) {
                    curDirection = DIRECTION.RIGHT;
                    curTopBound++;
                    curCol++;
                } else {
                    curRow--;
                }
            }
        }

        return matrix;
    }
}
