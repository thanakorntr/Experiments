package LeetCode;

/**
 * Created by Thanakorn on 8/22/15.
 *
 *  Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

 The replacement must be in-place, do not allocate extra memory.

 Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 */
public class NextPermutation {

    public static void main(String[] args) {

    }

    // 2 7 6 5 3 4
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int i1 = -1, i2 = -1;
        for (int i = nums.length-2; i >= 0; i--) {
            for (int j = nums.length-1; j > i; j--) {
                if (nums[i] < nums[j]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                    return;
                }
            }
        }

        int left = 0, right = nums.length-1;
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }
}
