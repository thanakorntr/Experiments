package LeetCode;

import java.util.Arrays;

/**
 * https://www.glassdoor.com/Interview/The-accent-of-the-interviewer-was-very-hard-to-understand-Given-an-array-with-N-2-elements-two-missing-from-1-to-N-f-QTN_602503.htm
 *
 * Given an array with N - 2 elements (two missing) from 1 to N, find the two missing
 * elements in linear time and constant memory usage.
 *
 * Created by Thanakorn on 6/11/16.
 */
public class MissingTwoNums {

    public static void main(String[] args) {

        int[] nums = new int[]{1,4};
        System.out.println(Arrays.toString(findMissingTwoNums(nums))); // 2,3

        nums = new int[]{2,4};
        System.out.println(Arrays.toString(findMissingTwoNums(nums))); // 1,3

        nums = new int[]{1,2};
        System.out.println(Arrays.toString(findMissingTwoNums(nums))); // 3,4

        nums = new int[]{2,3};
        System.out.println(Arrays.toString(findMissingTwoNums(nums))); // 1,4

        nums = new int[]{2,4,3};
        System.out.println(Arrays.toString(findMissingTwoNums(nums))); // 1,5

        nums = new int[]{1,2,4,6};
        System.out.println(Arrays.toString(findMissingTwoNums(nums))); // 3,5
    }

    private static int[] findMissingTwoNums(int[] nums) {
        if (nums == null) {
            throw new NullPointerException();
        }

        int[] missings = new int[2];

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != 1 && nums[i] != 2 && nums[i] - 3 != i) {
                int tmp = nums[nums[i] - 3];
                nums[nums[i] - 3] = nums[i];
                nums[i] = tmp;
            }
        }

        int firstMiss = -1, secondMiss = -1;
        boolean oneMissing = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1 || nums[i] == 2) {
                if (firstMiss == -1) {
                    firstMiss = i + 3;
                    oneMissing = nums[i] == 2;
                } else {
                    secondMiss = i + 3;
                    oneMissing = false;
                }
            }
        }

        if (firstMiss == -1 || secondMiss == -1) {
            if (firstMiss == -1) {
                firstMiss = 1;
                secondMiss = 2;
            } else {
                secondMiss = oneMissing ? 1 : 2;
            }
        }

        missings[0] = firstMiss;
        missings[1] = secondMiss;
        return missings;
    }

}
