package LeetCode;

import java.util.Arrays;

/**
 * Created by Thanakorn on 7/1/15.
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        int[] nums = {1,1,1,1};
        int target = 0;

        System.out.println(threeSumClosest(nums, target));
    }

    public static int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) {
            return -1;
        }

        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[nums.length-1];

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                }

                if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    closestSum = sum;
                }
            }
        }

        return closestSum;
    }

}
