import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thanakorn on 6/28/15.
 */
public class CombinationSum {

    public static void main(String[] args) {

        int[] candidateSet = {2,3,5};
        int target = 7;

        List<List<Integer>> ans = combinationSum(candidateSet, target);

        for (List<Integer> subAns : ans) {
            System.out.println(subAns.toString());
        }

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < candidates.length; i++) {
            List<List<Integer>> subAns = combinationSumHelper(candidates, i, target);
            if (!subAns.isEmpty()) {
                ans.addAll(subAns);
            }
        }

        return ans;
    }

    public static List<List<Integer>> combinationSumHelper(int[] candidates, int startIndex, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        if (startIndex >= candidates.length || candidates[startIndex] > target) {
            return ans;
        }

        int numSub = target / candidates[startIndex];
        for (int i = 1; i <= numSub; i++) {
            List<Integer> subAns = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                subAns.add(candidates[startIndex]);
            }
            if (candidates[startIndex] * i == target) {
                ans.add(subAns);
            } else {
                for (int k = startIndex+1; k < candidates.length; k++) {
                    List<List<Integer>> subAnsNextPart = combinationSumHelper(candidates, k, target-candidates[startIndex]*i);
                    if (!subAnsNextPart.isEmpty()) {
                        for (List<Integer> subsubAnsNextPart : subAnsNextPart) {
                            List<Integer> temp = new ArrayList<>();
                            temp.addAll(subAns);
                            temp.addAll(subsubAnsNextPart);
                            ans.add(temp);
                        }
                    }
                }
            }
        }

        return ans;
    }

}
