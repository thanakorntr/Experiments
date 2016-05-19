package LeetCode;

import java.util.*;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

 For example, given:
 s: "barfoothefoobarman"
 words: ["foo", "bar"]

 You should return the indices: [0,9].
 (order does not matter).


 *
 * Created by Thanakorn on 5/18/16.
 */
public class SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWords substringWithConcatenationOfAllWords
                = new SubstringWithConcatenationOfAllWords();

        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo", "bar"};
        System.out.println(substringWithConcatenationOfAllWords.findSubstring(s, words).toString());
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> startIndices = new ArrayList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return startIndices;
        }

        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            int count = wordMap.getOrDefault(word, 0);
            wordMap.put(word, count + 1);
        }

        int wordLen = words[0].length();

        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < s.length() - wordLen + 1; i++) {
            tokens.add(s.substring(i, i + wordLen));
        }

        for (int startIndex = 0; startIndex < s.length() - wordLen * words.length + 1; startIndex++) {
            int count = 0;
            int i = startIndex;
            while (count != words.length) {
                String curToken = tokens.get(i);
                int curCount = wordMap.getOrDefault(curToken, 0);
                if (curCount <= 0) {
                    break;
                }
                wordMap.put(curToken, curCount - 1);
                i += wordLen;
                count++;
            }

            if (count == words.length) {
                startIndices.add(startIndex);
            }

            i -= wordLen;
            while (i >= startIndex) {
                int curCount = wordMap.get(tokens.get(i));
                wordMap.put(tokens.get(i), curCount + 1);
                i -= wordLen;
            }
        }

        return startIndices;
    }

}
