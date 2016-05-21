package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two arrays, write a function to compute their intersection.

 Example:
 Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

 Note:
 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.
 Follow up:
 What if the given array is already sorted? How would you optimize your algorithm?
 What if nums1's size is small compared to num2's size? Which algorithm is better?
 What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 *
 * Created by Thanakorn on 5/20/16.
 */
public class IntersectionOfTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> num1MapCount = getMapCount(nums1);
        Map<Integer, Integer> num2MapCount = getMapCount(nums2);

        Map<Integer, Integer> smallerMapCount = num1MapCount.size() < num2MapCount.size() ? num1MapCount : num2MapCount;
        Map<Integer, Integer> biggerMapCount = smallerMapCount == num1MapCount ? num2MapCount : num1MapCount;

        Map<Integer, Integer> intersectMapCount = new HashMap<>();
        int totalNum = 0;

        for (int i : smallerMapCount.keySet()) {
            if (biggerMapCount.containsKey(i)) {
                int minCount = Math.min(smallerMapCount.get(i), biggerMapCount.get(i));
                intersectMapCount.put(i, minCount);
                totalNum += minCount;
            }
        }

        int index = 0;
        int[] intersect = new int[totalNum];
        for (int i : intersectMapCount.keySet()) {
            int count = intersectMapCount.get(i);
            for (int j = 0; j < count; j++) {
                intersect[index++] = i;
            }
        }

        return intersect;
    }

    private Map<Integer, Integer> getMapCount(int[] nums) {
        Map<Integer, Integer> mapCount = new HashMap<>();
        for (int i : nums) {
            int count = mapCount.getOrDefault(i, 0);
            mapCount.put(i, count + 1);
        }
        return mapCount;
    }

}
