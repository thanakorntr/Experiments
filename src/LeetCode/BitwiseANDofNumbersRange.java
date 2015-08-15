package LeetCode;

/**
 * Created by Thanakorn on 8/15/15.
 */
/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range,
inclusive.
For example, given the range [5, 7], you should return 4.*/

public class BitwiseANDofNumbersRange {

    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd(5,7));
    }

    public static int rangeBitwiseAnd(int m, int n) {
        int num = m;
        for (int i = m+1; i <= n; i++) {
            num = (num & i);
        }
        return num;
    }
}
