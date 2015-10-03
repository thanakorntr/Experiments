package LeetCode;

/**
 * Created by Thanakorn on 10/3/15.
 *
 *  Given two words word1 and word2, find the minimum number of steps required to
 *  convert word1 to word2. (each operation is counted as 1 step.)

 You have the following 3 operations permitted on a word:

 a) Insert a character
 b) Delete a character
 c) Replace a character
 */
public class EditDistance {

    public static void main(String[] args) {

        String word1 = "plasma";
        String word2 = "altruism";
        System.out.println(minDistance(word1, word2));

        word1 = "sea";
        word2 = "eat";
        System.out.println(minDistance(word1, word2));
    }

    private static int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }

        if (word1.isEmpty() || word2.isEmpty()) {
            return Math.max(word1.length(), word2.length());
        }

        int word1Len = word1.length();
        int word2Len = word2.length();
        int[][] minDistanceMatrix = new int[word1Len+1][word2Len+1];

        minDistanceMatrix[0][0] = word1.charAt(0) == word2.charAt(0) ? 0 : 1;
        for (int row = 0; row < word1Len+1; row++) {
            minDistanceMatrix[row][0] = row;
        }
        for (int col = 0; col < word2Len+1; col++) {
            minDistanceMatrix[0][col] = col;
        }

        for (int row = 1; row < word1Len+1; row++) {
            for (int col = 1; col < word2Len+1; col++) {
                int replacementCost = word1.charAt(row-1) == word2.charAt(col-1) ?
                        minDistanceMatrix[row-1][col-1] : minDistanceMatrix[row-1][col-1] + 1;
                int insertionCost = minDistanceMatrix[row-1][col] + 1;
                int deletionCost = minDistanceMatrix[row][col-1] + 1;
                minDistanceMatrix[row][col] = Math.min(replacementCost, Math.min(insertionCost, deletionCost));
            }
        }

        return minDistanceMatrix[word1Len][word2Len];
    }
}
