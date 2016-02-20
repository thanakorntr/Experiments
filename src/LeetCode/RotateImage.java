package LeetCode;

/**
 * You are given an n x n 2D matrix representing an image.
 * <p>
 * Rotate the image by 90 degrees (clockwise).
 * <p>
 * Follow up:
 * Could you do this in-place?
 * <p>
 * Created by Thanakorn on 2/20/16.
 */
public class RotateImage {

    public void rotate(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = row; col < matrix[row].length; col++) {
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = tmp;
            }
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length / 2; col++) {
                int tmp = matrix[row][col];
                int mirrowIndex = matrix[row].length - 1 - col;
                matrix[row][col] = matrix[row][mirrowIndex];
                matrix[row][mirrowIndex] = tmp;
            }
        }
    }

}
