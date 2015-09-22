package LeetCode;

/**
 * Created by Thanakorn on 9/21/15.
 *
 * http://stackoverflow.com/questions/20550037/find-maximum-product-of-3-numbers-in-an-array
 *
 * int[] arr = {-5, -7, 4, 2, 1, 9};  // Max Product of 3 numbers = -5 * -7 * 9
    int[] arr2 = {4, 5, -19, 3};       // Max Product of 3 numbers = 4 * 5 * 3
 */
public class MaxProductOf3NumsInArray {

    public static void main(String[] args) {

        int[] nums = new int[]{-5,-7,4,2,1,9};
        System.out.println(maxProduct(nums)); // 315
        nums = new int[]{4,5,-19,3};
        System.out.println(maxProduct(nums)); // 60

    }


    private static int maxProduct(int[] nums) {
        if (nums.length < 3) {
            return Integer.MIN_VALUE;
        }

        if (nums.length == 3) {
            return nums[0] * nums[1] * nums[2];
        }

        int maxPos1 = -1, maxPos2 = -2, maxPos3 = -3;
        int minNeg1 = 1, minNeg2 = 2;

        for (int i : nums) {
            if (i >= 0) {
                if (i > maxPos1) {
                    maxPos3 = maxPos2;
                    maxPos2 = maxPos1;
                    maxPos1 = i;
                } else if (i > maxPos2 && i < maxPos1) {
                    maxPos3 = maxPos2;
                    maxPos2 = i;
                } else if (i > maxPos3 && i < maxPos2) {
                    maxPos3 = i;
                }
            } else {

            }
        }



        return 0;
    }
}
