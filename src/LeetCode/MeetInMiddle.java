package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/meet-in-the-middle/
 *
 * Meet in the middle
 Given a set of n integers where n <= 40. Each of them is at most 1012,
 determine the maximum sum subset having sum less than or equal S(S1018)

 Example:

 Input  : set[] = {45, 34, 4, 12, 5, 2} and S = 42
 Output : 41
 Maximum possible subset sum is 41 which can be
 obtained by summing 34, 5 and 2.

 Input  : Set[] = {3, 34, 4, 12, 5, 2} and S = 10
 Output : 10
 Maximum possible subset sum is 10 which can be
 obtained by summing 2, 3 and 5.
 *
 * Created by Thanakorn on 6/13/16.
 */
public class MeetInMiddle {

    public static void main(String[] args) {
        int[] nums = new int[]{45, 34, 4, 12, 5, 2};
        int S = 42;
        System.out.println(solveMeetInMiddle(nums, S));  // 41

        nums = new int[]{3, 34, 4, 12, 5, 2};
        S = 10;
        System.out.println(solveMeetInMiddle(nums, S));  // 10
    }

    private static int solveMeetInMiddle(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();
        return solveMeetInMiddleHelper(nums, 0, S, memo);
    }

    private static int solveMeetInMiddleHelper(int[] nums, int startIndex,
                                               int S, Map<Integer, Map<Integer, Integer>> memo) {

        if (memo.containsKey(startIndex) && memo.get(startIndex).containsKey(S)) {
            return memo.get(startIndex).get(S);
        }

        if (!memo.containsKey(startIndex)) {
            memo.put(startIndex, new HashMap<>());
        }

        if (nums[startIndex] > S) {
            memo.get(startIndex).put(S, 0);
            return 0;
        }
        if (startIndex == nums.length - 1) {
            memo.get(startIndex).put(S, nums[startIndex]);
            return nums[startIndex];
        }

        if (!memo.containsKey(startIndex + 1)) {
            memo.put(startIndex + 1, new HashMap<>());
        }
        if (!memo.get(startIndex + 1).containsKey(S - nums[startIndex])) {
            memo.get(startIndex + 1).put(S - nums[startIndex], solveMeetInMiddleHelper(nums, startIndex + 1, S - nums[startIndex], memo));
        }
        int max1 = nums[startIndex] + memo.get(startIndex + 1).get(S - nums[startIndex]);

        if (!memo.get(startIndex + 1).containsKey(S)) {
            memo.get(startIndex + 1).put(S, solveMeetInMiddleHelper(nums, startIndex + 1, S, memo));
        }
        int max2 = memo.get(startIndex + 1).get(S);

        int max = Math.max(max1, max2);
        memo.get(startIndex).put(S, max);
        return max;
    }

}
