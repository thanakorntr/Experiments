package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.

 Example 1:
 nums = [1, 3], n = 6
 Return 1.

 Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 So we only need 1 patch.

 Example 2:
 nums = [1, 5, 10], n = 20
 Return 2.
 The two patches can be [2, 4].

 Example 3:
 nums = [1, 2, 2], n = 5
 Return 0.
 *
 * Created by Thanakorn on 1/27/16.
 */
public class PatchingArray {

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,31,33};
        int n = 2147483647;

        PatchingArray patchingArray = new PatchingArray();
        int result = patchingArray.minPatches(nums, n);

        System.out.println(result);
    }

    public int minPatches(int[] nums, int n) {

        if (nums == null || nums.length == 0) {
            return n;
        }

        Set<Integer> sumSet = getSum(nums, 0, n);
        int numBitSet = sumSet.size();

        if (numBitSet == n) {
            return 0;
        }

        boolean[] bitSet = new boolean[n];
        for (int sum : sumSet) {
            bitSet[sum - 1] = true;
        }

        int firstMissingNum = -1;
        for (int i = 1; i <= n; i++) {
            if (!bitSet[i - 1]) {
                firstMissingNum = i;
                break;
            }
        }

        int numPatches = 0;

        while (sumSet.size() != n) {
            sumSet.add(firstMissingNum);
            bitSet[firstMissingNum - 1] = true;

            int newFirstMissingNum = -1;
            for (int i = firstMissingNum + 1; i <= n; i++) {
                if (!bitSet[i - 1]) {
                    newFirstMissingNum = i;
                    break;
                }
            }

            Set<Integer> newSumSet = new HashSet<>();
            for (int sum : sumSet) {
                int newSum = firstMissingNum + sum;
                if (newSum <= n) {
                    newSumSet.add(newSum);
                    bitSet[newSum - 1] = true;
                }
            }

            sumSet.addAll(newSumSet);

            firstMissingNum = newFirstMissingNum;
            numPatches++;
        }

        return numPatches;
    }

    private Set<Integer> getSum(int[] nums, int startIndex, int n) {
        Set<Integer> sumSet = new HashSet<>();

        if (startIndex == nums.length - 1) {
            sumSet.add(nums[nums.length - 1]);
            return sumSet;
        }

        Set<Integer> nextSumSet = getSum(nums, startIndex + 1, n);
        sumSet.addAll(nextSumSet);
        sumSet.add(nums[startIndex]);
        for (int sum : nextSumSet) {
            if (nums[startIndex] + sum <= n) {
                sumSet.add(nums[startIndex] + sum);
            }
        }
        return sumSet;
    }
}
