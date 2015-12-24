package LeetCode;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thanakorn on 12/5/15.
 */
@RunWith(Parameterized.class)
public class HungarianAlgorithmTest {

    private static final int ROWS = 6;
    private static final int COLS = 6;

    private static final double eps = 0.000001;

    private static final int NUM_TESTCASES = 100;

    @Parameterized.Parameter(value = 0)
    public double[][] testcase;

    @Parameterized.Parameter(value = 1)
    public double expected;

    @org.junit.Test
    public void eval() {
        HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm(testcase);
        int[] m = hungarianAlgorithm.execute();
        double actual = 0;
        for (int i = 0; i < m.length; i++) {
            if (m[i] != -1) {
                actual += testcase[i][m[i]];
            }
        }
        assertEquals(expected, actual, eps);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] testcases = new Object[NUM_TESTCASES][2];
        for (int i = 0; i < NUM_TESTCASES; i++) {
            double[][] matrix = generateRandomMatrix();
            testcases[i][0] = matrix;
            testcases[i][1] = standardDp(matrix);
        }
        return Arrays.asList(testcases);
    }





    private static double standardDp(double[][] matrix) {

        // has 1 row and 1 col
        if (matrix.length == 1 && matrix[0].length == 1) {
            return matrix[0][0];
        }

        // has 1 row
        if (matrix.length == 1) {
            double minVal = Double.MAX_VALUE;
            for (double val : matrix[0]) {
                minVal = Math.min(minVal, val);
            }
            return minVal;
        }

        // has 1 col
        if (matrix[0].length == 1) {
            double minVal = Double.MAX_VALUE;
            for (double[] row : matrix) {
                minVal = Math.min(minVal, row[0]);
            }
            return minVal;
        }

        double minSum = Double.MAX_VALUE;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                double curVal = matrix[row][col] + standardDp(generateSubMatrix(matrix, row, col));
                minSum = Math.min(minSum, curVal);
            }
        }

        return minSum;
    }

    private static double[][] generateSubMatrix(double[][] matrix, int excludedRow, int excludedCol) {

        double[][] subMatrix = new double[matrix.length - 1][matrix[0].length - 1];

        int subRowIndex = 0, subColIndex = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (row != excludedRow && col != excludedCol) {
                    subMatrix[subRowIndex][subColIndex] = matrix[row][col];
                    subColIndex++;
                    if (subColIndex == subMatrix[0].length) {
                        subColIndex = 0;
                        subRowIndex++;
                    }
                }
            }
        }

        return subMatrix;
    }

    private static double[][] generateRandomMatrix() {
        double[][] matrix = new double[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                matrix[row][col] = Math.random();
            }
        }
        return matrix;
    }
}
