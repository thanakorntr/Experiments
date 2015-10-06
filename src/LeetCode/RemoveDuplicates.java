package LeetCode;

import java.util.Arrays;

/**
 * Created by Thanakorn on 10/2/15.
 *
 *  Follow up for "Remove Duplicates":
 What if duplicates are allowed at most twice?

 For example,
 Given sorted array nums = [1,1,1,2,2,3],

 Your function should return length = 5, with the first five elements of
 nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicates {

    public static void main(String[] args) {

        int[] nums = new int[] {1,1,1,2,2,3};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));

    }

    private static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n;
            }
        }
        return i;
    }

    private static int removeDuplicates2(int[] nums) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i < 2) {
                count++;
            } else {
                if (nums[i] > nums[count-2]) {
                    nums[count] = nums[i];
                    count++;
                }
            }
        }

        return count;
    }
}
