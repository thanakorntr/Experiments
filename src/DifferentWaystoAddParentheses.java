import java.util.*;

/**
 * Created by Thanakorn on 7/28/15.
 */

public class DifferentWaystoAddParentheses {

    public static void main(String[] args) {

        String input1 = "2-1-1";
        List<Integer> ans = diffWaysToCompute(input1);  // 0,2
        System.out.println(ans.toString());

    }

    public static List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> memo = new HashMap<>();
        return diffWaysToComputeHelper(input, memo);
    }

    public static List<Integer> diffWaysToComputeHelper(String input, Map<String, List<Integer>> memo) {
        List<Integer> ans = new ArrayList<>();
        if (input.isEmpty()) {
            return ans;
        }

        if (memo.containsKey(input)) {
            return memo.get(input);
        }

        boolean containsOperator = false;
        for (int i = 0; i < input.length(); i++) {
            if (isOperator(input.charAt(i))) {
                containsOperator = true;
                String leftStr = input.substring(0, i);
                if (!memo.containsKey(leftStr)) {
                    memo.put(leftStr, diffWaysToComputeHelper(leftStr, memo));
                }
                String rightStr = input.substring(i+1);
                if (!memo.containsKey(rightStr)) {
                    memo.put(rightStr, diffWaysToComputeHelper(rightStr, memo));
                }
                List<Integer> leftAns = memo.get(leftStr);
                List<Integer> rightAns = memo.get(rightStr);
                for (int l : leftAns) {
                    for (int r : rightAns) {
                        if (input.charAt(i) == '+') {
                            ans.add(l+r);
                        } else if (input.charAt(i) == '-') {
                            ans.add(l-r);
                        } else if (input.charAt(i) == '*') {
                            ans.add(l*r);
                        }
                    }
                }
            }
        }

        if (!containsOperator) {
            ans.add(Integer.parseInt(input));
        }
        memo.put(input, ans);
        return ans;
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*';
    }
}
