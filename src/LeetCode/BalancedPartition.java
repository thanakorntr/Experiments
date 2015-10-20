package LeetCode;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thanakorn on 10/19/15.
 */
@RunWith(Parameterized.class)
public class BalancedPartition {

    private static final int NUMS_LEN = 10;
    private static final int NUM_TESTCASES = 100;

    @Parameterized.Parameter(value = 0)
    public int[] testcase;

    @Parameterized.Parameter(value = 1)
    public boolean expected;

    @org.junit.Test
    public void eval() {
        boolean actual = canBalancedPartitionBruteForce(testcase);
        assertEquals(expected, actual);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] testcases = new Object[NUM_TESTCASES][2];
        for (int i = 0; i < NUM_TESTCASES; i++) {
            int[] nums = generateRandomTestcase();
            testcases[i][0] = nums;
            testcases[i][1] = canBalancedPartitionKnapSackStyle(nums);
        }
        return Arrays.asList(testcases);
    }

    private static boolean canBalancedPartitionKnapSackStyle(int[] nums) {

        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int halfSum = sum / 2;

        boolean[] prevCanPartition = new boolean[halfSum + 1];
        boolean[] curCanPartition = new boolean[halfSum + 1];
        for (int num : nums) {
            for (int remainingSum = 1; remainingSum < prevCanPartition.length; remainingSum++) {
                if (remainingSum == num) {
                    curCanPartition[remainingSum] = true;
                } else if (remainingSum > num) {
                    curCanPartition[remainingSum] = prevCanPartition[remainingSum] || prevCanPartition[remainingSum - num];
                } else {
                    curCanPartition[remainingSum] = prevCanPartition[remainingSum];
                }
            }
            prevCanPartition = curCanPartition;
        }

        return curCanPartition[halfSum];

    }

    private static boolean canBalancedPartitionBruteForce(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int halfSum = sum / 2;

        Map<Integer, Map<Integer, Boolean>> memo = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            memo.put(i, new HashMap<>());
        }
        return canBalancedPartitionBruteForceHelper(nums, halfSum, 0, memo);
    }

    private static boolean canBalancedPartitionBruteForceHelper(int[] nums, int remainingSum,
                                                                int startIndex, Map<Integer, Map<Integer, Boolean>> memo) {

        if (memo.get(startIndex).containsKey(remainingSum)) {
            return memo.get(startIndex).get(remainingSum);
        }

        if (startIndex == nums.length - 1) {
            memo.get(startIndex).put(remainingSum, remainingSum == nums[startIndex]);
            return remainingSum == nums[startIndex];
        }

        if (nums[startIndex] == remainingSum) {
            memo.get(startIndex).put(remainingSum, true);
            return true;
        }

        if (nums[startIndex] < remainingSum) {
            if (!memo.get(startIndex + 1).containsKey(remainingSum)) {
                memo.get(startIndex + 1).put(remainingSum, canBalancedPartitionBruteForceHelper(nums, remainingSum, startIndex + 1, memo));
            }
            if (memo.get(startIndex + 1).get(remainingSum)) {
                memo.get(startIndex).put(remainingSum, true);
                return true;
            }

            if (!memo.get(startIndex + 1).containsKey(remainingSum - nums[startIndex])) {
                memo.get(startIndex + 1).put(remainingSum - nums[startIndex], canBalancedPartitionBruteForceHelper(nums, remainingSum - nums[startIndex], startIndex + 1, memo));
            }
            if (memo.get(startIndex + 1).get(remainingSum - nums[startIndex])) {
                memo.get(startIndex).put(remainingSum, true);
                return true;
            }

            memo.get(startIndex).put(remainingSum, false);
            return false;
        } else {
            if (!memo.get(startIndex + 1).containsKey(remainingSum)) {
                memo.get(startIndex + 1).put(remainingSum, canBalancedPartitionBruteForceHelper(nums, remainingSum, startIndex + 1, memo));
            }
            if (memo.get(startIndex + 1).get(remainingSum)) {
                memo.get(startIndex).put(remainingSum, true);
                return true;
            } else {
                memo.get(startIndex).put(remainingSum, false);
                return false;
            }
        }

    }

    private static int[] generateRandomTestcase() {
        int[] nums = new int[NUMS_LEN];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (NUMS_LEN * Math.random() + 1);
        }
        return nums;
    }
}
