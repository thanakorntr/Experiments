package LeetCode;

/**
 * Created by Thanakorn on 8/30/15.
 * *
 * A number is non-decreasing if every digit (except the first one) is greater than or equal to previous digit.
 * For example, 223, 4455567, 899, are non-decreasing numbers.

 So, given the number of digits n, you are required to find the count of total non-decreasing numbers with n digits.

 Examples:

 Input:  n = 1
 Output: count  = 10

 Input:  n = 2
 Output: count  = 55

 Input:  n = 3
 Output: count  = 220
 */
public class NonDecreasingNumbersWithNDigits {

    public static void main(String[] args) {
        System.out.println(nonDecreasingNumbersWithNDigits(1)); // 10
        System.out.println(nonDecreasingNumbersWithNDigits(2)); // 55
        System.out.println(nonDecreasingNumbersWithNDigits(3)); // 220
    }

    private static int nonDecreasingNumbersWithNDigits(int n) {

        // initiate base cases
        int[] prev = new int[10];
        int[] cul = new int[10];
        for (int i = 0; i < 10; i++) {
            prev[i] = 1;
            cul[i] = i+1;
        }

        for (int j = 2; j <= n; j++) {
            prev = cul;
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += prev[i];
                cul[i] = sum;
            }
        }

        return cul[9];
    }
}
