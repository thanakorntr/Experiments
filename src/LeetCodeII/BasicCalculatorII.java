package LeetCodeII;

/**
 * Implement a basic calculator to evaluate a simple expression string.

 The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

 You may assume that the given expression is always valid.

 Some examples:

 "3+2*2" = 7
 " 3/2 " = 1
 " 3+5 / 2 " = 5

 Note: Do not use the eval built-in library function.
 *
 * Created by Thanakorn on 4/12/16.
 */
public class BasicCalculatorII {

    public static void main(String[] args) {
        BasicCalculatorII basicCalculatorII = new BasicCalculatorII();
        String str = "3+2*2";
        System.out.println(basicCalculatorII.calculate(str));
        str = " 3/2";
        System.out.println(basicCalculatorII.calculate(str));
        str = " 3+5 / 2 ";
        System.out.println(basicCalculatorII.calculate(str));
    }

    public int calculate(String s) {
        s = s.replaceAll("\\s+", "");
        return calculateHelper(s, 0);
    }

    private int calculateHelper(String s, int start) {

        int operatorIndex = start;
        while (operatorIndex < s.length() && Character.isDigit(s.charAt(operatorIndex))) {
            operatorIndex++;
        }

        int firstOperand = Integer.parseInt(s.substring(start, operatorIndex));
        if (operatorIndex >= s.length()) {
            return firstOperand;
        }
        String operator = s.substring(operatorIndex, operatorIndex + 1);

        if (operator.equals("+")) {
            int secondOperand = calculateHelper(s, operatorIndex + 1);
            return firstOperand + secondOperand;
        }

        if (operator.equals("-")) {
            int secondOperand = calculateHelper(s, operatorIndex + 1);
            return firstOperand - secondOperand;
        }

        int secondOperandEndIndex = operatorIndex + 1;
        while (secondOperandEndIndex < s.length()
                && Character.isDigit(s.charAt(secondOperandEndIndex))) {
            secondOperandEndIndex++;
        }

        int secondOperand = Integer.parseInt(s.substring(operatorIndex + 1, secondOperandEndIndex));
        int tmp;
        if (operator.equals("*")) {
            tmp = firstOperand * secondOperand;
        } else {
            tmp = firstOperand / secondOperand;
        }

        return calculateHelper(Integer.toString(tmp) + s.substring(secondOperandEndIndex), 0);
    }

}
