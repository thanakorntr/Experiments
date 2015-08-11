package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 7/16/15.
 */

/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is
a valid dictionary word.

        Return all such possible sentences.

        For example, given
        s = "catsanddog",
        dict = ["cat", "cats", "and", "sand", "dog"].

        A solution is ["cats and dog", "cat sand dog"].*/

public class WordBreakII {

    public static void main(String[] args) {
        String s = "catsanddog";

        Set<String> wordDict = new HashSet<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");

        List<String> ans = wordBreak(s, wordDict);

        ans.forEach(x -> System.out.println(x));
    }

    public static List<String> wordBreak(String s, Set<String> wordDict) {
        Map<String, List<String>> memo = new HashMap<>();
        return wordBreakHelper(s, wordDict, memo);
    }

    public static List<String> wordBreakHelper(String s, Set<String> wordDict, Map<String, List<String>> memo) {
        List<String> ans = new ArrayList<>();

        if (s.isEmpty()) {
            return ans;
        }

        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        for (int i = 1; i <= s.length(); i++) {
            String frontSubStr = s.substring(0, i);
            if (wordDict.contains(frontSubStr)) {
                StringBuilder sb = new StringBuilder(frontSubStr);
                String nextSubStr = s.substring(i);
                List<String> subProbAns = null;
                if (!memo.containsKey(nextSubStr)) {
                    subProbAns = wordBreakHelper(nextSubStr, wordDict, memo);
                    memo.put(nextSubStr, subProbAns);
                } else {
                    subProbAns = memo.get(nextSubStr);
                }
                if (subProbAns.isEmpty()) {
                    if (i == s.length()) {
                        ans.add(frontSubStr);
                    }
                } else {
                    for (String subProb : subProbAns) {
                        sb.append(" ").append(subProb);
                        ans.add(sb.toString());
                        sb.delete(frontSubStr.length(), sb.length());
                    }
                }
            }
        }

        memo.put(s, ans);
        return ans;
    }
}
