package LeetCode;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.

 Note: Do not use any built-in library function such as sqrt.

 Example 1:

 Input: 16
 Returns: True
 Example 2:

 Input: 14
 Returns: False
 *
 * Created by Thanakorn on 6/26/16.
 */
public class ValidPerfectSquare {

    public static void main(String[] args) {
        ValidPerfectSquare validPerfectSquare = new ValidPerfectSquare();
        System.out.println(validPerfectSquare.isPerfectSquare(16));  // true
        System.out.println(validPerfectSquare.isPerfectSquare(14));  // false
        System.out.println(validPerfectSquare.isPerfectSquare(2147483647));
    }

    public boolean isPerfectSquare(int num) {
        if (num <= 0) {
            return false;
        }

        long left = 1, right = num;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long midSquare = mid * mid;
            if (midSquare == num) {
                return true;
            } else if (midSquare > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }

}
