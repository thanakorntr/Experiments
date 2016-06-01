package LeetCode;

/**
 * http://www.geeksforgeeks.org/minimum-time-to-finish-tasks-without-skipping-two-consecutive/
 * <p>
 * Given time taken by n tasks. Find the minimum time needed to finish the tasks such that skipping
 * of tasks is allowed, but can not skip two consecutive tasks.
 * <p>
 * Examples :
 * <p>
 * Input : arr[] = {10, 5, 7, 10}
 * Output : 12
 * We can skip first and last task and
 * finish these task in 12 min.
 * <p>
 * Input : arr[] = {10}
 * Output : 0
 * There is only one task and we can
 * skip it.
 * <p>
 * Input : arr[] = {10, 30}
 * Output : 10
 * <p>
 * Input : arr[] = {10, 5, 2, 4, 8, 6, 7, 10}
 * Output : 22
 * Expected Time Complexity is O(n) and extra space is O(1).
 * <p>
 * Created by Thanakorn on 5/31/16.
 */
public class MinimumTimeToFinishTasksWithoutSkippingTwoConsecutive {

    public static void main(String[] args) {
        int[] nums = new int[]{10, 5, 7, 10};
        System.out.println(minTime(nums));  // 12

        nums = new int[]{10};
        System.out.println(minTime(nums));  // 0

        nums = new int[]{10, 30};
        System.out.println(minTime(nums));  // 10

        nums = new int[]{10, 5, 2, 4, 8, 6, 7, 10};
        System.out.println(minTime(nums));  // 22
    }

    private static int minTime(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int minChosen1 = nums[0];
        int minNotChosen1 = 0;
        for (int i = 1; i < nums.length; i++) {
            int minChosen = Math.min(minChosen1 + nums[i], minNotChosen1 + nums[i]);
            int minNotChosen = minChosen1;
            minChosen1 = minChosen;
            minNotChosen1 = minNotChosen;
        }
        return Math.min(minChosen1, minNotChosen1);
    }

}
