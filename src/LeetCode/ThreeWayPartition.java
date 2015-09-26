package LeetCode;

import java.util.Arrays;

/**
 * Created by Thanakorn on 9/26/15.
 */
public class ThreeWayPartition {

    public static void main(String[] args) {

        eval(10);

    }

    private static void eval(int size) {
        int[] nums = generateRandomArray(size);
        System.out.println("Original array: " + Arrays.toString(nums));
        partition3Ways(nums);
        System.out.println("Sorted 3 ways: " + Arrays.toString(nums));
    }

    private static void partition3Ways(int[] nums) {
        // 0:  0 - low-1
        // 1:  low - mid-1
        // 2:  high - size-1

        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else if (nums[mid] == 2) {
                swap(nums, mid, high);
                high--;
            }
        }

    }

    private static int[] generateRandomArray(int size) {
        if (size <= 0) {
            return null;
        }

        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            double rnd = Math.random();
            if (rnd < 1D / 3) {
                nums[i] = 0;
            } else if (rnd >= 1D / 3 && rnd < 2D / 3) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
        return nums;
    }

    private static void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}
