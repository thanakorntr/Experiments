package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 7/31/15.
 */
public class IPsoftBrainTeaser {

    public static void main(String[] args) {

        July2015();
    }

    public static boolean isPrime(int num) {

        if (num <= 1) return false;
        // Loop's ending condition is i * i <= num instead of i <= sqrt(num)
        // to avoid repeatedly calling an expensive function sqrt().
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void July2015() {

        Set<Integer> centeredSquarePrimes = new HashSet<>();
        for (int n = 1; n < 1000000; n++) {
            int csp = (int)Math.pow(n, 2) + (int)Math.pow(n-1, 2);
            if (isPrime(csp)) {
                centeredSquarePrimes.add(csp);
            }
        }

        for (int Oh = 1; Oh <= 24; Oh++) {
            for (int A = 1; A <= 1000000; A++) {
                double NDouble = ((1.65D * A) - (0.6D * Oh)) / 0.93;
                if (NDouble == Math.floor(NDouble) && !Double.isInfinite(NDouble)) {
                    int N = (int)NDouble;
                    if (N + Oh + A > 1000) {
                        int sum = N + Oh + A;
                        if (centeredSquarePrimes.contains(sum)) {
                            boolean verifyEqualSum = (0.07D * N) + (0.4D * Oh) + (2.65D * A) == (double)(N + Oh + A);
                            boolean verifyGreaterThan1000 = (N + Oh + A) > 1000;
                            if (verifyEqualSum && verifyGreaterThan1000) {
                                System.out.print("A = " + A + " N = " + N + " Oh = " + Oh);
                                System.out.print(" Sum = " + (A + N + Oh));
                                System.out.print(" Cost = " + ((0.07D * N) + (0.4D * Oh) + (2.65D * A)));
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
    }
}
