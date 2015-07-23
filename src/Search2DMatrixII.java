/**
 * Created by Thanakorn on 7/22/15.
 */
public class Search2DMatrixII {

    public static void main(String[] args) {

        int[][] matrix = {
                            {1,   4,  7, 11, 15},
                            {2,   5,  8, 12, 19},
                            {3,   6,  9, 16, 22},
                            {10, 13, 14, 17, 24},
                            {18, 21, 23, 26, 30}
                         };

        System.out.println(searchMatrix(matrix, 5));  // true
        System.out.println(searchMatrix(matrix, 20)); // false

    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) return false;

        final int ROW = matrix.length;
        if (ROW == 0) return false;
        final int COL = matrix[0].length;

        if (ROW <= COL) {
            for (int row = 0; row < ROW; row++) {
                if (target >= matrix[row][0] && target <= matrix[row][COL-1]) {
                    if (target == matrix[row][0] || target == matrix[row][COL-1]) {
                        return true;
                    }
                    int startCol = 0;
                    int endCol = COL-1;
                    while (startCol <= endCol) {
                        int midCol = (startCol + endCol) / 2;
                        if (matrix[row][midCol] == target) {
                            return true;
                        } else if (matrix[row][midCol] < target) {
                            startCol = midCol + 1;
                        } else {
                            endCol = midCol - 1;
                        }
                    }
                }
            }
            return false;
        } else {
            for (int col = 0; col < COL; col++) {
                if (target >= matrix[0][col] && target <= matrix[ROW-1][col]) {
                    if (target == matrix[0][col] || target == matrix[ROW-1][col]) {
                        return true;
                    }
                    int startRow = 0;
                    int endRow = ROW-1;
                    while (startRow <= endRow) {
                        int midRow = (startRow + endRow) / 2;
                        if (matrix[midRow][col] == target) {
                            return true;
                        } else if (matrix[midRow][col] < target) {
                            startRow = midRow + 1;
                        } else {
                            endRow = midRow - 1;
                        }
                    }
                }
            }
            return false;
        }
    }
}
