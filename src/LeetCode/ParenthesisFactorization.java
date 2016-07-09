package LeetCode;

import java.util.*;

/**
 * # take an array and print non over lapping in order pairs. example:


 # [1,2,3,4] => input

 # output below is in order combination

 # (1234)
 # (1)(234)
 # (1)(23)(4)
 # (1)(2)(34)
 # (12)(34)
 # (12)(3)(4)
 # (123)(4)
 # (1)(2)(3)(4)

 *
 * Created by Thanakorn on 7/8/16.
 */
public class ParenthesisFactorization {

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3,4};
        factorizeParen(nums);

    }

    private static void factorizeParen(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        Map<Integer, Map<Integer, Set<String>>> memo = new HashMap<>();
        helper(nums, 0 , nums.length - 1, memo);
        Set<String> factorizedParens = memo.get(0).get(nums.length - 1);
        for (String factorizedParen : factorizedParens) {
            System.out.println(factorizedParen);
        }
    }

    private static void helper(int[] nums,
                               int startindex,
                               int endIndex,
                               Map<Integer, Map<Integer, Set<String>>> memo) {

        if (memo.containsKey(startindex) && memo.get(startindex).containsKey(endIndex)) {
            return;
        }
        if (!memo.containsKey(startindex)) {
            memo.put(startindex, new HashMap<>());
        }
        if (!memo.get(startindex).containsKey(endIndex)) {
            memo.get(startindex).put(endIndex, new HashSet<>());
        }

        if (startindex == endIndex) {
            Set<String> ans = new HashSet<>();
            ans.add("(" + nums[startindex] + ")");
            memo.get(startindex).put(endIndex, ans);
            return;
        }

        Set<String> ans = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for (int i = startindex; i <= endIndex; i++) {
            stringBuilder.append(nums[i]);
        }
        stringBuilder.append(")");
        ans.add(stringBuilder.toString());

        for (int i = startindex; i < endIndex; i++) {
            helper(nums, startindex, i, memo);
            helper(nums, i + 1, endIndex, memo);
            Set<String> first = memo.get(startindex).get(i);
            Set<String> second = memo.get(i + 1).get(endIndex);
            for (String firstStr : first) {
                for (String secondStr : second) {
                    ans.add(firstStr + secondStr);
                }
            }
        }

        memo.get(startindex).put(endIndex, ans);
    }

}
