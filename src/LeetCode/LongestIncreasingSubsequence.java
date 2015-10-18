package LeetCode;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thanakorn on 10/18/15.
 */
@RunWith(Parameterized.class)
public class LongestIncreasingSubsequence {

    private static final int NUM_LEN = 10;
    private static final int NUM_TEST_CASES = 100;

    @Parameterized.Parameter (value = 0)
    public int[] testcase;

    @Parameterized.Parameter (value = 1)
    public int expected;

    @org.junit.Test
    public void eval() {
        int actual = efficientLIS(testcase);
        assertEquals(expected, actual);
    }

    @Parameterized.Parameters (name = "Test {0}")
    public static Collection<Object[]> data() {
        Object[][] testcases = new Object[NUM_TEST_CASES][2];
        for (int i = 0; i < NUM_TEST_CASES; i++) {
            int[] testcase = generateRandomTest();
            testcases[i][0] = testcase;
            testcases[i][1] = dpLIS(testcase);
        }
        return Arrays.asList(testcases);
    }


    /**
     * http://stackoverflow.com/questions/4938833/find-longest-increasing-sequence
     */
    private int efficientLIS(int[] nums) {

        List<Integer> lis = new ArrayList<>();
        lis.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < lis.get(0)) {
                lis.set(0, nums[i]);
            } else if (nums[i] > lis.get(lis.size() - 1)) {
                lis.add(nums[i]);
            } else {
                int left = 0, right = lis.size()-2;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (nums[i] > lis.get(mid) && nums[i] < lis.get(mid+1)) {
                        lis.set(mid+1, nums[i]);
                        break;
                    } else if (nums[i] > lis.get(mid)) {
                        left = mid+1;
                    } else {
                        right = mid-1;
                    }
                }
            }
        }

        return lis.size();
    }

    private static int dpLIS(int[] nums) {

        int maxLen = 1;
        int[] lisArray = new int[nums.length];
        lisArray[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            int tmpLen = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    tmpLen = Math.max(tmpLen, lisArray[j] + 1);
                }
            }
            lisArray[i] = tmpLen;
            maxLen = Math.max(maxLen, tmpLen);
        }

        return maxLen;
    }

    private static int[] generateRandomTest() {
        int[] nums = new int[NUM_LEN];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int)(NUM_LEN * Math.random()) + 1;
        }
        return nums;
    }
}
