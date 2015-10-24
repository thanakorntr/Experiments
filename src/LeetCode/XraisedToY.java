package LeetCode;

/**
 * Created by Thanakorn on 10/24/15.
 * <p>
 * Given a positive integer n, find if it can be expressed as x^y where y > 1 and x > 0. x and y both are integers.
 * <p>
 * Examples:
 * <p>
 * Input:  n = 8
 * Output: true
 * 8 can be expressed as 23
 * <p>
 * Input:  n = 49
 * Output: true
 * 49 can be expressed as 72
 * <p>
 * Input:  n = 48
 * Output: false
 * 48 can't be expressed as x^y
 */
public class XraisedToY {

    public static void main(String[] args) {

        System.out.println(checkXraisedToY(8));  // true
        System.out.println(checkXraisedToY(49));  // true
        System.out.println(checkXraisedToY(48));  // false

    }

    private static boolean checkXraisedToY(int n) {

        if (n == 1) {
            return true;
        }

        for (int x = 2; x * x <= n; x++) {
            int p = x;
            while (p <= n) {
                if (p == n) {
                    return true;
                }
                p *= x;
            }
        }

        return false;
    }
}
