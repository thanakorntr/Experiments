package LeetCode;

import java.util.Arrays;

/**
 * Created by Thanakorn on 2/9/16.
 */
public class Partition {

    public static void main(String[] args) {

        int[] nums = new int[]{3, 5, 1, 2, 6, 4};
        System.out.println(partition(nums, 0, nums.length - 1));
        System.out.println(Arrays.toString(nums));
    }

    // use nums[right] as a pivot
    private static int partition(int[] nums, int left, int right) {
        if (nums == null || nums.length == 0 || left > right) {
            throw new IllegalArgumentException();
        }

        int pivot = nums[right];
        int l = left;
        int r = right - 1;
        while (l <= r) {
            while (l <= r && nums[l] < pivot) {
                l++;
            }
            while (r >= l && nums[r] >= pivot) {
                r--;
            }
            if (l > r) {
                break;
            }
            swap(nums, l, r);
        }
        swap(nums, l, right);
        return l;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
