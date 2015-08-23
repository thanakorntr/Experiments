package LeetCode;

import java.util.Arrays;

/**
 * Created by Thanakorn on 8/22/15.
 *
 *  Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

 The replacement must be in-place, do not allocate extra memory.

 Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 */
public class NextPermutation {

    public static void main(String[] args) {

        int[] nums = {1,3,2};
        nextPermutation(nums);  // [2,1,3]
        System.out.println(Arrays.toString(nums));
        nums = new int[]{2,3,1};
        nextPermutation(nums);  // [3,1,2]
        System.out.println(Arrays.toString(nums));

    }

    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int startIndex = -1;
        for (int i = nums.length-2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) {
                startIndex = i;
                break;
            }
        }

        if (startIndex == -1) {
            reverse(nums, 0, nums.length-1);
            return;
        }

        for (int i = nums.length-1; i > startIndex; i--) {
            if (nums[i] > nums[startIndex]) {
                swap(nums, startIndex, i);
                reverse(nums, startIndex+1, nums.length-1);
                return;
            }
        }
    }

    public static void reverse(int[] nums, int left, int right) {
        if (left >= right) return;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}
