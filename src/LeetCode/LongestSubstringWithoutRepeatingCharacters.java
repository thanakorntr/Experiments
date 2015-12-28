package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanakorn on 7/18/15.
 */

/*      Given a string, find the length of the longest substring without repeating characters.
        For example, the longest substring without repeating letters for "abcabcbb" is "abc",
        which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
*/

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {

        String str = "abcabcbb";
        str = "abba";
        System.out.println(lengthOfLongestSubstring(str));

    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> lastIndex = new HashMap<>();
        int maxLen = 1;
        int startIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (lastIndex.containsKey(curChar)) {
                int lastIndexOfCurChar = lastIndex.get(curChar);
                startIndex = Math.max(startIndex, lastIndexOfCurChar + 1);
            }
            maxLen = Math.max(maxLen, i - startIndex + 1);
            lastIndex.put(curChar, i);
        }

        return maxLen;
    }

    public static int lengthOfLongestSubstring2(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> lastIndex = new HashMap<>();
        int maxLen = 1;
        int startIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (lastIndex.containsKey(curChar)) {
                int lastIndexOfCurChar = lastIndex.get(curChar);
                for (int j = startIndex; j <= lastIndexOfCurChar; j++) {
                    lastIndex.remove(s.charAt(j));
                }
                startIndex = lastIndexOfCurChar + 1;
            }
            maxLen = Math.max(maxLen, i - startIndex + 1);
            lastIndex.put(curChar, i);
        }

        return maxLen;
    }
}
