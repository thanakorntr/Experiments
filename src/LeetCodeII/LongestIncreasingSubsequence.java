package LeetCodeII;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thanakorn on 4/23/16.
 */
@RunWith(Parameterized.class)
public class LongestIncreasingSubsequence {

    private static final int NUM_LEN = 3;
    private static final int NUM_TEST_CASES = 100;

    @Parameterized.Parameter (value = 0)
    public int[] testcase;

    @Parameterized.Parameter (value = 1)
    public int expected;

    @org.junit.Test
    public void eval() {
        int actual = efficientLIS(testcase);
        assertEquals(Arrays.toString(testcase), expected, actual);
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
            int curNum = nums[i];
            int left = 0, right = lis.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (lis.get(mid) == curNum) {
                    break;
                } else if (lis.get(mid) < curNum) {
                    if (mid == lis.size() - 1) {
                        lis.add(curNum);
                        break;
                    }
                    left = mid + 1;
                } else {  // lis.get(mid) > curNum
                    if (mid - 1 >= 0 && lis.get(mid - 1) < curNum) {
                        lis.set(mid, curNum);
                        break;
                    }
                    if (mid == 0) {
                        lis.set(mid, curNum);
                        break;
                    }
                    right = mid - 1;
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
