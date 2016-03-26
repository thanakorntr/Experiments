package Test;

import LeetCode.QuickSort;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thanakorn on 3/26/16.
 */
@RunWith(Parameterized.class)
public class QuickSortTest {

    private static final int NUMS_LEN = 5;
    private static final int NUM_TESTCASES = 100;

    @Parameterized.Parameter(value = 0)
    public int[] testcase;

    @Parameterized.Parameter(value = 1)
    public int[] expected;

    @org.junit.Test
    public void eval() {
        QuickSort.quickSort(testcase);
        Arrays.sort(expected);
        assertEquals(Arrays.toString(expected), Arrays.toString(testcase));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] testcases = new Object[NUM_TESTCASES][2];
        for (int i = 0; i < NUM_TESTCASES; i++) {
            int[] nums = generateRandomTestcase();
            testcases[i][0] = nums;
            testcases[i][1] = Arrays.copyOf(nums, nums.length);
        }
        return Arrays.asList(testcases);
    }


    private static int[] generateRandomTestcase() {
        int[] nums = new int[NUMS_LEN];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (NUMS_LEN * Math.random() + 1);
        }
        return nums;
    }

}
