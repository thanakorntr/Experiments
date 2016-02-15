package LeetCode;

import java.util.Arrays;

/**
 * You are given a m x n 2D grid initialized with these three possible values.
 * <p>
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you
 * may assume that the distance to a gate is less than2147483647.
 * <p>
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate,
 * it should be filled with INF.
 * <p>
 * For example, given the 2D grid:
 * <p>
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 * 0  -1 INF INF
 * <p>
 * After running your function, the 2D grid should be:
 * <p>
 * 3  -1   0   1
 * 2   2   1  -1
 * 1  -1   2  -1
 * 0  -1   3   4
 * <p>
 * <p>
 * <p>
 * Created by Thanakorn on 2/15/16.
 */
public class WallsAndGates {

    private static final int INF = Integer.MAX_VALUE;

    private static int[][] diffPoints = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {

        int[][] grid = new int[][]{
                {INF, -1, 0, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, -1},
                {0, -1, INF, INF}
        };

        wallsAndGates(grid);

        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }

    }

    private static void wallsAndGates(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException();
        }

        final int ROW = grid.length;
        final int COL = grid[0].length;

        boolean[][] visited = new boolean[ROW][COL];

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (grid[row][col] == INF) {
                    wallsAndGatesHelper(grid, row, col, visited);
                }
            }
        }
    }

    private static void wallsAndGatesHelper(int[][] grid, int row, int col, boolean[][] visited) {

        visited[row][col] = true;
        int minDistance = INF;

        for (int[] diff : diffPoints) {
            int newRow = row + diff[0];
            int newCol = col + diff[1];
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length
                    && !visited[newRow][newCol]) {
                if (grid[newRow][newCol] == 0) {
                    minDistance = 1;
                    break;
                } else if (grid[newRow][newCol] != -1 &&
                           grid[newRow][newCol] != 0 &&
                           grid[newRow][newCol] != INF) {
                    minDistance = Math.min(minDistance, 1 + grid[newRow][newCol]);
                } else if (grid[newRow][newCol] == INF) {
                    wallsAndGatesHelper(grid, newRow, newCol, visited);
                    if (grid[newRow][newCol] != INF) {
                        minDistance = Math.min(minDistance, 1 + grid[newRow][newCol]);
                    }
                }
            }
        }

        grid[row][col] = minDistance;
        visited[row][col] = false;
    }

}
