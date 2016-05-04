package LeetCodeII;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.

 Return the minimum cuts needed for a palindrome partitioning of s.

 For example, given s = "aab",
 Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 * Created by Thanakorn on 5/3/16.
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
        int[] dp = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            dp[i] = i;
            for (int j = i; j >= 0; j--) {
                if (j == 0 && palindromTable[j][i]) {
                    dp[i] = 0;
                    break;
                }
                if (palindromTable[j][i]) {
                    if (j - 1 >= 0 && dp[j - 1] + 1 < dp[i]) {
                        dp[i] = dp[j - 1] + 1;
                    }
                }
            }
        }

        return dp[s.length() - 1];
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
