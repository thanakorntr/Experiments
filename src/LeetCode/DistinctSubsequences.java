package LeetCode;

/**
 * Given a string S and a string T, count the number of distinct subsequences of T in S.

 A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

 Here is an example:
 S = "rabbbit", T = "rabbit"

 Return 3.
 *
 * Created by Thanakorn on 5/27/16.
 */
public class DistinctSubsequences {

    public static void main(String[] args) {
        DistinctSubsequences distinctSubsequences = new DistinctSubsequences();
        String s = "rabbbit";
        String t = "rabbit";
        System.out.println(distinctSubsequences.numDistinct(s, t));  // 3
    }

    public int numDistinct(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }

        if (s.isEmpty() || t.isEmpty()) {
            if (s.isEmpty()) {
                return 0;
            } else if (!s.isEmpty()) {
                return s.length();
            }
        }

        int[][] memo = new int[s.length()][t.length()];
        for (int row = 0; row < s.length(); row++) {
            for (int col = 0; col < t.length(); col++) {
                memo[row][col] = -1;
            }
        }

        return numDistinctHelper(s, t, 0, 0, memo);
    }

    private int numDistinctHelper(String s, String t,
                                  int startS, int startT,
                                  int[][] memo) {

        if (memo[startS][startT] != -1) {
            return memo[startS][startT];
        }

        int count = 0;
        for (int i = startS; i < s.length(); i++) {
            if (s.length() - i < t.length() - startT) {
                break;
            }
            if (s.charAt(i) == t.charAt(startT)) {
                int nextStartS = i + 1;
                int nextStartT = startT + 1;
                if (nextStartS >= s.length() || nextStartT >= t.length()) {
                    if (nextStartS >= s.length() && nextStartT >= t.length()) {
                        count++;
                    } else if (nextStartT >= t.length()) {
                        count++;
                    }
                    continue;
                } else {
                    if (memo[nextStartS][nextStartT] == -1) {
                        memo[nextStartS][nextStartT] = numDistinctHelper(s, t, nextStartS, nextStartT, memo);
                    }
                }
                count += memo[nextStartS][nextStartT];
            }
        }

        memo[startS][startT] = count;
        return count;
    }

}
