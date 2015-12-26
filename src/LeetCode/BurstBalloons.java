package LeetCode;

import java.util.Arrays;

/**
 * Created by Thanakorn on 11/29/15.
 * <p>
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * <p>
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * Note:
 * (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * <p>
 * Example:
 * <p>
 * Given [3, 1, 5, 8]
 * <p>
 * Return 167
 * <p>
 * nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {

    public static void main(String[] args) {

    }

    public int maxCoins(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] padddedNums = new int[nums.length + 2];
        padddedNums[0] = padddedNums[padddedNums.length - 1] = 1;
        for (int i = 0; i < nums.length; i++) {
            padddedNums[i + 1] = nums[i];
        }

        int[][] memo = new int[padddedNums.length][padddedNums.length];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return maxCoinsHelper(padddedNums, 0, padddedNums.length - 1, memo);
    }

    private int maxCoinsHelper(int[] paddedNums, int left, int right, int[][] memo) {

        if (memo[left][right] != -1) {
            return memo[left][right];
        }

        if (left + 1 == right) {  // because we added 2 padding balloons
            return 0;
        }

        int maxCost = Integer.MIN_VALUE;
        for (int i = left + 1; i < right; i++) {
            int subCost = paddedNums[left] * paddedNums[right] * paddedNums[i]
                    + maxCoinsHelper(paddedNums, left, i, memo)
                    + maxCoinsHelper(paddedNums, i, right, memo);
            maxCost = Math.max(maxCost, subCost);
        }

        memo[left][right] = maxCost;
        return maxCost;
    }
}
