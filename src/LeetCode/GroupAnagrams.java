package LeetCode;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.

 For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Return:

 [
 ["ate", "eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 Note:
 For the return value, each inner list's elements must follow the lexicographic order.
 All inputs will be in lower-case.

 *
 * Created by Thanakorn on 12/29/15.
 */
public class GroupAnagrams {


    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> anagrams = new ArrayList<>();

        if (strs == null) {
            return anagrams;
        }

        Arrays.sort(strs);

        Map<String, Integer> anaMap = new HashMap<>();

        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = String.valueOf(charArray);
            if (!anaMap.containsKey(sortedStr)) {
                anaMap.put(sortedStr, anagrams.size());
                anagrams.add(new ArrayList<>());
            }
            anagrams.get(anaMap.get(sortedStr)).add(str);
        }

        return anagrams;
    }
}
