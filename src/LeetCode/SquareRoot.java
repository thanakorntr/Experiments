package LeetCode;

/**
 * Created by Thanakorn on 11/15/15.
 */
public class SquareRoot {

    public static void main(String[] args) {

        System.out.println(mySqrt(2147395599));
    }

    private static int mySqrt(int x) {
        int left = 0;
        int right = x;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long possibleSqrt = (long)mid * mid;
            if (possibleSqrt == x) {
                return mid;
            } else if (possibleSqrt < x) {
                if ((long)(mid + 1) * (mid + 1) > x) {
                    return mid;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left * left > x ? left - 1 : left;
    }
}
