package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 *  Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

 For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

 Note: you may assume that n is not less than 2.

 Hint:

 There is a simple O(n) solution to this problem.
 You may check the breaking results of n ranging from 7 to 10 to discover the regularities.

 *
 * Created by Thanakorn on 4/21/16.
 */
public class IntegerBreak {

    public static void main(String[] args) {
        IntegerBreak integerBreak = new IntegerBreak();
        int n = 4;
        System.out.println(integerBreak.integerBreak(n));
    }

    public int integerBreak(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(1, 0);
        memo.put(2, 1);
        return integerBreakHelper(n, memo);
    }

    private int integerBreakHelper(int n, Map<Integer, Integer> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int maxProduct = Integer.MIN_VALUE;

        for (int i = 1; i < n; i++) {
            int remaining = n - i;
            if (!memo.containsKey(remaining)) {
                memo.put(remaining, integerBreakHelper(remaining, memo));
            }
            maxProduct = Math.max(maxProduct, Math.max(i * memo.get(remaining), i * remaining));
        }

        memo.put(n, maxProduct);
        return maxProduct;
    }

}
