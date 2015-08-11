package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 7/4/15.
 */
public class Anagrams {

    public static void main(String[] args) {
        String[] input = {"",""};
        List<String> anagrams = anagrams(input);
        System.out.println(anagrams.toString());
    }

    public static List<String> anagrams(String[] strs) {
        List<String> anagrams = new ArrayList<>();
        Map<String, List<String>> anagramsMap = new HashMap<>();

        for (String word : strs) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String orderedWord = String.copyValueOf(charArray);
            if (!anagramsMap.containsKey(orderedWord)) {
                anagramsMap.put(orderedWord, new ArrayList<>());
            }
            anagramsMap.get(orderedWord).add(word);
        }

        for (String orderedWord : anagramsMap.keySet()) {
            if (anagramsMap.get(orderedWord).size() > 1) {
                anagrams.addAll(anagramsMap.get(orderedWord));
            }
        }
        return anagrams;
    }
}
