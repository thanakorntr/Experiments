package LeetCode;

import java.math.BigInteger;
import java.util.*;

/**
 * Given an integer array with all positive numbers and no duplicates,
 * find the number of possible combinations that add up to a positive integer target.

 Example:

 nums = [1, 2, 3]
 target = 4

 The possible combination ways are:
 (1, 1, 1, 1)
 (1, 1, 2)
 (1, 2, 1)
 (1, 3)
 (2, 1, 1)
 (2, 2)
 (3, 1)

 Note that different sequences are counted as different combinations.

 Therefore the output is 7.
 Follow up:
 What if negative numbers are allowed in the given array?
 How does it change the problem?
 What limitation we need to add to the question to allow negative numbers?

 *
 * Created by Thanakorn on 7/30/16.
 */
public class CombinationSumIV {

    public static void main(String[] args) {
        CombinationSumIV combinationSumIV = new CombinationSumIV();
        int[] nums = new int[] {1, 2, 3};
        int target = 4;
        System.out.println(combinationSumIV.combinationSum4(nums, target));  // 7

        nums = new int[] {1, 50};
        target = 200;
        System.out.println(combinationSumIV.combinationSum4(nums, target));  // 28730
    }

    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int[] memo = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num > i) {
                    break;
                } else if (num == i) {
                    memo[i] += 1;
                } else {
                    memo[i] += memo[i - num];
                }
            }
        }
        return memo[target];
    }

    public int combinationSum4TopDown(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        Map<Integer, Map<Integer, List<List<Integer>>>> memo = new HashMap<>();
        helper(nums, target, 0, memo);

        List<List<Integer>> ansArray = memo.get(0).get(target);
        int count = 0;
        for (List<Integer> subAns : ansArray) {
            count += getTotalComb(subAns);
        }
        return count;
    }

    private int getTotalComb(List<Integer> subAns) {
        List<Integer> dividingFactor = new ArrayList<>();
        int prev = subAns.get(0);
        int count = 1;
        for (int i = 1; i < subAns.size(); i++) {
            if (subAns.get(i) != prev) {
                dividingFactor.add(count);
                count = 1;
                prev = subAns.get(i);
            } else {
                count++;
            }
        }
        dividingFactor.add(count);
        BigInteger ans = factorial(subAns.size());
        for (int div : dividingFactor) {
            ans = ans.divide(factorial(div));
        }
        return ans.intValue();
    }

    private BigInteger factorial(int n) {
        if (n <= 1) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(n).multiply(factorial(n - 1));
    }

    private void helper(int[] nums, int target,
                        int startIndex, Map<Integer, Map<Integer, List<List<Integer>>>> memo) {

        if (memo.containsKey(startIndex) && memo.get(startIndex).containsKey(target)) {
            return;
        }
        if (!memo.containsKey(startIndex)) {
            memo.put(startIndex, new HashMap<>());
        }
        if (startIndex >= nums.length || nums[startIndex] > target) {
            memo.get(startIndex).put(target, new ArrayList<>());
            return;
        }

        List<List<Integer>> totalAns = new ArrayList<>();
        int mult = target / nums[startIndex];
        for (int i = 0; i <= mult; i++) {
            List<Integer> ans = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                ans.add(nums[startIndex]);
            }
            int remaining = target - nums[startIndex] * i;
            if (remaining == 0) {
                totalAns.add(ans);
                break;
            }
            if (!memo.containsKey(startIndex + 1) || !memo.get(startIndex + 1).containsKey(remaining)) {
                helper(nums, remaining, startIndex + 1, memo);
            }
            List<List<Integer>> subAns = memo.get(startIndex + 1).get(remaining);
            if (!subAns.isEmpty()) {
                for (List<Integer> subSubAns : subAns) {
                    List<Integer> newSubAns = new ArrayList<>();
                    newSubAns.addAll(ans);
                    newSubAns.addAll(subSubAns);
                    totalAns.add(newSubAns);
                }
            }
        }

        memo.get(startIndex).put(target, totalAns);
    }

}
