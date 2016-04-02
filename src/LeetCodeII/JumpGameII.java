package LeetCodeII;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *  Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Your goal is to reach the last index in the minimum number of jumps.

 For example:
 Given array A = [2,3,1,1,4]

 The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

 Note:
 You can assume that you can always reach the last index.
 *
 * Created by Thanakorn on 4/2/16.
 */
public class JumpGameII {

    public int jump(int[] nums) {

        if (nums == null || nums.length < 2) {
            return 0;
        }

        int numJump = 1;
        int farthestJumpIndex = 0;
        Queue<Integer> curLevel = new LinkedList<>();
        Set<Integer> visitedIndices = new HashSet<>();
        visitedIndices.add(0);
        curLevel.add(0);

        while (!curLevel.isEmpty()) {
            Queue<Integer> nextLevel = new LinkedList<>();
            while (!curLevel.isEmpty()) {
                int curIndex = curLevel.poll();
                int curJump = nums[curIndex];
                for (int i = curJump; i >=0 ; i--) {
                    int nextIndex = curIndex + i;
                    if (visitedIndices.contains(nextIndex)) {
                        continue;
                    }
                    farthestJumpIndex = Math.max(farthestJumpIndex, nextIndex);
                    if (farthestJumpIndex >= nums.length - 1) {
                        return numJump;
                    }
                    visitedIndices.add(nextIndex);
                    nextLevel.add(nextIndex);
                }
            }
            curLevel = nextLevel;
            numJump++;
        }

        return -1;
    }

}
