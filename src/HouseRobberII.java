import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanakorn on 7/25/15.
 */
public class HouseRobberII {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int[] a = Arrays.copyOfRange(nums, 1, nums.length);
        System.out.println(Arrays.toString(a));
    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        if (nums.length == 3) {
            return Math.max(nums[0], Math.max(nums[1], nums[2]));
        }

        // case1
        int[] nums1 = Arrays.copyOfRange(nums, 2, nums.length-1);
        Map<Integer, Integer> memo = new HashMap<>();
        int score1 = nums[0] + robHelper(nums1, 0, memo);

        // case2
        memo = new HashMap<>();
        int score2 = robHelper(nums, 1, memo);

        return Math.max(score1, score2);
    }

    public static int robHelper(int[] nums, int startIndex, Map<Integer, Integer> memo) {
        if (startIndex >= nums.length) {
            return 0;
        }

        if (memo.containsKey(startIndex)) {
            return memo.get(startIndex);
        }

        // case 1
        if (!memo.containsKey(startIndex+2)) {
            memo.put(startIndex+2, robHelper(nums, startIndex+2, memo));
        }
        int score1 = nums[startIndex] + memo.get(startIndex+2);

        // case2
        if (!memo.containsKey(startIndex+1)) {
            memo.put(startIndex+1, robHelper(nums, startIndex+1, memo));
        }
        int score2 = memo.get(startIndex+1);

        return Math.max(score1, score2);
    }
}
