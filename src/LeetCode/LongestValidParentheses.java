package LeetCode;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * <p>
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 * <p>
 * Created by Thanakorn on 5/21/16.
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        String str = ")()())";
        System.out.println(longestValidParentheses.longestValidParentheses(str));  // 4
    }

    public int longestValidParentheses(String s) {
        return longestValidParenthesesOptimized(s);
    }

    public int longestValidParenthesesOptimized(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        int[] dp = new int[s.length()];
        dp[0] = 0;
        dp[1] = s.charAt(0) == '(' && s.charAt(1) == ')' ? 2 : 0;
        int longest = dp[1];
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else {  // s.charAt(i - 1) == ')'
                    int prevIndex = i - dp[i - 1] - 1;
                    if (prevIndex >= 0) {
                        if (s.charAt(prevIndex) == '(') {
                            if (prevIndex - 1 >= 0) {
                                dp[i] = dp[i - 1] + 2 + dp[prevIndex - 1];
                            } else {
                                dp[i] = dp[i - 1] + 2;
                            }
                        }
                    }
                }

                longest = Math.max(longest, dp[i]);
            }
        }

        return longest;
    }

    public int longestValidParenthesesNsquare(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int longestParenLen = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (longestParenLen > s.length() - i) {
                break;
            }
            if (s.charAt(i) == ')') {
                continue;
            }
            int count = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == '(') {
                    count++;
                } else {
                    count--;
                }
                if (count == 0) {
                    longestParenLen = Math.max(longestParenLen, j - i + 1);
                } else if (count < 0) {
                    break;
                }
            }
        }

        return longestParenLen;
    }

}
