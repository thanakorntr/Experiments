package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers with possible duplicates, randomly output the index of a given target number.
 * You can assume that the given target number must exist in the array.

 Note:
 The array size can be very large. Solution that uses too much extra space will not pass the judge.

 Example:

 int[] nums = new int[] {1,2,3,3,3};
 Solution solution = new Solution(nums);

 // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 solution.pick(3);

 // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 solution.pick(1);
 *
 * Created by Thanakorn on 9/17/16.
 */
public class RandomPickIndex {

    private Map<Integer, List<Integer>> data;

    public RandomPickIndex(int[] nums) {
        data = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!data.containsKey(nums[i])) {
                data.put(nums[i], new ArrayList<>());
            }
            data.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> indices = data.get(target);
        int ramdomIndex = (int)(Math.random() * indices.size());
        return indices.get(ramdomIndex);
    }

}
