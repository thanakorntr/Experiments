package LeetCode;

/**
 * Created by Thanakorn on 8/17/15.
 */
public class IsPalindrome {

    public boolean isPalindrome(int x) {
        if (x < 0) return false;

        int palindrome = 0;
        int tmp = x;

        while (tmp > 0) {
            int lastDigit = tmp % 10;
            palindrome = palindrome*10 + lastDigit;
            tmp = tmp / 10;
        }

        return palindrome == x;
    }

}
