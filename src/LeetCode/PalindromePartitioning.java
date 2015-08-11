package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 7/13/15.
 */
public class PalindromePartitioning {

    public static void main(String[] args) {

        String s = "aab";

        List<List<String>> ans = partition(s);

        for (List<String> list : ans) {
            System.out.println(list.toString());
        }

    }

    public static List<List<String>> partition(String s) {

        Map<String, List<List<String>>> memo = new HashMap<>();

        List<List<String>> ans = partitionHelper(s, memo);

        return ans;
    }

    public static List<List<String>> partitionHelper(String s, Map<String, List<List<String>>> memo) {
        List<List<String>> ans = new ArrayList<>();

        if (s.isEmpty()) {
            return ans;
        }

        if (s.length() == 1) {
            ans.add(Arrays.asList(s));
            return ans;
        }

        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        for (int i = 1; i <= s.length(); i++) {
            String subStrLeft = s.substring(0, i);
            if (isPalindrome(subStrLeft)) {
                if (i == s.length()) {
                    ans.add(Arrays.asList(subStrLeft));
                    break;
                }
                String subStrRight = s.substring(i);
                List<List<String>> subSolStrRight;
                if (!memo.containsKey(subStrRight)) {
                    subSolStrRight = partitionHelper(subStrRight, memo);
                    memo.put(subStrRight, subSolStrRight);
                } else {
                    subSolStrRight = memo.get(subStrRight);
                }
                if (subSolStrRight.isEmpty()) {
                    continue;
                } else {
                    for (List<String> sub : subSolStrRight) {
                        List<String> tmp = new ArrayList<>();
                        tmp.add(subStrLeft);
                        tmp.addAll(sub);
                        ans.add(tmp);
                    }
                }
            }
        }

        memo.put(s, ans);
        return ans;
    }

    public static boolean isPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return true;
        }

        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
