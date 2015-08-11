package LeetCode;

/**
 * Created by Thanakorn on 7/5/15.
 */
public class IsPowerOfTwo {

    public static void main(String[] args) {

        System.out.println(isPowerOfTwo(2097153));
    }

    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        int i = n & n - 1;
        return i == 0;
    }


    public static boolean isPowerOfTwo2(int n) {
        if (n <= 0) return false;
        return Integer.bitCount(n) == 1 ? true : false;
    }
}
