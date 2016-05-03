package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.

 Return the minimum cuts needed for a palindrome partitioning of s.

 For example, given s = "aab",
 Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 * Created by Thanakorn on 5/1/16.
 */
public class PalindromePartitioningII {

    public static void main(String[] args) {
        PalindromePartitioningII palindromePartitioningII = new PalindromePartitioningII();
        String s = "aab";
        System.out.println(palindromePartitioningII.minCut(s));  // 1

        s = "aaaaaaaaa";
        System.out.println(palindromePartitioningII.minCut(s));  // 0
    }

    public int minCut(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        boolean[][] palindromTable = getPalindromeMatrix(s);
        Map<String, Integer> memo = new HashMap<>();
        return minCutHelper(s, 0, s.length() - 1, palindromTable, memo);
    }

    private int minCutHelper(String s, int startIndex, int endIndex,
                             boolean[][] palindromTable, Map<String, Integer> memo) {

        if (startIndex >= s.length() - 1 || startIndex > endIndex || palindromTable[startIndex][endIndex]) {
            return 0;
        }

        String subStr = s.substring(startIndex, endIndex + 1);

        if (memo.containsKey(subStr)) {
            return memo.get(subStr);
        }

        int minCut = Integer.MAX_VALUE;
        for (int i = startIndex; i < endIndex ; i++) {
            if (palindromTable[startIndex][i]) {
                String subsubStr = s.substring(i + 1, endIndex + 1);
                if (!memo.containsKey(subsubStr)) {
                    memo.put(subsubStr, minCutHelper(s, i + 1, endIndex, palindromTable, memo));
                }
                minCut = Math.min(minCut, 1 + memo.get(subsubStr));
            }
        }

        memo.put(subStr, minCut);
        return minCut;
    }

    private boolean[][] getPalindromeMatrix(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException();
        }

        // palindromTable[i][i] = true
        // palindromTable[i][i+1] = true if s[i] = s[i+1], false otherwise
        // palindromTable[i][j] = true if s[i] = s[j] && palindromTable[i+1][j-1] = true
        boolean[][] palindromTable = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            palindromTable[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            palindromTable[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        for (int i = s.length() - 3; i >= 0; i--) {
            for (int j = s.length() - 1; j >= i + 2; j--) {
                palindromTable[i][j] = s.charAt(i) == s.charAt(j) && palindromTable[i + 1][j - 1];
            }
        }

        return palindromTable;
    }

}
