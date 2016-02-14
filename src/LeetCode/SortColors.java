package LeetCode;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

 Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

 Note:
 You are not suppose to use the library's sort function for this problem.
 *
 * Created by Thanakorn on 2/14/16.
 */
public class SortColors {

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int low = 0, mid = 0, high = nums.length-1;
        // 0: 0 - low-1
        // 1: low - mid-1
        // unknown: mid - high
        // 2: high+1 - nums.length-1

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }

}
