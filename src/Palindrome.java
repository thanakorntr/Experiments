/**
 * Created by Thanakorn on 7/13/15.
 */
public class Palindrome {

    public static void main(String[] args) {

        String s1 = "aba";
        String s2 = "amuma";
        String s3 = "ollo";
        String s4 = "Thanakorn";
        String s5 = "Trakarnvanich";

        System.out.println(isPalindrome(s1));
        System.out.println(isPalindrome(s2));
        System.out.println(isPalindrome(s3));
        System.out.println(isPalindrome(s4));
        System.out.println(isPalindrome(s5));

    }

    public static boolean isPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return true;
        }

        int left = 0, right = s.length() - 1;

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
