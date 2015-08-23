package LeetCode;

import java.util.Arrays;

/**
 * Created by Thanakorn on 8/23/15.
 *
 * Given an array and a value, remove all instances of that value in place and return the new length.

 The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */
public class RemoveElement {

    public static void main(String[] args) {

        int[] nums = {1,2,3,1};
        int count = removeElement(nums, 1);
        System.out.println(count);
        System.out.println(Arrays.toString(nums));

    }

    public static int removeElement(int[] nums, int val) {
        if (nums == null) {
            return 0;
        }

        int count = 0;

        int left = 0, right = nums.length-1;
        while (left <= right) {
            if (nums[left] == val) {
                while (nums[right] == val && right > left) {
                    right--;
                }
                if (right > left) {
                    nums[left] = nums[right];
                    count++;
                    right--;
                } else {
                    break;
                }
            } else {
                count++;
            }
            left++;
        }

        return count;
    }
}
