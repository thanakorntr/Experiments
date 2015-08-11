package LeetCode;

/**
 * Created by Thanakorn on 6/2/15.
 */
public class SearchMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{ {1,3} };
        System.out.println(searchMatrix(matrix, 2));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }

        int upperRow = 0;
        int lowerRow = matrix.length - 1;
        int midRow;

        while (true) {
            midRow = (upperRow + lowerRow) / 2;

            if (matrix[midRow][0] <= target && matrix[midRow][matrix[midRow].length-1] >= target) {
                int left = 0;
                int right = matrix[midRow].length - 1;
                int midCol;
                while (true) {
                    midCol = (left + right) / 2;

                    if (matrix[midRow][midCol] == target) {
                        return true;
                    } else if (matrix[midRow][midCol] > target) {
                        right = midCol - 1;
                        if (left > right) {
                            return false;
                        }
                    } else {
                        left = midCol + 1;
                        if (left > right) {
                            return false;
                        }
                    }
                }

            } else if (matrix[midRow][0] > target) {
                lowerRow = midRow - 1;
                if (upperRow > lowerRow) {
                    break;
                }
            } else {
                upperRow = midRow + 1;
                if (upperRow > lowerRow) {
                    break;
                }
            }
        }

        return false;
    }
}
