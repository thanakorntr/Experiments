package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 8/9/15.
 */
public class FourSum {

    public static void main(String[] args) {

        int[] s = {1,0,-1,0,-2,2};
        int target = 0;
        List<List<Integer>> ans = fourSum(s, target);
        ans.forEach(x -> System.out.println(x.toString()));

    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return ans;
        }

        Set<List<Integer>> visitedAns = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-3; i++) {
            for (int j = i+1; j < nums.length-2; j++) {
                int left = j+1;
                int right = nums.length-1;
                int newTarget = target - nums[i] - nums[j];
                while (left < right) {
                    if (nums[left] + nums[right] == newTarget) {
                        if (!visitedAns.contains(Arrays.asList(nums[i], nums[j], nums[left], nums[right]))) {
                            visitedAns.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                            ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        }
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < newTarget) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return ans;
    }
}
