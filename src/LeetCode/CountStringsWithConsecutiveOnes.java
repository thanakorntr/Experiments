package LeetCode;

/**
 * http://www.geeksforgeeks.org/count-strings-with-consecutive-1s/
 *
 * Given a number n, count number of n length strings with consecutive 1’s in them.

 Examples:

 Input  : n = 2
 Output : 1
 There are 4 strings of length 2, the
 strings are 00, 01, 10 and 11. Only the
 string 11 has consecutive 1's.

 Input  : n = 3
 Output : 3
 There are 8 strings of length 3, the
 strings are 000, 001, 010, 011, 100,
 101, 110 and 111.  The strings with
 consecutive 1's are 011, 110 and 111.

 Input : n = 5
 Output : 19
 *
 * Created by Thanakorn on 4/29/16.
 */
public class CountStringsWithConsecutiveOnes {

    public static void main(String[] args) {
        System.out.println(countString(3));
        System.out.println(countString(4));
        System.out.println(countString(5));
    }

    private static int countString(int n) {
        if (n <= 2) {
            return 1;
        }

        int a = 0; // number of consecutive strings ending with 0
        int b = 0; // number of consecutive strings ending with 1
        int c = 1; // number of consecutive strings ending with 1s
        int d = 1; // number of non-consecutive strings ending with 1
        int e = 2; // number of non-consecutive strings ending with 0s

        for (int i = 3; i <= n; i++) {
            int newA = a + b + c;
            int newB = a;
            int newC = b + c + d;
            int newD = e;
            int newE = d + e;
            a = newA;
            b = newB;
            c = newC;
            d = newD;
            e = newE;
        }

        return a + b + c;
    }

}
