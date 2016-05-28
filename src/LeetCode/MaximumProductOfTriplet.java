package LeetCode;

import java.util.*;

/**
 * http://www.geeksforgeeks.org/find-maximum-product-of-a-triplet-in-array/
 * <p>
 * Given an integer array, find a maximum product of a triplet in array.
 * <p>
 * Examples:
 * <p>
 * Input:  [10, 3, 5, 6, 20]
 * Output: 1200
 * Multiplication of 10, 6 and 20
 * <p>
 * Input:  [-10, -3, -5, -6, -20]
 * Output: -90
 * <p>
 * Input:  [1, -4, 3, -6, 7, 0]
 * Output: 168
 * <p>
 * Created by Thanakorn on 5/28/16.
 */
public class MaximumProductOfTriplet {

    public static void main(String[] args) {
        int[] nums = new int[]{10, 3, 5, 6, 20};
        System.out.println(maxProductTriplet(nums));  // 1200

        nums = new int[]{-10, -3, -5, -6, -20};
        System.out.println(maxProductTriplet(nums));  // -90

        nums = new int[]{1, -4, 3, -6, 7, 0};
        System.out.println(maxProductTriplet(nums));  // 168
    }

    private static int maxProductTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        Queue<Integer> minNegVals = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> maxNegVals = new PriorityQueue<>();
        Queue<Integer> minPosVals = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> maxPosVals = new PriorityQueue<>();
        boolean hasZero = false;

        for (int num : nums) {
            if (num > 0) {
                if (maxPosVals.size() < 3) {
                    maxPosVals.add(num);
                } else {
                    if (num > maxPosVals.peek()) {
                        maxPosVals.poll();
                        maxPosVals.add(num);
                    }
                }
                if (minPosVals.size() < 3) {
                    minPosVals.add(num);
                } else {
                    if (num < minPosVals.peek()) {
                        minPosVals.poll();
                        minPosVals.add(num);
                    }
                }
            } else if (num < 0) {
                if (minNegVals.size() < 3) {
                    minNegVals.add(num);
                } else {
                    if (num < minNegVals.peek()) {
                        minNegVals.poll();
                        minNegVals.add(num);
                    }
                }
                if (maxNegVals.size() < 3) {
                    maxNegVals.add(num);
                } else {
                    if (num > maxNegVals.peek()) {
                        maxNegVals.poll();
                        maxNegVals.add(num);
                    }
                }
            } else {
                hasZero = true;
            }
        }

        List<Integer> maxPosList = new ArrayList<>(maxPosVals);
        Collections.reverse(maxPosList);
        List<Integer> minPosList = new ArrayList<>(minPosVals);
        Collections.reverse(minPosList);
        List<Integer> maxNegList = new ArrayList<>(maxNegVals);
        Collections.reverse(maxNegList);
        List<Integer> minNegList = new ArrayList<>(minNegVals);
        Collections.reverse(minNegList);

        int max = Integer.MIN_VALUE;
        max = Math.max(max, maxPosList.size() == 3 ? maxPosList.get(0) * maxPosList.get(1) * maxPosList.get(2) : max);
        max = Math.max(max, maxNegList.size() == 3 ? maxNegList.get(0) * maxNegList.get(1) * maxNegList.get(2) : max);
        max = Math.max(max, minNegList.size() >= 2 && maxPosList.size() >= 1 ? minNegList.get(0) * minNegList.get(1) * maxPosList.get(0) : max);
        max = Math.max(max, maxNegList.size() >= 1 && minPosList.size() >= 2 ? maxNegList.get(0) * minPosList.get(0) * minPosList.get(1) : max);

        return hasZero ? Math.max(0, max) : max;
    }

}
