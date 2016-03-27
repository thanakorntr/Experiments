package LeetCode;

/**
 *  Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

 For example, given the array [2,3,1,2,4,3] and s = 7,
 the subarray [4,3] has the minimal length under the problem constraint.
 *
 * Created by Thanakorn on 7/5/15.
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {

        int[] nums = {2,3,1,2,4,3};
        int s = 7;
        System.out.println(minSubArrayLen(s, nums));

    }

    public static int minSubArrayLen(int s, int[] nums) {
        int curMinLen = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s && start <= i) {
                if (sum - nums[start] >= s) {
                    sum -= nums[start];
                    start++;
                    curMinLen = Math.min(curMinLen, i - start + 1);
                } else {
                    curMinLen = Math.min(curMinLen, i - start + 1);
                    break;
                }
            }
        }

        return curMinLen == Integer.MAX_VALUE ? 0 : curMinLen;
    }
}
