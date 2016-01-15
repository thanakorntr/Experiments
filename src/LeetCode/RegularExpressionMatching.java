package LeetCode;

/**
 * Implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "a*") → true
 isMatch("aa", ".*") → true
 isMatch("ab", ".*") → true
 isMatch("aab", "c*a*b") → true

 *
 * Created by Thanakorn on 1/13/16.
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        String s = "";
        String p = ".*";
        System.out.println(new RegularExpressionMatching().isMatch(s, p));
    }

    public boolean isMatch(String s, String p) {
        if (s.isEmpty() && p.isEmpty()) {
            return true;
        }
        if (s.isEmpty() || p.isEmpty()) {
            if (p.isEmpty()) {
                return false;
            }
        }

        char[] sArray = s.toCharArray();
        char[] pArray = p.toCharArray();
        return isMatchHelper(sArray, pArray, 0, 0);
    }

    private boolean isMatchHelper(char[] sArray, char[] pArray, int sIndex, int pIndex) {

        if (sIndex >= sArray.length && pIndex >= pArray.length) {
            return true;
        }

        if (sIndex >= sArray.length || pIndex >= pArray.length) {
            if (pIndex >= pArray.length) {
                return false;
            } else {
                if (pIndex == pArray.length - 1 || (pArray.length - pIndex) % 2 != 0) {
                    return false;
                }
                for (int i = pIndex + 1; i < pArray.length; i += 2) {
                    if (pArray[i] != '*') {
                        return false;
                    }
                }
                return true;
            }
        }

        if (pArray[pIndex] == '.') {
            if (pIndex + 1 < pArray.length && pArray[pIndex + 1] == '*') {
                for (int i = sIndex; i < sArray.length; i++) {
                    if (isMatchHelper(sArray, pArray, i, pIndex + 2)) {
                        return true;
                    }
                }
                return isMatchHelper(sArray, pArray, sArray.length, pIndex + 2);
            } else {
                return isMatchHelper(sArray, pArray, sIndex + 1, pIndex + 1);
            }
        } else if (pArray[pIndex] != '.' && pArray[pIndex] != '*') {
            if (pIndex + 1 < pArray.length && pArray[pIndex + 1] == '*') {
                int i = sIndex;
                for (; i < sArray.length && sArray[i] == pArray[pIndex]; i++) {
                    if (isMatchHelper(sArray, pArray, i, pIndex + 2)) {
                        return true;
                    }
                }
                return isMatchHelper(sArray, pArray, i, pIndex + 2);
            } else {
                if (sArray[sIndex] != pArray[pIndex]) {
                    return false;
                } else {
                    return isMatchHelper(sArray, pArray, sIndex + 1, pIndex + 1);
                }
            }
        }

        return false;
    }

}
