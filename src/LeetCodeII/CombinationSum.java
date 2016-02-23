package LeetCodeII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the
 * candidate numbers sums to T.

 The same repeated number may be chosen from C unlimited number of times.

 Note:
 All numbers (including target) will be positive integers.
 Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 The solution set must not contain duplicate combinations.
 For example, given candidate set 2,3,6,7 and target 7,
 A solution set is:
 [7]
 [2, 2, 3]
 *
 * Created by Thanakorn on 2/22/16.
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = new int[]{2,3};
        int target = 6;
        List<List<Integer>> combination = combinationSum(candidates, target);
        for (List<Integer> com : combination) {
            System.out.println(com.toString());
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> combinationSum = new ArrayList<>();

        if (candidates == null || candidates.length == 0) {
            return combinationSum;
        }

        Arrays.sort(candidates);

        return combinationSumHelper(candidates, candidates.length - 1, target);

    }

    private static List<List<Integer>> combinationSumHelper(int[] candidates, int index, int target) {

        List<List<Integer>> combinationSum = new ArrayList<>();

        if (target <= 0) {
            return combinationSum;
        }

        if (index == 0) {
            int multiple = target / candidates[index];
            if (multiple > 0) {
                if (target % candidates[index] == 0) {
                    List<Integer> baseArray = new ArrayList<>();
                    for (int i = 0; i < multiple; i++) {
                        baseArray.add(candidates[index]);
                    }
                    combinationSum.add(baseArray);
                }
            }
            return combinationSum;
        }

        List<List<Integer>> subComSums = combinationSumHelper(candidates, index - 1, target);
        combinationSum.addAll(subComSums);

        int maxDupOfCurIndex = target / candidates[index];
        if (maxDupOfCurIndex > 0) {
            for (int i = 1; i <= maxDupOfCurIndex; i++) {
                int remainingSum = target - candidates[index] * i;
                subComSums = combinationSumHelper(candidates, index - 1, remainingSum);
                if (!subComSums.isEmpty()) {
                    for (List<Integer> subComSum : subComSums) {
                        for (int j = 0; j < i; j++) {
                            subComSum.add(candidates[index]);
                        }
                        combinationSum.add(subComSum);
                    }
                } else {
                    if (candidates[index] * i == target) {
                        List<Integer> subComSum = new ArrayList<>();
                        for (int j = 0; j < i; j++) {
                            subComSum.add(candidates[index]);
                        }
                        combinationSum.add(subComSum);
                    }
                }
            }
        }

        return combinationSum;

    }
}
