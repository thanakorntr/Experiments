package LeetCode;

/**
 * Created by Thanakorn on 10/24/15.
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Your goal is to reach the last index in the minimum number of jumps.

 For example:
 Given array A = [2,3,1,1,4]

 The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */
public class JumpGameII {

    public static void main(String[] args) {

        int[] nums = {2,3,1,1,4};
        System.out.println(jumpNSquare(nums)); // 2

    }

    // bfs
    private static int jump(int[] nums) {

        if (nums == null || nums.length < 2) {
            return 0;
        }

        int curIndex = 0;
        int maxJumpIndexCurLevel = 0;
        int curLevel = 1;

        while (maxJumpIndexCurLevel - curIndex + 1 > 0) {
            int maxJumpIndexNextLevel = maxJumpIndexCurLevel;
            while (curIndex <= maxJumpIndexCurLevel) {
                if (nums[curIndex] + curIndex >= nums.length - 1) {
                    return curLevel;
                }
                maxJumpIndexNextLevel = Math.max(maxJumpIndexNextLevel, nums[curIndex] + curIndex);
                curIndex++;
            }
            maxJumpIndexCurLevel = maxJumpIndexNextLevel;
            curLevel++;
        }

        return 0;
    }

    /**
     * Time limit exceeded.
     */
    private static int jumpNSquare(int[] nums) {

        int[] memo = new int[nums.length];
        memo[memo.length - 1] = 0;

        for (int i = memo.length - 2; i >= 0; i--) {
            int maxIndexAfterJump = Math.min(i + nums[i], nums.length - 1);
            int minJump = Integer.MAX_VALUE;
            for (int j = i +  1; j <= maxIndexAfterJump; j++) {
                if (memo[j] != -1) {
                    minJump = Math.min(minJump, memo[j]);
                }
            }
            if (minJump == Integer.MAX_VALUE) {
                memo[i] = -1;
            } else {
                memo[i] = 1 + minJump;
            }
        }

        return memo[0] != -1 ? memo[0] : -1;
    }
}
