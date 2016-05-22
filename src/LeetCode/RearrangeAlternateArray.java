package LeetCode;

import java.util.Arrays;

/**
 * Created by Thanakorn on 5/22/16.
 */
public class RearrangeAlternateArray {

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

        System.out.println("-----");

        nums = new int[]{1, 2, 3, -4, -1, 4};
        System.out.println(Arrays.toString(nums));
        rearrange(nums);
        System.out.println(Arrays.toString(nums));

        System.out.println("-----");

        nums = new int[]{-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
        System.out.println(Arrays.toString(nums));
        rearrange(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void rearrange(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {  // expect neg
                if (nums[i] >= 0) {
                    int swapIndex = i;
                    for (int j = i + 1; j < nums.length; j++) {
                        if (nums[j] < 0) {
                            swapIndex = j;;
                            break;
                        }
                    }
                    swap(nums, i, swapIndex);
                    rotateRight(nums, i + 1, swapIndex);
                }
            } else {  // expect pos
                if (nums[i] < 0) {
                    int swapIndex = i;
                    for (int j = i + 1; j < nums.length; j++) {
                        if (nums[j] >= 0) {
                            swapIndex = j;
                            break;
                        }
                    }
                    swap(nums, i, swapIndex);
                    rotateRight(nums, i + 1, swapIndex);
                }
            }
        }
    }

    private static void rotateRight(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int tmp = nums[start];
        nums[start] = nums[end];
        for (int i = start + 1; i <= end; i++) {
            int tmp2 = nums[i];
            nums[i] = tmp;
            tmp = tmp2;
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
