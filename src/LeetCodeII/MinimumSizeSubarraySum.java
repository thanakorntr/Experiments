package LeetCodeII;

/**
 *  Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

 For example, given the array [2,3,1,2,4,3] and s = 7,
 the subarray [4,3] has the minimal length under the problem constraint.
 *
 * Created by Thanakorn on 3/27/16.
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {

        int[] nums = {2,3,1,2,4,3};
        int s = 7;
        System.out.println(new MinimumSizeSubarraySum().minSubArrayLen(s, nums));

    }

    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int minLen = Integer.MAX_VALUE;
        int curSum = 0;
        int startIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            while (curSum >= s && startIndex <= i) {
                minLen = Math.min(minLen, i - startIndex + 1);
                curSum -= nums[startIndex];
                startIndex++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

}
