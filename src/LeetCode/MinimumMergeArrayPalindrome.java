package LeetCode;

/**
 * http://www.geeksforgeeks.org/find-minimum-number-of-merge-operations-to-make-an-array-palindrome/
 * <p>
 * Find minimum number of merge operations to make an array palindrome
 * Given an array of positive integers. We need to make the given array a ‘Palindrome’. Only allowed operation on array is merge. Merging two adjacent elements means replacing them with their sum. The task is to find minimum number of merge operations required to make given array a ‘Palindrome’.
 * <p>
 * To make an array a palindromic we can simply apply merging operations n-1 times where n is the size of array (Note a single element array is alway palindrome similar to single character string). In that case, size of array will be reduced to 1. But in this problem we are asked to do it in minimum number of operations.
 * <p>
 * Example:
 * <p>
 * Input : arr[] = {15, 4, 15}
 * Output : 0
 * Array is already a palindrome. So we
 * do not need any merge operation.
 * <p>
 * Input : arr[] = {1, 4, 5, 1}
 * Output : 1
 * We can make given array palindrome with
 * minimum one merging (merging 4 and 5 to
 * make 5)
 * <p>
 * Input : arr[] = {11, 14, 15, 99}
 * Output : 3
 * We need to merge all elements to make
 * a palindrome.
 * Expected time complexity is O(n).
 * <p>
 * Created by Thanakorn on 5/8/16.
 */
public class MinimumMergeArrayPalindrome {

    public static void main(String[] args) {
        MinimumMergeArrayPalindrome minimumMergeArrayPalindrome = new MinimumMergeArrayPalindrome();

        int[] nums = new int[]{15, 4, 15};
        System.out.println(minimumMergeArrayPalindrome.minimumMergeOperation(nums));  // 0

        nums = new int[]{1, 4, 5, 1};
        System.out.println(minimumMergeArrayPalindrome.minimumMergeOperation(nums));  // 1

        nums = new int[]{11, 14, 15, 99};
        System.out.println(minimumMergeArrayPalindrome.minimumMergeOperation(nums));  // 3
    }

    public int minimumMergeOperation(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int left = 0;
        int right = nums.length - 1;
        int numOperation = 0;

        while (left < right) {
            if (nums[left] > nums[right]) {
                nums[right - 1] += nums[right];
                numOperation++;
                right--;
            } else if (nums[left] < nums[right]) {
                nums[left + 1] += nums[left];
                numOperation++;
                left++;
            } else {
                left++;
                right--;
            }
        }

        return numOperation;
    }

}
