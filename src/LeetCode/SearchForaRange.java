package LeetCode;

import java.util.Arrays;

/**
 * Created by Thanakorn on 9/12/15.
 *
 * Given a sorted array of integers, find the starting and ending position of a given target value.

 Your algorithm's runtime complexity must be in the order of O(log n).

 If the target is not found in the array, return [-1, -1].

 For example,
 Given [5, 7, 7, 8, 8, 10] and target value 8,
 return [3, 4].
 */
public class SearchForaRange {

    public static void main(String[] args) {

        int[] nums = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(nums, 8))); // [3, 4]

    }

    public static int[] searchRange(int[] nums, int target) {

        int[] indices = new int[2];
        int leftIndex = findLeftMost(nums, target);
        if (leftIndex == -1) {
            indices[0] = -1;
            indices[1] = -1;
            return indices;
        }
        int rightIndex = findRightMost(nums, target);
        indices[0] = leftIndex;
        indices[1] = rightIndex;
        return indices;
    }

    private static int findLeftMost(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid =  left + (right - left) / 2;
            if (nums[mid] == target) {
                if (mid == 0 || nums[mid-1] != target) {
                    return mid;
                }
                right = mid-1;
            } else if (nums[mid] > target) {
                right = mid-1;
            } else if (nums[mid] < target) {
                left = mid+1;
            }
        }

        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    private static int findRightMost(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid =  left + (right - left) / 2;
            if (nums[mid] == target) {
                if (mid == nums.length-1 || nums[mid+1] != target) {
                    return mid;
                }
                left = mid+1;
            } else if (nums[mid] > target) {
                right = mid-1;
            } else if (nums[mid] < target) {
                left = mid+1;
            }
        }

        if (nums[left] == target) {
            return left;
        }
        return -1;
    }
}
