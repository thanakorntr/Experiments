package LeetCode;

/**
 * Created by Thanakorn on 5/24/16.
 */
public class AndroidUnlockPatterns {

    public static void main(String[] args) {
        AndroidUnlockPatterns androidUnlockPatterns = new AndroidUnlockPatterns();
        System.out.println(androidUnlockPatterns.numberOfPatterns(1, 2));
    }

    public int numberOfPatterns(int m, int n) {
        boolean[][] visited = new boolean[3][3];
        int count = 0;
        for (int lineLen = m; lineLen <= n; lineLen++) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    count += dfs(row, col, 1, lineLen, visited);
                }
            }
        }
        return count;
    }

    private int dfs(int row, int col, int curLen, int lineLen, boolean[][] visited) {
        if (curLen == lineLen) {
            visited[row][col] = false;
            return 1;
        }

        visited[row][col] = true;
        int count = 0;

        for (int nextRow = 0; nextRow < 3; nextRow++) {
            for (int nextCol = 0; nextCol < 3; nextCol++) {
                if (!visited[nextRow][nextCol]) {
                    if (Math.abs(nextRow - row) == 2 || Math.abs(nextCol - col) == 2) {
                        if (Math.abs(nextRow - row) == 2 && Math.abs(nextCol - col) == 2) {
                            if (visited[1][1]) {
                                count += dfs(nextRow, nextCol, curLen + 1, lineLen, visited);
                            }
                        } else if (Math.abs(nextRow - row) == 2) {
                            if (Math.abs(nextCol - col) == 1) {
                                count += dfs(nextRow, nextCol, curLen + 1, lineLen, visited);
                            } else {
                                if (visited[1][col]) {
                                    count += dfs(nextRow, nextCol, curLen + 1, lineLen, visited);
                                }
                            }
                        } else if (Math.abs(nextCol - col) == 2) {
                            if (Math.abs(nextRow - row) == 1) {
                                count += dfs(nextRow, nextCol, curLen + 1, lineLen, visited);
                            } else {
                                if (visited[row][1]) {
                                    count += dfs(nextRow, nextCol, curLen + 1, lineLen, visited);
                                }
                            }
                        }
                    } else {
                        count += dfs(nextRow, nextCol, curLen + 1, lineLen, visited);
                    }
                }
            }
        }

        visited[row][col] = false;
        return count;
    }

}
