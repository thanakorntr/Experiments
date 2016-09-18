package LeetCode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given a positive integer n and you can do operations as follow:

 If n is even, replace n with n/2.
 If n is odd, you can replace n with either n + 1 or n - 1.
 What is the minimum number of replacements needed for n to become 1?

 Example 1:

 Input:
 8

 Output:
 3

 Explanation:
 8 -> 4 -> 2 -> 1
 Example 2:

 Input:
 7

 Output:
 4

 Explanation:
 7 -> 8 -> 4 -> 2 -> 1
 or
 7 -> 6 -> 3 -> 2 -> 1
 *
 * Created by Thanakorn on 9/17/16.
 */
public class IntegerReplacement {

    public static void main(String[] args) {
        IntegerReplacement integerReplacement = new IntegerReplacement();

//        System.out.println(integerReplacement.integerReplacement(8));  // 3

//        System.out.println(integerReplacement.integerReplacement(7));  // 4

        System.out.println(integerReplacement.integerReplacement(2147483647));  // 32
    }

    public int integerReplacement(int n) {
        Set<Long> visited = new HashSet<>();
        visited.add((long)n);
        int numReplacement = 0;

        Queue<Long> curLevel = new LinkedList<>();
        curLevel.add((long)n);

        while (!curLevel.isEmpty()) {
            Queue<Long> nextLevel = new LinkedList<>();
            while (!curLevel.isEmpty()) {
                long curNum = curLevel.poll();
                if (curNum == 1) {
                    return numReplacement;
                }
                if (curNum % 2 == 0) {
                    long next1 = curNum / 2;
                    if (!visited.contains(next1)) {
                        nextLevel.add(next1);
                        visited.add(next1);
                    }
                }
                long next2 = curNum + 1;
                if (next2 > 0 && !visited.contains(next2)) {
                    nextLevel.add(next2);
                    visited.add(next2);
                }
                long next3 = curNum - 1;
                if (next3 > 0 && !visited.contains(next3)) {
                    nextLevel.add(next3);
                    visited.add(next3);
                }
            }
            numReplacement++;
            curLevel = nextLevel;
        }

        return numReplacement;
    }

}
