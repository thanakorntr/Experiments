package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 8/26/15.
 *
 *  Write a program to find the n-th ugly number.

 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

 Note that 1 is typically treated as an ugly number.

 Hint:

 The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
 An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
 The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
 Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).

 */
public class UglyNumberII {

    public static void main(String[] args) {
        // 1 2 3 4 5 6 8 9 10 12 ...

        System.out.println(nthUglyNumber(1600));
        //System.out.println(bruteForce(1600));
    }

    public static int nthUglyNumber(int n) {

        int count = 1;
        int curUglyNum = 1;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> numsInPq = new HashSet<>();
        pq.add(2); pq.add(3); pq.add(5);
        numsInPq.add(2); numsInPq.add(3); numsInPq.add(5);

        List<Integer> list = new ArrayList<>();
        list.add(1);

        while (count != n) {

            curUglyNum = pq.poll();
            numsInPq.remove(curUglyNum);
            list.add(curUglyNum);

            int n1 = mulAndCheck(curUglyNum, 2);
            int n2 = mulAndCheck(curUglyNum, 3);
            int n3 = mulAndCheck(curUglyNum, 5);

            if (n1 > curUglyNum && !numsInPq.contains(n1)) {
                numsInPq.add(n1);
                pq.add(n1);
            }
            if (n2 > curUglyNum && !numsInPq.contains(n2)) {
                numsInPq.add(n2);
                pq.add(n2);
            }
            if (n3 > curUglyNum && !numsInPq.contains(n3)) {
                numsInPq.add(n3);
                pq.add(n3);
            }

            count++;
        }

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i-1)) {
                System.out.println();
            }
        }

        return curUglyNum;
    }

    public static int mulAndCheck(int x, int y) {
        long m = ((long)x) * ((long)y);
        if (m < Integer.MIN_VALUE || m > Integer.MAX_VALUE) {
            return -1;
        }
        return (int)m;
    }

    public static int bruteForce(int n) {
        int count = 1;
        int curNum = 2;
        while (count != n) {
            if (UglyNumber.isUgly(curNum)) {
                count++;
                if (count == n) {
                    break;
                }
            }
            curNum++;
        }

        return curNum;
    }
}
