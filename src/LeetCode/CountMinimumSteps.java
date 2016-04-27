package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/count-minimum-steps-get-given-desired-array/
 *
 * Consider an array with n elements and value of all the elements is zero. We can perform following operations on the array.

 Incremental operations:Choose 1 element from the array and increment its value by 1.
 Doubling operation: Double the values of all the elements of array.
 We are given desired array target[] containing n elements. Compute and return the smallest possible number of the operations needed to change the array from all zeros to desired array.

 Sample test cases:

 Input: target[] = {2, 3}
 Output: 4
 To get the target array from {0, 0}, we
 first increment both elements by 1 (2
 operations), then double the array (1
 operation). Finally increment second
 element (1 more operation)

 Input: target[] = {2, 1}
 Output: 3
 One of the optimal solution is to apply the
 incremental operation 2 times to first and
 once on second element.

 Input: target[] = {16, 16, 16}
 Output: 7
 The output solution looks as follows. First
 apply an incremental operation to each element.
 Then apply the doubling operation four times.
 Total number of operations is 3+4 = 7
 *
 * Created by Thanakorn on 4/25/16.
 */
public class CountMinimumSteps {

    public static void main(String[] args) {
        List<Integer> desired = new ArrayList<>();
        desired.add(2);
        desired.add(3);
        System.out.println(countMinimumSteps(desired));  // 4
        desired.clear();
        desired.add(2);
        desired.add(1);
        System.out.println(countMinimumSteps(desired));  // 3
        desired.clear();
        desired.add(16);
        desired.add(16);
        desired.add(16);
        System.out.println(countMinimumSteps(desired));  // 7

    }

    private static int countMinimumSteps(List<Integer> desired) {
        if (desired == null || desired.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Map<List<Integer>, Integer> memo = new HashMap<>();
        List<Integer> current = new ArrayList<>();
        for (int i = 0; i < desired.size(); i++) {
            current.add(0);
        }
        int ans =  countMinimumStepsHelper(current,desired, memo, true);
        return ans;
    }

    private static int countMinimumStepsHelper(List<Integer> current,
                                               List<Integer> desired,
                                               Map<List<Integer>, Integer> memo,
                                               boolean initial) {
        if (memo.containsKey(current)) {
            return memo.get(current);
        }

        int minOperation = Integer.MAX_VALUE;

        // add 1 to each element
        for (int i = 0; i < desired.size(); i++) {
            current.set(i, current.get(i) + 1);
            int eval = eval(current, desired);
            if (eval == 0) {
                current.set(i, current.get(i) - 1);
                memo.put(current, 1);
                return 1;
            } else if (eval == -1) {
                int subMinOperation = 1 + countMinimumStepsHelper(new ArrayList<>(current), desired, memo, false);
                minOperation = Math.min(minOperation, subMinOperation);
            }
            current.set(i, current.get(i) - 1);
        }

        // double each element
        if (!initial) {
            for (int i = 0; i < desired.size(); i++) {
                current.set(i, current.get(i) * 2);
            }
            int eval = eval(current, desired);
            if (eval == 0) {
                for (int i = 0; i < desired.size(); i++) {
                    current.set(i, current.get(i) / 2);
                }
                memo.put(current, 1);
                return 1;
            } else if (eval == -1) {
                int subMinOperation = 1 + countMinimumStepsHelper(new ArrayList<>(current), desired, memo, false);
                minOperation = Math.min(minOperation, subMinOperation);
            }
        }

        for (int i = 0; i < desired.size(); i++) {
            current.set(i, current.get(i) / 2);
        }

        memo.put(current, minOperation);
        return minOperation;
    }

    // return -1 if some elements in current is less than desired and the remaining is desired
    // return 0 if all elements in current is desired
    // return 1 if at least one element in current is greater than desired
    private static int eval(List<Integer> current, List<Integer> desired) {
        if (current == null || desired == null || current.isEmpty() || desired.isEmpty()) {
            throw new IllegalArgumentException();
        }

        boolean hasLessThan = false;
        for (int i = 0; i < desired.size(); i++) {
            if (current.get(i) > desired.get(i)) {
                return 1;
            } else if (current.get(i) < desired.get(i)) {
                hasLessThan = true;
            }
        }

        return hasLessThan ? -1 : 0;
    }

}
