package LeetCode;

import java.util.Arrays;

/**
 * Created by Thanakorn on 5/28/15.
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,1};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    public static void quickSort(int[] nums) {
        quickSort2(nums, 0, nums.length - 1);
    }

    public static void quickSort1(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivotIndex = partition1(nums, start, end);
        quickSort1(nums, start, pivotIndex - 1);
        quickSort1(nums, pivotIndex + 1, end);
    }

    public static void quickSort2(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int i = start, j = end;
        int pivot = nums[end];
        while (i <= j) {
            while (nums[i] < pivot) {
                i++;
            }
            while (nums[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        quickSort2(nums, start, j);
        quickSort2(nums, i, end);
    }

    public static int partition1(int[] nums, int start, int end) {
        int pivotElem = nums[end];

        while (start < end) {
            if (nums[start] <= pivotElem) {
                start++;
            } else {
                nums[end] = nums[start];
                nums[start] = nums[end - 1];
                end--;
            }
        }

        if (start != end) {
            System.err.println("ERROR");
        }

        nums[end] = pivotElem;

        return end;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
