package LeetCode;

/**
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.

 You may assume no duplicates in the array.

 Here are few examples.
 [1,3,5,6], 5 → 2
 [1,3,5,6], 2 → 1
 [1,3,5,6], 7 → 4
 [1,3,5,6], 0 → 0
 *
 * Created by Thanakorn on 2/18/16.
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                if (mid == 0 || nums[mid - 1] < target) {
                    return mid;
                }
                if (nums[mid - 1] == target) {
                    return mid - 1;
                }
                right = mid - 1;
            } else {
                if (mid == nums.length - 1 || nums[mid + 1] >= target) {
                    return mid + 1;
                }
                left = mid + 1;
            }
        }

        return 0;
    }

}
