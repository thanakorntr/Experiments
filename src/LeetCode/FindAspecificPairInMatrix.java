package LeetCode;

/**
 * Given an n x n matrix mat[n][n] of integers,
 * find the maximum value of mat(c, d) – mat(a, b) over all choices of indexes such
 * that both c > a and d > b.

 Example:

 Input:
 mat[N][N] = {{ 1, 2, -1, -4, -20 },
 { -8, -3, 4, 2, 1 },
 { 3, 8, 6, 1, 3 },
 { -4, -1, 1, 7, -6 },
 { 0, -4, 10, -5, 1 }};
 Output: 18
 The maximum value is 18 as mat[4][2]
 - mat[1][0] = 18 has maximum difference.
 The program should do only ONE traversal of the matrix. i.e. expected time complexity is O(n2)
 *
 * Created by Thanakorn on 5/15/16.
 */
public class FindAspecificPairInMatrix {

    public static void main(String[] args) {

        int[][] matrix = new int[][] {
                    { 1, 2, -1, -4, -20 },
                    { -8, -3, 4, 2, 1 },
                    { 3, 8, 6, 1, 3 },
                    { -4, -1, 1, 7, -6 },
                    { 0, -4, 10, -5, 1 }
                };

        System.out.println(findMinPair(matrix));  // 18

    }

    private static int findMinPair(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException();
        }

        int maxVal = Integer.MIN_VALUE;

        int[][] minMemo = new int[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int leftColIndex = col - 1;
                int upperRowIndex = row - 1;
                int minLeft = leftColIndex >= 0 ? minMemo[row][leftColIndex] : matrix[row][col];
                int minUpper = upperRowIndex >= 0 ? minMemo[upperRowIndex][col] : matrix[row][col];
                minMemo[row][col] = Math.min(matrix[row][col], Math.min(minLeft, minUpper));
                if (leftColIndex >= 0 && upperRowIndex >= 0) {
                    maxVal = Math.max(maxVal, matrix[row][col] - minMemo[upperRowIndex][leftColIndex]);
                }
            }
        }

        return maxVal;
    }

}
