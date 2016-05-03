package LeetCodeII;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s, partition s such that every substring of the partition
 * is a palindrome.

 Return all possible palindrome partitioning of s.

 For example, given s = "aab",
 Return

 [
 ["aa","b"],
 ["a","a","b"]
 ]
 *
 * Created by Thanakorn on 4/14/16.
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();
        String s = "aab";
        List<List<String>> palindromes = palindromePartitioning.partition(s);
        for (List<String> palindrome : palindromes) {
            System.out.println(palindrome.toString());
        }
    }

    public List<List<String>> partition(String s) {
        if (s == null || s.isEmpty()) {
            return new ArrayList<>();
        }
        boolean[][] palindromeLookup = getPalindromeMatrix(s);
        Map<String, List<List<String>>> memo = new HashMap<>();
        return partitionHelper(s, 0, s.length() - 1, palindromeLookup, memo);
    }

    private List<List<String>> partitionHelper(String originalString,
                                               int startIndex,
                                               int endIndex,
                                               boolean[][] palindromeLookup,
                                               Map<String, List<List<String>>> memo) {

        String string = originalString.substring(startIndex, endIndex + 1);

        if (memo.containsKey(string)) {
            return memo.get(string);
        }

        List<List<String>> partition = new ArrayList<>();
        if (string.length() == 1) {
            List<String> singleList = new ArrayList<>();
            singleList.add(string);
            partition.add(singleList);
            memo.put(string, partition);
            return partition;
        }

        if (palindromeLookup[startIndex][endIndex]) {
            List<String> singleList = new ArrayList<>();
            singleList.add(string);
            partition.add(singleList);
        }

        for (int endLeft = startIndex; endLeft < endIndex; endLeft++) {
            if (palindromeLookup[startIndex][endLeft]) {
                String rightPart = originalString.substring(endLeft + 1, endIndex + 1);
                if (!memo.containsKey(rightPart)) {
                    memo.put(rightPart, partitionHelper(originalString, endLeft + 1, endIndex, palindromeLookup, memo));
                }
                List<List<String>> rightPalindrome = memo.get(rightPart);
                if (!rightPalindrome.isEmpty()) {
                    String leftPart = originalString.substring(startIndex, endLeft + 1);
                    for (List<String> subRight : rightPalindrome) {
                        List<String> subSol = new ArrayList<>();
                        subSol.add(leftPart);
                        subSol.addAll(subRight);
                        partition.add(subSol);
                    }
                }
            }
        }

        memo.put(string, partition);
        return partition;
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
