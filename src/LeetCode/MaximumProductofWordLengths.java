package LeetCode;

/**
 *
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

 Example 1:
 Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 Return 16
 The two words can be "abcw", "xtfn".

 Example 2:
 Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 Return 4
 The two words can be "ab", "cd".

 Example 3:
 Given ["a", "aa", "aaa", "aaaa"]
 Return 0
 No such pair of words.
 *
 * Created by Thanakorn on 12/24/15.
 */
public class MaximumProductofWordLengths {

    public static void main(String[] args) {

    }

    public int maxProduct(String[] words) {

        if (words == null || words.length < 2) {
            return 0;
        }

        int[] bitReps = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int bitRep = 0;
            for (char c : word.toCharArray()) {
                bitRep |= (1 << (c - 'a'));
            }
            bitReps[i] = bitRep;
        }

        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < bitReps.length - 1; i++) {
            for (int j = i + 1; j < bitReps.length; j++) {
                if ((bitReps[i] & bitReps[j]) == 0) {
                    maxLen = Math.max(maxLen, words[i].length() * words[j].length());
                }
            }
        }

        return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
    }
}
