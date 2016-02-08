package LeetCode;

/**
 * Created by Thanakorn on 2/7/16.
 */
public class DiagonalMatrix {

    public static void main(String[] args) {

        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        printMatrixDiagonal(matrix);
    }

    private static void printMatrixDiagonal(int[][] matrix) {
        for (int row = matrix.length - 1; row >= 0; row--) {
            printMatrixDiagonalHelper(matrix, row, 0);
        }
        for (int col = 1; col < matrix[0].length; col++) {
            printMatrixDiagonalHelper(matrix, 0, col);
        }
    }

    private static void printMatrixDiagonalHelper(int[][] matrix, int startRow, int startCol) {
        while (startRow < matrix.length && startCol < matrix[0].length) {
            System.out.println(matrix[startRow][startCol]);
            startRow++;
            startCol++;
        }
    }

}
