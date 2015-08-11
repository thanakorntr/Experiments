package LeetCode;

/**
 * Created by Thanakorn on 5/28/15.
 */
public class QuickSort {


    public static void main(String[] args) {

    }

    public static void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivotIndex = partition(nums, start, end);
        quickSort(nums, start, pivotIndex-1);
        quickSort(nums, pivotIndex+1, end);
    }

    public static int partition(int[] nums, int start, int end) {
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

        if (start != end){
            System.err.println("ERROR");
        }

        nums[end] = pivotElem;

        return end;
    }
}
