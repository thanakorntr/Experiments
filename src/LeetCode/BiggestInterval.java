package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of integers, find out the biggest interval that has all its
 * members in the given list. e.g. given list 1, 7, 4, 6, 3, 10, 2
 * then answer would be [1, 4]. Develop algorithm and write code for this
 * <p>
 * Created by Thanakorn on 7/17/16.
 */
public class BiggestInterval {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 7, 4, 6, 3, 10, 2};
        System.out.println(Arrays.toString(getBiggestInterval(nums)));  // 1,4
    }

    private static int[] getBiggestInterval(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException();
        }

        Map<Integer, int[]> intervalMap = new HashMap<>();
        int opLeft = -1;
        int optRight = 0;

        for (int num : nums) {
            if (intervalMap.containsKey(num)) {
                continue;
            }
            int left = num;
            if (intervalMap.containsKey(num - 1)) {
                left = intervalMap.get(num - 1)[0];
            }
            int right = num;
            if (intervalMap.containsKey(num + 1)) {
                right = intervalMap.get(num + 1)[1];
            }
            intervalMap.put(num, new int[]{left, right});
            intervalMap.put(left, new int[]{left, right});
            intervalMap.put(right, new int[]{left, right});
            if (right - left > optRight - opLeft) {
                opLeft = left;
                optRight = right;
            }
        }

        return new int[]{opLeft, optRight};
    }

}
