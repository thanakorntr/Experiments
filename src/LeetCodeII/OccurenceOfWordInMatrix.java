package LeetCodeII;

/**
 * Created by Thanakorn on 5/13/16.
 */
public class OccurenceOfWordInMatrix {

    public static void main(String[] args) {

        String word = "HELLO";
        char[][] matrix = new char[][]{
                {'H', 'E', 'L', 'L', 'O'},
                {'E', 'H', 'E', 'A', 'B'},
                {'L', 'L', 'O', 'C', 'D'},
        };

        System.out.println(countOccurence(matrix, word));  // 5
    }

    private static int[][] diffs = new int[][]{
            {-1, 0}, {1, 0}, {0, 1}, {0, -1},
            {-1, 1}, {1, -1}, {1, 1}, {1, -1}};

    private static int countOccurence(char[][] matrix, String word) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0
                || word == null || word.isEmpty()) {
            return 0;
        }

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        int count = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (word.charAt(0) == matrix[row][col]) {
                    count += countOccurenceHelper(matrix, word, row, col, 0, visited);
                }
            }
        }

        return count;
    }

    private static int countOccurenceHelper(char[][] matrix, String word,
                                            int curRow, int curCol,
                                            int startIndex, boolean[][] visited) {

        if (startIndex == word.length() - 1) {
            return word.charAt(startIndex) == matrix[curRow][curCol] ? 1 : 0;
        }

        if (word.charAt(startIndex) != matrix[curRow][curCol]
                || visited[curRow][curCol]) {
            return 0;
        }

        visited[curRow][curCol] = true;

        int count = 0;

        for (int[] diff : diffs) {
            int newRow = curRow + diff[0];
            int newCol = curCol + diff[1];
            if (newRow >= 0 && newRow < matrix.length
                    && newCol >= 0 && newCol < matrix[newRow].length
                    && !visited[newRow][newCol]) {
                count += countOccurenceHelper(matrix, word, newRow, newCol, startIndex + 1, visited);
            }
        }

        visited[curRow][curCol] = false;
        return count;
    }

}
