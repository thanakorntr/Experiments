package LeetCode;

/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

 Example:
 Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100,
 excluding [11,22,33,44,55,66,77,88,99])


 *
 * Created by Thanakorn on 6/18/16.
 */
public class CountNumberswithUniqueDigits {

    public static void main(String[] args) {
        CountNumberswithUniqueDigits countNumberswithUniqueDigits = new CountNumberswithUniqueDigits();
        System.out.println(countNumberswithUniqueDigits.countNumbersWithUniqueDigits(2));  // 91
    }

    public int countNumbersWithUniqueDigits(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }

        int totalCombination = 1;
        int combinationOfLenK = 1;
        for (int k = 1; k <= n; k++) {
            if (k <= 2) {
                combinationOfLenK *= 9;
            } else {
                combinationOfLenK *= (10 - k + 1);
            }
            totalCombination += combinationOfLenK;
        }

        return totalCombination;
    }

}
