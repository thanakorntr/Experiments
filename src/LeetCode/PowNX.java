package LeetCode;

/**
 * Created by Thanakorn on 11/17/15.
 */
public class PowNX {

    public static void main(String[] args) {

    }

    public double myPow(double x, int n) {
        if (n==0) {
            return 1;
        }

        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }
}
