package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 11/6/15.
 * <p>
 * Remove the minimum number of invalid parentheses in order to make the input string valid.
 * Return all possible results.
 * <p>
 * Note: The input string may contain letters other than the parentheses ( and ).
 * <p>
 * Examples:
 * <p>
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 */
public class RemoveInvalidParentheses {

    public static void main(String[] args) {

        String str = "1a";
        System.out.println(str.substring(0,0));
    }


    public List<String> removeInvalidParentheses(String s) {

        LinkedList<String> curLevel = new LinkedList<>();
        curLevel.add(s);

        while (!curLevel.isEmpty()) {
            Set<String> nextLevel = new HashSet<>();
            List<String> validParenInCurLevel = new ArrayList<>();
            while (!curLevel.isEmpty()) {
                String str = curLevel.poll();
                if (isValidParentheses(str)) {
                    validParenInCurLevel.add(str);
                } else {
                    if (validParenInCurLevel.isEmpty()) {
                        for (int i = 0; i < str.length(); i++) {
                            if (str.charAt(i) == '('
                                    || str.charAt(i) == ')') {
                                String deletedParenStr = str.substring(0, i) + str.substring(i + 1);
                                nextLevel.add(deletedParenStr);
                            }
                        }
                    }
                }
            }

            if (!validParenInCurLevel.isEmpty()) {
                return validParenInCurLevel;
            }
            curLevel = new LinkedList<>(nextLevel);
        }

        return new ArrayList<>();
    }

    private boolean isValidParentheses(String s) {

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                if (count <= 0) {
                    return false;
                }
                count--;
            }
        }

        return count == 0;
    }
}
