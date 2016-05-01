package LeetCode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Given two arrays of length m and n with digits 0-9 representing two numbers.
 * Create the maximum number of length k <= m + n from digits of the two.
 * The relative order of the digits from the same array must be preserved.
 * Return an array of the k digits. You should try to optimize your time and space complexity.
 * <p>
 * Example 1:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * return [9, 8, 6, 5, 3]
 * <p>
 * Example 2:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * return [6, 7, 6, 0, 4]
 * <p>
 * Example 3:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * return [9, 8, 9]
 * <p>
 * Created by Thanakorn on 12/24/15.
 */
public class CreateMaximumNumber {

    public static void main(String[] args) {
        CreateMaximumNumber createMaximumNumber = new CreateMaximumNumber();
        int[] nums1 = new int[]{3, 4, 6, 5};
        int[] nums2 = new int[]{9, 1, 2, 5, 8, 3};
        int k = 5;
        // 9 8 6 5 3
        System.out.println(Arrays.toString(createMaximumNumber.maxNumber(nums1, nums2, k)));

        nums1 = new int[]{6, 7};
        nums2 = new int[]{6, 0, 4};
        k = 5;
        // 6 7 6 0 4
        System.out.println(Arrays.toString(createMaximumNumber.maxNumber(nums1, nums2, k)));

        nums1 = new int[]{3, 9};
        nums2 = new int[]{8, 9};
        k = 3;
        // 9 8 9
        System.out.println(Arrays.toString(createMaximumNumber.maxNumber(nums1, nums2, k)));

        nums1 = new int[]{2,5,6,4,4,0};
        nums2 = new int[]{7,3,8,0,6,5,7,6,2};
        k = 15;
        // 7,3,8,2,5,6,4,4,0,6,5,7,6,2,0
        System.out.println(Arrays.toString(createMaximumNumber.maxNumber(nums1, nums2, k)));

    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums2 == null) {
            throw new IllegalArgumentException();
        }
        int[] max = null;
        for (int numElementInNums1 = 0;
             numElementInNums1 <= Math.min(k, nums1.length);
             numElementInNums1++) {
            int numRemainingInNums2 = k - numElementInNums1;
            if (numRemainingInNums2 > nums2.length || numRemainingInNums2 < 0) {
                continue;
            }
            int[] max1 = createMax(nums1, numElementInNums1);
            int[] max2 = createMax(nums2, numRemainingInNums2);
            int[] merged = merge(max1, max2);
            if (max == null) {
                max = merged;
            } else {
                if (compare(max, 0, merged, 0) == -1) {
                    max = merged;
                }
            }
        }
        return max;
    }

    private int[] createMax(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            throw new IllegalArgumentException();
        }
        int[] maxArray = new int[k];
        int index = 0;
        int startIndex = 0;
        Deque<Integer> deque = new LinkedList<>();
        while (index < k) {
            int lastIndex = nums.length - k + index;
            while (startIndex <= lastIndex) {
                while (!deque.isEmpty() && deque.peekLast() < nums[startIndex]) {
                    deque.pollLast();
                }
                deque.add(nums[startIndex]);
                startIndex++;
            }

            maxArray[index] = deque.pollFirst();
            index++;
        }
        return maxArray;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            throw new IllegalArgumentException();
        }
        if (nums1.length == 0 || nums2.length == 0) {
            if (nums1.length == 0) {
                return nums2;
            } else {
                return nums1;
            }
        }
        int k = nums1.length + nums2.length;
        int[] mergedArray = new int[k];
        int index = 0;
        int index1 = 0;
        int index2 = 0;
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] > nums2[index2]) {
                mergedArray[index++] = nums1[index1++];
            } else if (nums1[index1] < nums2[index2]) {
                mergedArray[index++] = nums2[index2++];
            } else {
                int index11 = index1 + 1;
                int index22 = index2 + 1;
                if (compare(nums1, index11, nums2, index22) <= 0) {
                    mergedArray[index++] = nums2[index2++];
                } else {
                    mergedArray[index++] = nums1[index1++];
                }
            }
        }
        while (index1 < nums1.length) {
            mergedArray[index++] = nums1[index1++];
        }
        while (index2 < nums2.length) {
            mergedArray[index++] = nums2[index2++];
        }
        return mergedArray;
    }

    private int compare(int[] nums1, int start1, int[] nums2, int start2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            throw new IllegalArgumentException();
        }

        if (start1 >= nums1.length) {
            return -1;
        }
        if (start2 >= nums2.length) {
            return 1;
        }

        int i , j;
        for (i = start1, j = start2; i < nums1.length && j < nums2.length; i++, j++) {
            if (nums1[i] < nums2[j]) {
                return -1;
            } else if (nums1[i] > nums2[j]) {
                return 1;
            }
        }

        if (i < nums1.length) {
            return 1;
        } else if (j < nums2.length) {
            return -1;
        }

        return 0;
    }

}
