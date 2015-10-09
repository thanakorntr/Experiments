package LeetCode;

/**
 * Created by Thanakorn on 10/8/15.
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        new SearchInRotatedSortedArray().search(new int[]{8,9,2,3,4}, 9);
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int pivot = findPivot(nums);

        int index = binarySearch(nums, 0, pivot, target);
        if (index != -1) {
            return index;
        }
        if (pivot == nums.length-1) {
            return -1;
        }
        index = binarySearch(nums, pivot+1, nums.length-1, target);
        if (index != -1) {
            return index;
        }
        return -1;
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[left] == target ? left : -1;
    }

    private int findPivot(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid < nums.length && nums[mid] > nums[mid+1]) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return left;
    }
}
