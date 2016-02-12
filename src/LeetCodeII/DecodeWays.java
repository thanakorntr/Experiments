package LeetCodeII;

/**
 *  A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26

 Given an encoded message containing digits, determine the total number of ways to decode it.

 For example,
 Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

 The number of ways decoding "12" is 2.
 *
 * Created by Thanakorn on 2/11/16.
 */
public class DecodeWays {

    public int numDecodings(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }

        char[] charArray = s.toCharArray();
        int[] dp = new int[s.length()];
        dp[0] = 1;
        if (isValid(charArray, 1, 1)) {
            if (isValid(charArray, 0, 1)) {
                dp[1] = 2;
            } else {
                dp[1] = 1;
            }
        } else {
            if (isValid(charArray, 0, 1)) {
                dp[1] = 1;
            } else {
                return 0;
            }
        }

        for (int i = 2; i < s.length(); i++) {
            int curDp = 0;
            if (isValid(charArray, i, i)) {
                curDp += dp[i - 1];
            }
            if (isValid(charArray, i - 1, i)) {
                curDp += dp[i - 2];
            }
            if (curDp == 0) {
                return 0;
            }
            dp[i] = curDp;
        }

        return dp[s.length() - 1];
    }

    private boolean isValid(char[] charArray, int start, int end) {
        if (charArray == null || charArray.length == 0 || start > end) {
            throw new IllegalArgumentException();
        }
        if (start == end) {
            return charArray[start] > '0' && charArray[start] <= '9';
        } else if (end == start + 1) {
            if (charArray[start] == '0') {
                return false;
            } else if (charArray[start] > '0' && charArray[start] <= '1') {
                return true;
            } else if (charArray[start] == '2' && charArray[end] <= '6') {
                return true;
            } else {
                return false;
            }
        }
        throw new IllegalArgumentException();
    }
}
