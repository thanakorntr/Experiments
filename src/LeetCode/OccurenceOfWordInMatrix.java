package LeetCode;

import com.sun.tools.javac.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Thanakorn on 11/15/15.
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

    private static int countOccurence(char[][] matrix, String word) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0
                || word == null || word.isEmpty()) {
            return 0;
        }

        int count = 0;

        char[] wordArray = word.toCharArray();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (wordArray[0] == matrix[row][col]) {
                    Set<Pair<Integer, Integer>> storedIndices = new HashSet<>();
                    count += dfs(row, col, matrix, wordArray, 0, storedIndices);
                }
            }
        }

        return count;
    }

    private static int dfs(int row, int col, char[][] matrix,
                           char[] wordArray, int charIndex, Set<Pair<Integer, Integer>> storedIndices) {

        if (charIndex == wordArray.length - 1) {
            if (wordArray[charIndex] == matrix[row][col]) {
                return 1;
            } else {
                return 0;
            }
        }

        storedIndices.add(new Pair<>(row, col));

        int count = 0;

        // top left
        if (row - 1 >= 0 && col - 1 >= 0
                && wordArray[charIndex + 1] == matrix[row - 1][col - 1]
                && !storedIndices.contains(new Pair<>(row - 1, col - 1))) {
            count += dfs(row - 1, col - 1, matrix, wordArray, charIndex + 1, storedIndices);
        }

        // top middle
        if (row - 1 >= 0
                && wordArray[charIndex + 1] == matrix[row - 1][col]
                && !storedIndices.contains(new Pair<>(row - 1, col))) {
            count += dfs(row - 1, col, matrix, wordArray, charIndex + 1, storedIndices);
        }

        // top right
        if (row - 1 >= 0 && col + 1 < matrix[row].length
                && wordArray[charIndex + 1] == matrix[row - 1][col + 1]
                && !storedIndices.contains(new Pair<>(row - 1, col + 1))) {
            count += dfs(row - 1, col + 1, matrix, wordArray, charIndex + 1, storedIndices);
        }

        // middle left
        if (col - 1 >= 0
                && wordArray[charIndex + 1] == matrix[row][col - 1]
                && !storedIndices.contains(new Pair<>(row, col - 1))) {
            count += dfs(row, col - 1, matrix, wordArray, charIndex + 1, storedIndices);
        }

        // middle right
        if (col + 1 < matrix[row].length
                && wordArray[charIndex + 1] == matrix[row][col + 1]
                && !storedIndices.contains(new Pair<>(row, col + 1))) {
            count += dfs(row, col + 1, matrix, wordArray, charIndex + 1, storedIndices);
        }

        // bottom left
        if (row + 1 < matrix.length && col - 1 >= 0
                && wordArray[charIndex + 1] == matrix[row + 1][col - 1]
                && !storedIndices.contains(new Pair<>(row + 1, col - 1))) {
            count += dfs(row + 1, col - 1, matrix, wordArray, charIndex + 1, storedIndices);
        }

        // bottom middle
        if (row + 1 < matrix.length
                && wordArray[charIndex + 1] == matrix[row + 1][col]
                && !storedIndices.contains(new Pair<>(row + 1, col))) {
            count += dfs(row + 1, col, matrix, wordArray, charIndex + 1, storedIndices);
        }

        // bottom right
        if (row + 1 < matrix.length && col + 1 < matrix[row].length
                && wordArray[charIndex + 1] == matrix[row + 1][col + 1]
                && !storedIndices.contains(new Pair<>(row + 1, col + 1))) {
            count += dfs(row + 1, col + 1, matrix, wordArray, charIndex + 1, storedIndices);
        }

        storedIndices.remove(new Pair<>(row, col));
        return count;
    }
}
