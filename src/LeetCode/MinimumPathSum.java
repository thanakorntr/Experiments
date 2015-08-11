package LeetCode;

/**
 * Created by Thanakorn on 7/12/15.
 */
public class MinimumPathSum {

    public static void main(String[] args) {

    }

    public static int minPathSum(int[][] grid) {

        int ROW = grid.length;
        if (ROW == 0) {
            return 0;
        }
        int COL = grid[0].length;

        int[] upper = new int[COL];
        int[] lower = new int[COL];
        // base cases
        for (int col = COL-1; col >= 0; col--) {
            if (col == COL-1) {
                lower[col] = grid[ROW - 1][col];
            } else {
                lower[col] = grid[ROW-1][col] + lower[col+1];
            }
        }

        for (int row = ROW-2; row >= 0; row--) {
            for (int col = COL-1; col >= 0; col--) {
                if (col == COL-1) {
                    upper[col] = grid[row][col] + lower[col];
                } else {
                    upper[col] = Math.min(grid[row][col] + lower[col], grid[row][col] + upper[col+1]);
                }
            }
            lower = upper;
        }

        return lower[0];
    }
}
