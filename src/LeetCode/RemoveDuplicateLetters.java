package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

 Example:
 Given "bcabc"
 Return "abc"

 Given "cbacdcbc"
 Return "acdb"
 *
 * Created by Thanakorn on 12/24/15.
 */
public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        new RemoveDuplicateLetters().removeDuplicateLetters("abacb");
    }

    public String removeDuplicateLetters(String s) {

        if (s == null || s.isEmpty()) {
            return "";
        }

        Map<Character, Integer> lastIndexOfChars = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastIndexOfChars.put(s.charAt(i), i);
        }

        StringBuilder stringBuilder = new StringBuilder();
        int start = 0;
        int end = findLeastIndex(lastIndexOfChars);
        while (!lastIndexOfChars.isEmpty()) {
            char smallestChar = 'z' + 1;
            for (int j = start; j <= end; j++) {
                if (s.charAt(j) < smallestChar
                        && lastIndexOfChars.containsKey(s.charAt(j))) {
                    smallestChar = s.charAt(j);
                    start = j + 1;
                }
            }

            stringBuilder.append(smallestChar);
            lastIndexOfChars.remove(smallestChar);

            if (s.charAt(end) == smallestChar) {
                end = findLeastIndex(lastIndexOfChars);
            }
        }

        return stringBuilder.toString();
    }

    private int findLeastIndex(Map<Character, Integer> lastIndexOfChars) {
        if (lastIndexOfChars == null || lastIndexOfChars.isEmpty()) {
            return -1;
        }
        int minIndex = Integer.MAX_VALUE;
        for (int index : lastIndexOfChars.values()) {
            minIndex = Math.min(minIndex, index);
        }
        return minIndex;
    }
}
