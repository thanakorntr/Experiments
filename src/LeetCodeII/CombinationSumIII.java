package LeetCodeII;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 2/22/16.
 */
public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        return combinationSum3Helper(k, n, 1);
    }

    public List<List<Integer>> combinationSum3Helper(int k, int target, int startNum) {
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
        for (List<Integer> subAnsRemainingPart : subAnsRemaining) {
            subAns = new ArrayList<>();
            subAns.add(startNum);
            subAns.addAll(subAnsRemainingPart);
            ans.add(subAns);
        }

        subAnsRemaining = combinationSum3Helper(k, target, startNum+1);
        ans.addAll(subAnsRemaining);

        return ans;
    }

}
