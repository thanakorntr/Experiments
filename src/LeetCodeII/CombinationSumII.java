package LeetCodeII;

import java.util.*;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

 Each number in C may only be used once in the combination.

 Note:
 All numbers (including target) will be positive integers.
 Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 The solution set must not contain duplicate combinations.
 For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 A solution set is:
 [1, 7]
 [1, 2, 5]
 [2, 6]
 [1, 1, 6]
 *
 * Created by Thanakorn on 2/22/16.
 */
public class CombinationSumII {

    public static void main(String[] args) {
        int[] nums = new int[]{1,1};
        int target = 1;
        List<List<Integer>> comSum2 = new CombinationSumII().combinationSum2(nums, target);
        for (List<Integer> comSum : comSum2) {
            System.out.println(comSum.toString());
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> combinationSum2 = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return combinationSum2;
        }
        Arrays.sort(candidates);
        return combinationSum2Helper(candidates, candidates.length - 1, target);
    }

    private List<List<Integer>> combinationSum2Helper(int[] candidates, int index, int target) {
        List<List<Integer>> combinationSum2 = new ArrayList<>();

        if (target <= 0) {
            return combinationSum2;
        }
        if (index == 0) {
            if (candidates[index] == target) {
                List<Integer> singleArray = new ArrayList<>();
                singleArray.add(candidates[index]);
                combinationSum2.add(singleArray);
            }
            return combinationSum2;
        }

        if (candidates[index] == target) {
            List<Integer> singleArray = new ArrayList<>();
            singleArray.add(candidates[index]);
            combinationSum2.add(singleArray);
            return combinationSum2;
        }

        Set<List<Integer>> visited = new HashSet<>();

        List<List<Integer>> subComSums2 = combinationSum2Helper(candidates, index - 1, target);
        for (List<Integer> subCom2 : subComSums2) {
            if (!visited.contains(subCom2)) {
                combinationSum2.add(subCom2);
                visited.add(subCom2);
            }
        }

        subComSums2 = combinationSum2Helper(candidates, index - 1, target - candidates[index]);
        if (!subComSums2.isEmpty()) {
            for (List<Integer> subCom2 : subComSums2) {
                subCom2.add(candidates[index]);
                if (!visited.contains(subCom2)) {
                    combinationSum2.add(subCom2);
                    visited.add(subCom2);
                }
            }
        } else {
            if (candidates[index] == target) {
                List<Integer> singleArray = new ArrayList<>();
                singleArray.add(candidates[index]);
                if (!visited.contains(singleArray)) {
                    combinationSum2.add(singleArray);
                    visited.add(singleArray);
                }
            }
        }

        return combinationSum2;
    }
}
