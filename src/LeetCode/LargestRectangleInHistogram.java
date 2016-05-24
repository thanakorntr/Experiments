package LeetCode;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * Created by Thanakorn on 5/22/16.
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        LargestRectangleInHistogram largestRectangleInHistogram = new LargestRectangleInHistogram();
        int[] heights = new int[]{2,1,5,6,2,3};
        System.out.println(largestRectangleInHistogram.largestRectangleArea(heights));  // 10
    }

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        return largestRectangleAreaHelper(heights, 0, heights.length - 1);
    }

    private int largestRectangleAreaHelper(int[] heights, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return heights[startIndex];
        }

        int mid = startIndex + (endIndex - startIndex) / 2;

        int maxLeft = largestRectangleAreaHelper(heights, startIndex, mid);
        int maxRight = largestRectangleAreaHelper(heights, mid + 1, endIndex);

        int maxMid = 0;
        int left = mid;
        int right = mid + 1;
        int minHeight = Math.min(heights[left], heights[right]);
        while (left >= startIndex && right <= endIndex) {
            minHeight = Math.min(minHeight, Math.min(heights[left], heights[right]));
            maxMid = Math.max(maxMid, (right - left + 1) * minHeight);
            if (left - 1 >= startIndex && right + 1 <= endIndex) {
                if (heights[left - 1] >= heights[right + 1]) {
                    left--;
                } else {
                    right++;
                }
            } else if (left - 1 >= startIndex) {
                left--;
            } else {
                right++;
            }
        }

        return Math.max(maxMid, Math.max(maxLeft, maxRight));
    }

}
