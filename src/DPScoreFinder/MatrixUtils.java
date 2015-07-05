package DPScoreFinder;

/**
 * Created by Thanakorn on 6/3/15.
 */
public class MatrixUtils {

    public static double[][] generateRandomDoubleMatrix(int row, int col) {
        double[][] matrix = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = Math.random();
            }
        }

        return matrix;
    }

    public static double[][] generateRandomIntMatrix(int row, int col) {
        double[][] matrix = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = (int)(Math.random() * 10);
            }
        }

        return matrix;
    }

    public static double[][] getTranspose(double[][] matrix) {
        if (matrix == null) {
            return null;
        }

        final int ROW = matrix.length;
        final int COL = matrix[0].length;

        double[][] transpose = new double[COL][ROW];
        int newRow = 0, newCol = 0;

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                transpose[newRow][newCol] = matrix[row][col];
                newRow++;
            }
            newRow = 0;
            newCol++;
        }

        return transpose;
    }

    public static void printMatrix(double[][] matrix) {
        if (matrix == null) {
            return;
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
