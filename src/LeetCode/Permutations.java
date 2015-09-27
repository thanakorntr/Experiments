package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thanakorn on 9/27/15.
 *
 *  Given a collection of numbers, return all possible permutations.

 For example,
 [1,2,3] have the following permutations:
 [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 */
public class Permutations {

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3};
        List<int[]> permutations = permute(nums);
        for (int[] permute : permutations) {
            System.out.println(Arrays.toString(permute));
        }
    }

    private static List<int[]> permute(int[] nums) {
        List<int[]> ans = new ArrayList<>();
        permuteHelper(nums, 0, ans);
        return ans;
    }

    private static void permuteHelper(int[] nums, int startIndex, List<int[]> ans) {
        if (startIndex == nums.length - 1) {
            ans.add(Arrays.copyOf(nums,nums.length));
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            swap(nums, startIndex, i);
            permuteHelper(nums, startIndex+1, ans);
            swap(nums, startIndex, i);
        }
    }

    private static void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}
