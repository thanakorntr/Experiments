package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 3/6/16.
 */
public class PalindromePermutations {

    public static void main(String[] args) {

        String str = "aabcb";
        List<String> allPerm = generatePalindromPerm(str);
        allPerm.forEach(System.out::println);  // Output: abcba bacab

        str = "aabbcadad";
        allPerm = generatePalindromPerm(str);
        allPerm.forEach(System.out::println);  // Output: aabdcdbaa aadbcbdaa abadcdaba
                                               // abdacadba adabcbada adbacabda
                                               // baadcdaab badacadab bdaacaadb
                                               // daabcbaad dabacabad dbaacaabd

    }

    private static List<String> generatePalindromPerm(String str) {

        List<String> allPerm = new ArrayList<>();

        if (str == null || str.isEmpty()) {
            return allPerm;
        }

        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!charMap.containsKey(c)) {
                charMap.put(c, 1);
            } else {
                charMap.put(c, charMap.get(c) + 1);
            }
        }

        if (!determineIfPossiblePalindromePerm(str, charMap)) {
            return allPerm;
        }

        Character oddChar = null;
        StringBuilder halfPalindromeBuilder = new StringBuilder();
        for (char c : charMap.keySet()) {
            if (charMap.get(c) % 2 != 0) {
                oddChar = c;
            }
            addChar(halfPalindromeBuilder, c, charMap.get(c) / 2);
        }

        Set<String> halfPalinSet = generateAllPerm(halfPalindromeBuilder.toString());
        if (oddChar == null) {
            for (String halfPalin : halfPalinSet) {
                allPerm.add(halfPalin + generateReverse(halfPalin));
            }
        } else {
            for (String halfPalin : halfPalinSet) {
                allPerm.add(halfPalin + oddChar + generateReverse(halfPalin));
            }
        }

        return allPerm;
    }

    private static Set<String> generateAllPerm(String str) {

        Set<String> allPerm = new HashSet<>();
        if (str == null || str.isEmpty()) {
            return allPerm;
        }

        char[] charArray = str.toCharArray();
        generateAllPermHelper(charArray, 0, allPerm);
        return allPerm;

    }

    private static void generateAllPermHelper(char[] charArray, int index, Set<String> allPerms) {
        if (index == charArray.length - 1) {
            allPerms.add(String.copyValueOf(charArray));
            return;
        }

        for (int i = index; i < charArray.length; i++) {
            swap(charArray, index, i);
            generateAllPermHelper(charArray, index + 1, allPerms);
            swap(charArray, index, i);
        }
    }

    private static String generateReverse(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str == null) {
            return "";
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            stringBuilder.append(str.charAt(i));
        }
        return stringBuilder.toString();
    }

    private static void swap(char[] charArray, int i, int j) {
        if (i < 0 || i >= charArray.length || j < 0 || j >= charArray.length
                || i == j) {
            return;
        }

        char tmp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = tmp;
    }

    private static boolean determineIfPossiblePalindromePerm(String str, Map<Character, Integer> charMap) {

        if (str.length() % 2 == 0) {
            for (char c : charMap.keySet()) {
                if (charMap.get(c) % 2 != 0) {
                    return false;
                }
            }
            return true;
        } else {
            int numOdd = 0;
            for (char c : charMap.keySet()) {
                if (charMap.get(c) % 2 != 0) {
                    numOdd++;
                }
            }
            return numOdd == 1;
        }

    }

    private static void addChar(StringBuilder stringBuilder, char c, int numTimes) {
        for (int i = 0; i < numTimes; i++) {
            stringBuilder.append(c);
        }
    }
}
