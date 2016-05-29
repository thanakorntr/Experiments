package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=413
 *
 * Given an integer array and a non-negative integer k,
 * count all distinct pairs with difference equal to k, i.e., A[ i ] - A[ j ] = k.
 *
 * Created by Thanakorn on 5/29/16.
 */
public class CountDistinctPairsWithDifferenceK {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numTestCases = scanner.nextInt();

        for (int i = 0; i < numTestCases; i++) {
            int arraySize = scanner.nextInt();
            int[] nums = new int[arraySize];
            for (int j = 0; j < arraySize; j++) {
                nums[j] = scanner.nextInt();
            }
            int k = scanner.nextInt();
            int distinctPair = countDistinctPair(nums, k);
            System.out.println(distinctPair);
        }

    }

    private static int countDistinctPair(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        Map<Integer, Integer> numMap = new HashMap<>();
        for (int num : nums) {
            int count = numMap.getOrDefault(num, 0);
            numMap.put(num, count + 1);
        }

        int count = 0;
        for (int num : numMap.keySet()) {
            int target = k + num;
            if (numMap.containsKey(target)) {
                if (k != 0) {
                    count++;
                } else {
                    if (numMap.get(num) > 1) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

}
