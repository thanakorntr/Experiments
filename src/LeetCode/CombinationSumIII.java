package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 8/21/15.
 *
 *

 Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

 Ensure that numbers within the set are sorted in ascending order.

 Example 1:

 Input: k = 3, n = 7

 Output:

 [[1,2,4]]


 Example 2:

 Input: k = 3, n = 9

 Output:

 [[1,2,6], [1,3,5], [2,3,4]]

 */
public class CombinationSumIII {

    public static void main(String[] args) {

        List<List<Integer>> ans = combinationSum3(3,9);
        ans.forEach(x -> System.out.println(x.toString()));

    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        return combinationSum3Helper(k, n, 1);
    }

    public static List<List<Integer>> combinationSum3Helper(int k, int target, int startNum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k == 0 || startNum > 9 || startNum > target) {
            return ans;
        }

        if (startNum == target && k == 1) {
            List<Integer> subAns = new ArrayList<>();
            subAns.add(startNum);
            ans.add(subAns);
            return ans;
        }

        List<Integer> subAns;
        List<List<Integer>> subAnsRemaining = combinationSum3Helper(k-1, target-startNum, startNum+1);
        if (!subAnsRemaining.isEmpty()) {
            for (List<Integer> subAnsRemainingPart : subAnsRemaining) {
                if (subAnsRemainingPart.size() == k-1){
                    subAns = new ArrayList<>();
                    subAns.add(startNum);
                    subAns.addAll(subAnsRemainingPart);
                    ans.add(subAns);
                }
            }
        }

        subAnsRemaining = combinationSum3Helper(k, target, startNum+1);
        if (!subAnsRemaining.isEmpty()) {
            for (List<Integer> subAnsRemainingPart : subAnsRemaining) {
                if (subAnsRemainingPart.size() == k) {
                    ans.add(subAnsRemainingPart);
                }
            }
        }

        return ans;
    }
}
