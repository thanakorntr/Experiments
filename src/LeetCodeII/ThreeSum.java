package LeetCodeII;

import java.util.*;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Note:
 Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 The solution set must not contain duplicate triplets.
 For example, given array S = {-1 0 1 2 -1 -4},

 A solution set is:
 (-1, 0, 1)
 (-1, -1, 2)
 *
 * Created by Thanakorn on 2/17/16.
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> threeSum = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return threeSum;
        }

        Arrays.sort(nums);
        Set<List<Integer>> visited = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            int twoSum = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sumLeftRight = nums[left] + nums[right];
                if (sumLeftRight == twoSum) {
                    List<Integer> possibleSol = Arrays.asList(nums[i], nums[left], nums[right]);
                    if (!visited.contains(possibleSol)) {
                        visited.add(possibleSol);
                        threeSum.add(possibleSol);
                    }
                    left++;
                    right--;
                } else if (sumLeftRight > twoSum) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return threeSum;
    }

}
