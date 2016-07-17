package LeetCode;

import java.util.*;

/**
 * Question was "Given a pattern and a string input - find if the string follows
 * the same pattern and return 0 or 1.
 *
 Examples:
 1) Pattern : "abba", input: "redbluebluered" should return 1.
 2) Pattern: "aaaa", input: "asdasdasdasd" should return 1.
 3) Pattern: "aabb", input: "xyzabcxzyabc" should return 0.

 https://discuss.leetcode.com/topic/26750/share-my-java-backtracking-solution
 *
 * Created by Thanakorn on 7/16/16.
 */
public class WordPatternII {

    public static void main(String[] args) {
        String pattern = "abba";
        String input = "redbluebluered";
        System.out.println(isMatch(pattern, input));  // true

        pattern = "aaaa";
        input = "asdasdasdasd";
        System.out.println(isMatch(pattern, input));  // true

        pattern = "aabb";
        input = "xyzabcxzyabc";
        System.out.println(isMatch(pattern, input));  // false
    }

    private static boolean isMatch(String pattern, String input) {
        Map<Character, String> charMap = new HashMap<>();
        return isMatchHelper(pattern, input, charMap);
    }

    private static boolean isMatchHelper(String pattern, String input,
                                         Map<Character, String> charMap) {

        if (pattern.isEmpty() || input.isEmpty()) {
            return pattern.isEmpty() && input.isEmpty();
        }

        char curChar = pattern.charAt(0);
        int countChar = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == curChar) {
                countChar++;
            }
        }
        String newPattern = pattern.replace("" + curChar, "");

        for (int i = 1; i <= input.length() / countChar; i++) {
            String curPattern = input.substring(0, i);
            String newInput = input.replace(curPattern, "");
            if (isMatchHelper(newPattern, newInput, charMap)) {
                return true;
            }
        }

        return false;
    }

}
