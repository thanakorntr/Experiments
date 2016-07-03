package LeetCode;

import java.util.TreeSet;

/**
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such
 * that its sum is no larger than k.

 Example:
 Given matrix = [
 [1,  0, 1],
 [0, -2, 3]
 ]
 k = 2
 The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger
 than k (k = 2).

 Note:
 The rectangle inside the matrix must have an area > 0.
 What if the number of rows is much larger than the number of columns?
 *
 * Created by Thanakorn on 7/2/16.
 */
public class MaxSumOfRectangleNoLargerThanK {

    public static void main(String[] args) {
        MaxSumOfRectangleNoLargerThanK maxSum = new MaxSumOfRectangleNoLargerThanK();
        int[][] matrix = new int[][] {
                                      {1,0,1},
                                      {0,-2,3}
                                     };
        int k = 2;
        System.out.println(maxSum.maxSumSubmatrix(matrix, k));  // 2

        matrix = new int[][]{
                             {2,2,-1}
                            };
        k = 0;
        System.out.println(maxSum.maxSumSubmatrix(matrix, k)); // -1

        matrix = new int[][]{
                             {5,-4,-3,4},
                             {-3,-4,4,5},
                             {5,1,5,-4}
                            };
        k = 8;
        System.out.println(maxSum.maxSumSubmatrix(matrix, k));  // 8
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[][] accRowSum = new int[matrix.length][matrix[0].length];
        for (int col = 0; col < matrix[0].length; col++) {
            int sum = 0;
            for (int row = 0; row < matrix.length; row++) {
                sum += matrix[row][col];
                accRowSum[row][col] = sum;
            }
        }

        int globalMax = Integer.MIN_VALUE;

        for (int r1 = 0; r1 < matrix.length; r1++) {
            for (int r2 = r1; r2 < matrix.length; r2++) {
                int sum = getArea(accRowSum, r1, r2, 0);
                if (sum <= k) {
                    globalMax = Math.max(globalMax, sum);
                }
                TreeSet<Integer> treeSet = new TreeSet<>();
                treeSet.add(sum);
                for (int col = 1; col < matrix[0].length; col++) {
                    sum += getArea(accRowSum, r1, r2, col);
                    // sum - x <= k --> x >= sum - k
                    // find smallest value x that is large or equal to sum - k
                    Integer x = treeSet.ceiling(sum - k);
                    if (x != null) {
                        int area = sum - x;
                        globalMax = Math.max(globalMax, area);
                    }
                    if (sum <= k) {
                        globalMax = Math.max(globalMax, sum);
                    }
                    treeSet.add(sum);
                }
            }
        }

        return globalMax;
    }

    private int getArea(int[][] accRowSum, int r1, int r2, int col) {
        if (r1 == 0) {
            return accRowSum[r2][col];
        }
        return accRowSum[r2][col] - accRowSum[r1 - 1][col];
    }

}
