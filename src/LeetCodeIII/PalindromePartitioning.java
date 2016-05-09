package LeetCodeIII;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * * Given a string s, partition s such that every substring of the partition
 * is a palindrome.

 Return all possible palindrome partitioning of s.

 For example, given s = "aab",
 Return

 [
 ["aa","b"],
 ["a","a","b"]
 ]
 *
 * Created by Thanakorn on 5/9/16.
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
        Map<Integer, List<List<String>>> memo = new HashMap<>();
        return partitionHelper(s, 0, palindromeLookup, memo);
    }

    private List<List<String>> partitionHelper(String originalString,
                                               int startIndex,
                                               boolean[][] lookupTable,
                                               Map<Integer, List<List<String>>> memo) {

        if (memo.containsKey(startIndex)) {
            return memo.get(startIndex);
        }

        List<List<String>> partitions = new ArrayList<>();

        if (startIndex == originalString.length() - 1) {
            List<String> singleList = new ArrayList<>();
            singleList.add(originalString.substring(startIndex));
            partitions.add(singleList);
            memo.put(startIndex, partitions);
            return partitions;
        }

        if (lookupTable[startIndex][originalString.length() - 1]) {
            List<String> singleList = new ArrayList<>();
            singleList.add(originalString.substring(startIndex));
            partitions.add(singleList);
        }

        for (int endIndex = startIndex; endIndex < originalString.length() - 1; endIndex++) {
            if (lookupTable[startIndex][endIndex]) {
                if (!memo.containsKey(endIndex + 1)) {
                    memo.put(endIndex + 1, partitionHelper(originalString, endIndex + 1, lookupTable, memo));
                }
                List<List<String>> rightPartitions = memo.get(endIndex + 1);
                if (!rightPartitions.isEmpty()) {
                    String leftSubstring = originalString.substring(startIndex, endIndex + 1);
                    for (List<String> rightPartition : rightPartitions) {
                        List<String> combined = new ArrayList<>();
                        combined.add(leftSubstring);
                        combined.addAll(rightPartition);
                        partitions.add(combined);
                    }
                }
            }
        }

        memo.put(startIndex, partitions);
        return partitions;
    }

    private boolean[][] getPalindromeMatrix(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException();
        }
        boolean[][] lookupTable = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = s.length() - 1; j >= i; j--) {
                if (i == j) {
                    lookupTable[i][j] = true;
                } else if (i + 1 == j) {
                    lookupTable[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    lookupTable[i][j] = s.charAt(i) == s.charAt(j) && lookupTable[i + 1][j - 1];
                }
            }
        }
        return lookupTable;
    }

}
