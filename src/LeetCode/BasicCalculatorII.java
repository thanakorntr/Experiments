package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 10/1/15.
 *
 * Implement a basic calculator to evaluate a simple expression string.

 The expression string contains only non-negative integers, +, -, *, / operators
 and empty spaces . The integer division should truncate toward zero.

 You may assume that the given expression is always valid.

 Some examples:
 "3+2*2" = 7
 " 3/2 " = 1
 " 3+5 / 2 " = 5
 */
public class BasicCalculatorII {

    public static void main(String[] args) {

        System.out.println(calculate("3+2*2")); // 7
        System.out.println(calculate("3/2")); // 1
        System.out.println(calculate("3+5 / 2")); // 5
        System.out.println(calculate("2*3*4")); // 24

    }

    private static int calculate(String s) {

        List<String> tokenized = tokenize(s);
        List<String> tokenizedNoProductDivision = new ArrayList<>();
        for (int i = 0; i < tokenized.size(); i++) {
            if (tokenized.get(i).equals("*")) {
                int i1 = Integer.parseInt(tokenizedNoProductDivision.get(tokenizedNoProductDivision.size()-1));
                int i2 = Integer.parseInt(tokenized.get(i + 1));
                int product = i1 * i2;
                tokenizedNoProductDivision.remove(tokenizedNoProductDivision.size()-1);
                tokenizedNoProductDivision.add(Integer.toString(product));
                i++;
                continue;
            }
            if (tokenized.get(i).equals("/")) {
                int i1 = Integer.parseInt(tokenizedNoProductDivision.get(tokenizedNoProductDivision.size()-1));
                int i2 = Integer.parseInt(tokenized.get(i + 1));
                int division = i1 / i2;
                tokenizedNoProductDivision.remove(tokenizedNoProductDivision.size()-1);
                tokenizedNoProductDivision.add(Integer.toString(division));
                i++;
                continue;
            }
            tokenizedNoProductDivision.add(tokenized.get(i));
        }

        int ans = Integer.parseInt(tokenizedNoProductDivision.get(0));
        String curOperator = "";
        for (int i = 1; i < tokenizedNoProductDivision.size(); i++) {
            if (i % 2 == 0) {  // number
                int i2 = Integer.parseInt(tokenizedNoProductDivision.get(i));
                if (curOperator.equals("+")) {
                    ans += i2;
                } else if (curOperator.equals("-")) {
                    ans -= i2;
                }
            } else {  // operator
                curOperator = tokenizedNoProductDivision.get(i);
            }
        }

        return ans;
    }

    private static List<String> tokenize(String s) {
        List<String> tokenized = new ArrayList<>();
        int startNumIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/') {
                String token = s.substring(startNumIndex, i);
                tokenized.add(token.trim());
                tokenized.add(Character.toString(s.charAt(i)));
                startNumIndex = i + 1;
            }
        }
        String lastToken = s.substring(startNumIndex);
        tokenized.add(lastToken.trim());

        return tokenized;
    }
}
