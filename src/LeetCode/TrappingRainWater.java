package LeetCode;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

 For example,
 Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 *
 * Created by Thanakorn on 5/20/16.
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trappingRainWater.trap(height));
    }

    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        maxLeft[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }
        maxRight[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
        }

        int trappedWater = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int minWallHeight = Math.min(maxLeft[i], maxRight[i]);
            if (minWallHeight > height[i]) {
                trappedWater += minWallHeight - height[i];
            }
        }

        return trappedWater;
    }

}
