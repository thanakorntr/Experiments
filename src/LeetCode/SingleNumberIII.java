package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

 For example:

 Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

 Note:
 The order of the result is not important. So in the above example, [5, 3] is also correct.
 Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 *
 * Created by Thanakorn on 12/26/15.
 */
public class SingleNumberIII {


    public int[] singleNumber(int[] nums) {

        if (nums == null || nums.length < 2) {
            return null;
        }

        int xorOfAll = 0;
        for (int num : nums) {
            xorOfAll ^= num;
        }

        int diffOneBit = Integer.highestOneBit(xorOfAll);
        int[] ans = new int[2];
        for (int num : nums) {
            if ((num & diffOneBit) == 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }

        return ans;
    }

    public int[] singleNumberONMemory(int[] nums) {

        if (nums == null || nums.length < 2) {
            return null;
        }

        Set<Integer> dups = new HashSet<>();
        Set<Integer> ans = new HashSet<>();
        for (int num : nums) {
            if (!ans.contains(num)) {
                if (!dups.contains(num)) {
                    ans.add(num);
                } else {
                    dups.remove(num);
                }
            } else {
                ans.remove(num);
            }
        }

        int[] res = new int[2];
        int id = 0;
        for (int i : ans) {
            res[id++] = i;
        }
        return res;
    }

}
