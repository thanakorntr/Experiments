package LeetCode;

/**
 * http://www.geeksforgeeks.org/absolute-distinct-count-array-sorted-absolute-values/
 *
 * Created by Thanakorn on 5/22/16.
 */
public class AbsoluteDistinctCountInaSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[] {-3, -2, 0, 3, 4, 5};
        System.out.println(countAbsoluteDistinct(nums));  // 5

        nums = new int[] {-1, -1, -1, -1, 0, 1, 1, 1, 1};
        System.out.println(countAbsoluteDistinct(nums));  // 2

        nums = new int[] {-1, -1, -1, -1, 0};
        System.out.println(countAbsoluteDistinct(nums));  // 2

        nums = new int[] {0, 0, 0};
        System.out.println(countAbsoluteDistinct(nums));  // 1
    }

    private static int countAbsoluteDistinct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = nums.length;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] == nums[left + 1]) {
                left++;
                count--;
            }
            if (left == right) {
                break;
            }
            while (left < right && nums[right] == nums[right - 1]) {
                right--;
                count--;
            }
            if (left == right) {
                break;
            }

            int sum = nums[left] + nums[right];
            if (sum == 0) {
                left++;
                right--;
                count--;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        return count;
    }

}
