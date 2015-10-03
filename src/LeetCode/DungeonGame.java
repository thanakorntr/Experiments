package LeetCode;

/**
 * Created by Thanakorn on 10/3/15.
 */
public class DungeonGame {

    public static void main(String[] args) {

        int[][] dungeon = new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };

        System.out.println(calculateMinimumHP(dungeon));  // 7
    }

    private static int calculateMinimumHP(int[][] dungeon) {

        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }

        final int ROW = dungeon.length, COL = dungeon[0].length;

        int[][] dungeonMatrix = new int[ROW][COL];
        if (dungeon[ROW - 1][COL - 1] < 0) {
            dungeonMatrix[ROW - 1][COL - 1] = Math.abs(dungeon[ROW - 1][COL - 1]) + 1;
        } else {
            dungeonMatrix[ROW - 1][COL - 1] = 1;
        }

        for (int col = COL - 2; col >= 0; col--) {
            int minHealth = dungeonMatrix[ROW - 1][col + 1] - dungeon[ROW - 1][col];
            if (minHealth > 0) {
                dungeonMatrix[ROW - 1][col] = minHealth;
            } else {
                dungeonMatrix[ROW - 1][col] = 1;
            }
        }
        for (int row = ROW - 2; row >= 0; row--) {
            int minHealth = dungeonMatrix[row + 1][COL - 1] - dungeon[row][COL - 1];
            if (minHealth > 0) {
                dungeonMatrix[row][COL - 1] = minHealth;
            } else {
                dungeonMatrix[row][COL - 1] = 1;
            }
        }

        for (int row = ROW - 2; row >= 0; row--) {
            for (int col = COL - 2; col >= 0; col--) {
                int minHealthRight = dungeonMatrix[row][col + 1] - dungeon[row][col];
                if (minHealthRight <= 0) {
                    minHealthRight = 1;
                }
                int minHealthDown = dungeonMatrix[row + 1][col] - dungeon[row][col];
                if (minHealthDown <= 0) {
                    minHealthDown = 1;
                }
                dungeonMatrix[row][col] = Math.min(minHealthRight, minHealthDown);
            }
        }

        return dungeonMatrix[0][0];

    }
}
