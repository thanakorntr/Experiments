package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanakorn on 10/5/15.
 *
 * Given a pattern and a string str, find if str follows the same pattern.

 Examples:

 pattern = "abba", str = "dog cat cat dog" should return true.
 pattern = "abba", str = "dog cat cat fish" should return false.
 pattern = "aaaa", str = "dog cat cat dog" should return false.
 pattern = "abba", str = "dog dog dog dog" should return false.

 Notes:

 Both pattern and str contains only lowercase alphabetical letters.
 Both pattern and str do not have leading or trailing spaces.
 Each word in str is separated by a single space.
 Each letter in pattern must map to a word with length that is at least 1.

 */
public class WordPattern {

    public static void main(String[] args) {

    }

    private static boolean wordPattern(String pattern, String str) {

        if (pattern.isEmpty() && str.isEmpty()) {
            return true;
        }

        if (pattern.isEmpty() || str.isEmpty()) {
            return false;
        }

        String[] strs = str.split(" ");
        if (pattern.length() != strs.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char c = pattern.charAt(i);
            if (!map.containsKey(c)) {
                if (map.containsValue(strs[i])) {
                    return false;
                }
                map.put(c, strs[i]);
            } else {
                if (!map.get(c).equals(strs[i])) {
                    return false;
                }
            }
        }

        return true;
    }
}
