/**
 * Created by Thanakorn on 6/20/15.
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("bb"));
    }

    public static String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return s;
        }

        int maxLen = Integer.MIN_VALUE;
        int left = 0, right = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = palindromeLen(s, i, i);  // 1 3 5 ...
            int len2 = palindromeLen(s, i, i+1);  // 0 2 4 ...
            if (len1 > maxLen) {
                left = i - (len1 / 2);
                right = i + (len1 / 2);
                maxLen = len1;
            }
            if (len2 > maxLen) {
                left = i - ((len2 / 2) - 1);
                right = i + 1 + ((len2 / 2) - 1);
                maxLen = len2;
            }
        }

        return s.substring(left, right+1);
    }

    public static int palindromeLen(String s, int left, int right) {
        if (left < 0 || right >= s.length()) {
            return 0;
        }

        if (right == left + 1 && s.charAt(left) != s.charAt(right)) {
            return 0;
        }

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
