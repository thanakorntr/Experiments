package LeetCodeII;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 5/25/16.
 */
public class AllPossibleStrings {

    public static void main(String[] args) {
        String test = "10?01?011?100?";
        List<String> allStrings = getAllStrings(test);

        printAllStrings(allStrings);
        System.out.println(allStrings.size());
    }

    public static List<String> getAllStrings(String str) {
        List<String> allStrings = new ArrayList<>();

        if (str == null || str.isEmpty()) {
            return allStrings;
        }

        char[] charArray = str.toCharArray();
        List<Integer> placeHolderIndices = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '?') {
                placeHolderIndices.add(i);
            }
        }

        getAllStringsHelper(charArray, 0, placeHolderIndices, allStrings);
        return allStrings;
    }

    private static void getAllStringsHelper(char[] charArray, int index,
                                            List<Integer> placeHolderIndices, List<String> allStrings) {

        if (index == placeHolderIndices.size() - 1) {
            charArray[placeHolderIndices.get(index)] = '0';
            allStrings.add(String.copyValueOf(charArray));
            charArray[placeHolderIndices.get(index)] = '1';
            allStrings.add(String.copyValueOf(charArray));
            return;
        }

        charArray[placeHolderIndices.get(index)] = '0';
        getAllStringsHelper(charArray, index + 1, placeHolderIndices, allStrings);
        charArray[placeHolderIndices.get(index)] = '1';
        getAllStringsHelper(charArray, index + 1, placeHolderIndices, allStrings);
    }

    public static void printAllStrings(List<String> strs) {
        for (String string : strs) {
            System.out.println(string);
        }
    }

}
