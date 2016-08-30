package LeetCode;

import java.util.*;

/**
 * Given two strings s and t which consist of only lowercase letters.

 String t is generated by random shuffling string s and then add one more letter at a random position.

 Find the letter that was added in t.

 Example:

 Input:
 s = "abcd"
 t = "abcde"

 Output:
 e

 Explanation:
 'e' is the letter that was added.
 *
 * Created by Thanakorn on 8/29/16.
 */
public class FindTheDifference {


    public static void main(String[] args) {
        FindTheDifference findTheDifference = new FindTheDifference();
        String s = "abcd";
        String t = "abcde";
        System.out.println(findTheDifference.findTheDifference(s, t));
    }

    public char findTheDifference(String s, String t) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int count = charCount.getOrDefault(s.charAt(i), 0);
            charCount.put(s.charAt(i), count + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            int count = charCount.getOrDefault(t.charAt(i), 0);
            if (count == 1) {
                charCount.remove(t.charAt(i));
            } else {
                charCount.put(t.charAt(i), count - 1);
            }
        }
        return charCount.keySet().iterator().next();
    }

}
