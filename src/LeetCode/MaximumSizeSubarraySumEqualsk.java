package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.cnblogs.com/EdwardLiu/p/5104280.html
 * <p>
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * <p>
 * Example 1:
 * Given nums = [1, -1, 5, -2, 3], k = 3,
 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * <p>
 * Example 2:
 * Given nums = [-2, -1, 2, 1], k = 1,
 * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 * <p>
 * Follow Up:
 * Can you do it in O(n) time?
 * <p>
 * Created by Thanakorn on 3/27/16.
 */
public class MaximumSizeSubarraySumEqualsk {

    public static void main(String[] args) {
        MaximumSizeSubarraySumEqualsk maximumSizeSubarraySumEqualsk = new MaximumSizeSubarraySumEqualsk();
        int[] nums = new int[]{1, -1, 5, -2, 3};
        int k = 3;
        System.out.println(maximumSizeSubarraySumEqualsk.maximumSizeSubarray(nums, k));
        nums = new int[]{-2, -1, 2, 1};
        k = 1;
        System.out.println(maximumSizeSubarraySumEqualsk.maximumSizeSubarray(nums, k));
    }

    public int maximumSizeSubarray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int sum = 0;
        int maxLen = Integer.MIN_VALUE;
        Map<Integer, Integer> indexSumMap = new HashMap<>();
        indexSumMap.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (!indexSumMap.containsKey(sum)) {
                indexSumMap.put(sum, i);
            }
            if (indexSumMap.containsKey(sum - k)) {
                int startIndex = indexSumMap.get(sum - k) + 1;
                maxLen = Math.max(maxLen, i - startIndex + 1);
            }
        }

        return maxLen == Integer.MIN_VALUE ? 0 : maxLen;

    }

}
