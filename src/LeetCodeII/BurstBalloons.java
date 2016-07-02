package LeetCodeII;

/**
 * Created by Thanakorn on 7/1/16.
 */
public class BurstBalloons {

    public static void main(String[] args) {
        BurstBalloons burstBalloon = new BurstBalloons();
        int[] nums = new int[]{3,1,5,8};
        System.out.println(burstBalloon.maxCoins(nums));  // 167
    }

    public int maxCoins(int[] iNums) {
        if (iNums == null || iNums.length == 0) {
            return 0;
        }

        int[] nums = new int[iNums.length + 2];
        nums[0] = 1;
        nums[nums.length - 1] = 1;
        System.arraycopy(iNums, 0, nums, 1, iNums.length);

        int[][] memo = new int[nums.length][nums.length];
        for (int i = memo.length - 1; i >= 0; i--) {
            for (int j = i; j < memo.length; j++) {
                if (j > i + 1) {
                    int maxVal = Integer.MIN_VALUE;
                    for (int k = i + 1; k < j; k++) {
                        maxVal = Math.max(maxVal, memo[i][k] + memo[k][j] + nums[k] * nums[i] * nums[j]);
                    }
                    memo[i][j] = maxVal;
                }
            }
        }

        return memo[0][memo.length - 1];
    }

}
