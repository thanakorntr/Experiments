package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 *  Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 For example,
 S = "ADOBECODEBANC"
 T = "ABC"

 Minimum window is "BANC".

 Note:
 If there is no such window in S that covers all characters in T, return the empty string "".

 If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 *
 * Created by Thanakorn on 8/9/15.
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();

        String s;
        String t;

        s = "ADBANC";
        t = "ABC";
        System.out.println(minimumWindowSubstring.minWindow(s, t));  // "BANC"

        s = "ADOBECODEBANC";
        t = "ABC";
        System.out.println(minimumWindowSubstring.minWindow(s, t));  // "BANC"

        s = "ab";
        t = "b";
        System.out.println(minimumWindowSubstring.minWindow(s, t));  // "b"

        s = "a";
        t = "aa";
        System.out.println(minimumWindowSubstring.minWindow(s, t));  // ""
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> tcharsMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            int count = tcharsMap.getOrDefault(c, 0);
            tcharsMap.put(c, count + 1);
        }

        int optLeft = 0;
        int optRight = Integer.MAX_VALUE;
        int left = 0;
        int count = t.length();

        for (int i = 0; i < s.length(); i++) {
            if (tcharsMap.containsKey(s.charAt(i))) {
                left = i;
                break;
            }
        }

        for (int i = left; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (tcharsMap.containsKey(curChar)) {
                tcharsMap.put(curChar, tcharsMap.get(curChar) - 1);
                if (tcharsMap.get(curChar) >= 0) {
                    count--;
                }
            }

            while (count == 0) {
                if (i - left < optRight - optLeft) {
                    optLeft = left;
                    optRight = i;
                }
                tcharsMap.put(s.charAt(left), tcharsMap.get(s.charAt(left)) + 1);
                if (tcharsMap.get(s.charAt(left)) > 0) {
                    count++;
                }
                for (int j = left + 1; j <= i; j++) {
                    if (tcharsMap.containsKey(s.charAt(j))) {
                        left = j;
                        break;
                    }
                }
            }
        }

        return optRight != Integer.MAX_VALUE ? s.substring(optLeft, optRight + 1) : "";
    }

}
