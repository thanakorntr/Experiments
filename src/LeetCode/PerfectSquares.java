package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 *  Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...)
 *  which sum to n.

 For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 *
 * Created by Thanakorn on 9/11/15.
 */
public class PerfectSquares {

    public static void main(String[] args) {

        System.out.println(numSquares(12)); // 3
        System.out.println(numSquares(13)); // 2

    }

    public static int numSquares(int n) {
        List<Integer> perfectSquares = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            perfectSquares.add(i*i);
        }

        int[] m = new int[n+1];
        m[0] = 0;

        for (int currentNum = 1; currentNum < m.length; currentNum++) {
            m[currentNum] = Integer.MAX_VALUE;
            for (int sqr : perfectSquares) {
                if (currentNum == sqr) {
                    m[currentNum] = 1;
                    break;
                } else if (currentNum > sqr) {
                    int remaining = currentNum - sqr;
                    m[currentNum] = Math.min(m[currentNum], 1 + m[remaining]);
                }
            }
        }

        return m[n];
    }
}
