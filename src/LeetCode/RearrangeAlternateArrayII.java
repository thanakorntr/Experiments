package LeetCode;

import java.util.Arrays;

/**
 * Created by Thanakorn on 5/22/16.
 */
public class RearrangeAlternateArrayII {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 3, 4, -1};
        System.out.println(Arrays.toString(nums));
        rearrange(nums);
        System.out.println(Arrays.toString(nums));

        System.out.println("-----");

        nums = new int[]{-2, 3, 1};
        System.out.println(Arrays.toString(nums));
        rearrange(nums);
        System.out.println(Arrays.toString(nums));

        System.out.println("-----");

        nums = new int[]{-5, 3, 4, 5, -6, -2, 8, 9, -1, -4};
        System.out.println(Arrays.toString(nums));
        rearrange(nums);
        System.out.println(Arrays.toString(nums));

        System.out.println("-----");

        nums = new int[]{-5, -4, -2, -1, 1, 2, -3, -5};
        System.out.println(Arrays.toString(nums));
        rearrange(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void rearrange(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (nums[left] >= 0) {
                left++;
            }
            while (nums[right] < 0) {
                right--;
            }
            if (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        left = 0;
        right = right < 0 ? right + 1 : right;
        if (nums[right] >= 0) {
            right++;
        }
        while (right < nums.length && left < right) {
            swap(nums, left, right);
            right++;
            left += 2;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        if (i < 0 || i >= nums.length || j < 0 || j >= nums.length) {
            throw new IndexOutOfBoundsException();
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
