package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanakorn on 2/23/16.
 */
public class LongestSubstringWithKuniqueChars {

    public static void main(String[] args) {
        System.out.println(longestSubstringK("aabbcc", 1));
        System.out.println(longestSubstringK("aabbcc", 2));
        System.out.println(longestSubstringK("aaabbb", 3));
        System.out.println(longestSubstringK("eceba", 3));
        System.out.println(longestSubstringK("zxzxzxzxzx cvcvcvcvcvcvcvcvcv", 2));
        System.out.println(longestSubstringK("aaaaaaaa abcabc aaaabbbbaaaabbbbbbabaa", 3));
        System.out.println(longestSubstringK("adabcacdaaaaaaaaaaaabaaaaaaa", 3));
    }

    private static String longestSubstringK(String str, int k) {

        Map<Character, Integer> charCount = new HashMap<>();

        int left = 0;
        String longestSubstring = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!charCount.containsKey(c)) {
                charCount.put(c, 1);
            } else {
                charCount.put(c, charCount.get(c) + 1);
            }
            while (charCount.size() > k) {
                int leftCharCount = charCount.get(str.charAt(left));
                if (leftCharCount == 1) {
                    charCount.remove(str.charAt(left));
                } else {
                    charCount.put(str.charAt(left), leftCharCount - 1);
                }
                left++;
            }
            if (i - left + 1 > longestSubstring.length()) {
                longestSubstring = str.substring(left, i + 1);
            }
        }

        return longestSubstring;

    }
}
