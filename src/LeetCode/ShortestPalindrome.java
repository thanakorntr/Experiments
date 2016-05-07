package LeetCode;

/**
 *  Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

 For example:

 Given "aacecaaa", return "aaacecaaa".

 Given "abcd", return "dcbabcd".
 *
 * Created by Thanakorn on 5/7/16.
 */
public class ShortestPalindrome {

    public static void main(String[] args) {
        ShortestPalindrome shortestPalindrome = new ShortestPalindrome();

        String str = "aacecaaa";
        System.out.println(shortestPalindrome.shortestPalindrome(str));  // "aaacecaaa"

        str = "abcd";
        System.out.println(shortestPalindrome.shortestPalindrome(str));  // "dcbabcd"
    }

    public String shortestPalindrome(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        if (s.isEmpty()) {
            return s;
        }

        int endPalindromeIndex = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (isPalindrome(s, 0, i)) {
                endPalindromeIndex = i;
                break;
            }
        }

        String frontPartToAdd = new StringBuilder(s.substring(endPalindromeIndex + 1)).reverse().toString();
        return frontPartToAdd + s;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
