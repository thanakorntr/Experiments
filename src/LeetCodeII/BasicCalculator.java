package LeetCodeII;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement a basic calculator to evaluate a simple expression string.

 The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

 You may assume that the given expression is always valid.

 Some examples:

 "1 + 1" = 2
 " 2-1 + 2 " = 3
 "(1+(4+5+2)-3)+(6+8)" = 23

 Note: Do not use the eval built-in library function.
 *
 * Created by Thanakorn on 4/12/16.
 */
public class BasicCalculator {

    public static void main(String[] args) {
        BasicCalculator basicCalculator = new BasicCalculator();
        String str = "1 + 1";
        System.out.println(basicCalculator.calculate(str));
        str = " 2-1 + 2 ";
        System.out.println(basicCalculator.calculate(str));
        str = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(basicCalculator.calculate(str));
    }

    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException();
        }
        s = "(" + s + ")";
        s = s.replaceAll("\\s+", "");
        return calculateHelper(s, 0, s.length() - 1);
    }

    private int calculateHelper(String s, int start, int end) {
        Queue<String> queue = new LinkedList<>();

        for (int i = start + 1; i < end; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                int startIndex = i;
                int endIndex = i;
                int leftParenCount = 1;
                for (int j = startIndex + 1; j < end; j++) {
                    if (s.charAt(j) == '(') {
                        leftParenCount++;
                    } else if (s.charAt(j) == ')') {
                        leftParenCount--;
                        if (leftParenCount == 0) {
                            endIndex = j;
                            break;
                        }
                    }
                }
                int subVal = calculateHelper(s, startIndex, endIndex);
                queue.add(Integer.toString(subVal));
                i = endIndex;
            } else if (isOperator(c)) {
                queue.add(s.substring(i, i + 1));
            } else {  // a digit
                int j = i;
                while (Character.isDigit(s.charAt(j))) {
                    j++;
                }
                queue.add(s.substring(i, j));
                i = j - 1;
            }
        }

        return evalQueue(queue);
    }

    private int evalQueue(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int sum = Integer.parseInt(queue.poll());

        while (!queue.isEmpty()) {
            String operator = queue.poll();
            int secondOperand = Integer.parseInt(queue.poll());
            if (operator.equals("+")) {
                sum += secondOperand;
            } else if (operator.equals("-")) {
                sum -= secondOperand;
            }
        }

        return sum;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-';
    }

}
