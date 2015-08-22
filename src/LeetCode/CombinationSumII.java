package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 8/21/15.
 *
 *  Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

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
 */
public class CombinationSumII {

    public static void main(String[] args) {

        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> cs = combinationSum2(candidates, target);
        cs.forEach(x -> System.out.println(x.toString()));

    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return ans;
        }
        Arrays.sort(candidates);
        ans = combinationSum2Helper(candidates, target, 0);
        return ans;
    }

    public static List<List<Integer>> combinationSum2Helper(int[] candidates, int target, int startIndex) {
        List<List<Integer>> ans = new ArrayList<>();
        if (startIndex >= candidates.length || candidates[startIndex] > target) {
            return ans;
        }

        if (candidates[startIndex] == target) {
            List<Integer> subAns = new ArrayList<>();
            subAns.add(candidates[startIndex]);
            ans.add(subAns);
            return ans;
        }

        Set<List<Integer>> visited = new HashSet<>();

        List<List<Integer>> subAnsRemaining = combinationSum2Helper(candidates, target-candidates[startIndex],
                startIndex+1);
        if (!subAnsRemaining.isEmpty()) {
            for (List<Integer> subAnsRemainingPart : subAnsRemaining) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(candidates[startIndex]);
                tmp.addAll(subAnsRemainingPart);
                if (!visited.contains(tmp)) {
                    visited.add(tmp);
                    ans.add(tmp);
                }
            }
        }

        subAnsRemaining = combinationSum2Helper(candidates, target,
                startIndex+1);
        if (!subAnsRemaining.isEmpty()) {
            for (List<Integer> subAnsRemainingPart : subAnsRemaining) {
                if (!visited.contains(subAnsRemainingPart)) {
                    visited.add(subAnsRemainingPart);
                    ans.add(subAnsRemainingPart);
                }
            }
        }

        return ans;
    }
}
