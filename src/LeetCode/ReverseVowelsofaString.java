package LeetCode;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.

 Example 1:
 Given s = "hello", return "holle".

 Example 2:
 Given s = "leetcode", return "leotcede".
 *
 * Created by Thanakorn on 4/23/16.
 */
public class ReverseVowelsofaString {

    public String reverseVowels(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        int left = 0, right = s.length() - 1;
        char[] charArray = s.toCharArray();
        while (left < right) {
            while (!isVowel(charArray[left]) && left < right) {
                left++;
            }
            while (!isVowel(charArray[right]) && left < right) {
                right--;
            }
            if (left < right) {
                swap(charArray, left, right);
            }
            left++;
            right--;
        }

        return String.copyValueOf(charArray);
    }

    private void swap(char[] charArray, int i, int j) {
        char tmp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = tmp;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A'
                || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

}
