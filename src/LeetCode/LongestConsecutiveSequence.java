package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanakorn on 10/10/15.
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 For example,
 Given [100, 4, 200, 1, 3, 2],
 The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

 Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {

        int[] nums = new int[]{100,4,200,1,3,2};
        System.out.println(longestConsecutive(nums));  // 4

    }

    private static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int longest = Integer.MIN_VALUE;
        Map<Integer, Integer> mapLongestConsecutive = new HashMap<>();

        for (int n : nums) {
            if (!mapLongestConsecutive.containsKey(n)) {
                int left = mapLongestConsecutive.containsKey(n-1) ? mapLongestConsecutive.get(n-1) : 0;
                int right = mapLongestConsecutive.containsKey(n+1) ? mapLongestConsecutive.get(n+1) : 0;
                int len = 1 + left + right;
                mapLongestConsecutive.put(n, len);
                longest = Math.max(longest, len);
                mapLongestConsecutive.put(n-left, len);
                mapLongestConsecutive.put(n+right, len);
            }
        }

        return longest;
    }

}
