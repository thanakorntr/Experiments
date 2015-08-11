package LeetCode;

/**
 * Created by Thanakorn on 6/28/15.
 */
public class JumpGame {

    public static void main(String[] args) {

        int[] A = {2,3,1,1,4}; // true
         A =  new int[]{3,2,1,0,4};  // false

        System.out.println(canJump(A));
    }

    public static boolean canJump(int[] nums) {
        int farthestIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > farthestIndex) {
                return false;
            }
            farthestIndex = Math.max(farthestIndex, nums[i] + i);
        }
        return true;
    }

}
