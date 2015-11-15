package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 11/13/15.
 *
 * Given two numbers represented as strings, return multiplication of the numbers as a string.

 Note: The numbers can be arbitrarily large and are non-negative.

 Subscribe to see which companies asked this question
 */
public class MultiplyStrings {

    public static void main(String[] args) {

        System.out.println(Integer.parseInt("0000"));

    }


    public static String multiply(String num1, String num2) {

        if (num1 == null || num2 == null || num1.isEmpty() || num2.isEmpty()) {
            return "";
        }

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        if (num1.length() < num2.length()) {
            return multiply(num2, num1);
        }

        List<String> multiRows = new ArrayList<>();

        for (int num2Index = num2.length() - 1; num2Index >= 0; num2Index--) {
            int paddingZeros = num2.length() - num2Index - 1;
            StringBuilder curRowBuilder = new StringBuilder();
            for (int i = 0; i < paddingZeros; i++) {
                curRowBuilder.append(0);
            }

            int carry = 0;
            int curNumInNum2 = Character.getNumericValue(num2.charAt(num2Index));
            for (int num1Index = num1.length() - 1; num1Index >= 0; num1Index--) {
                int curMul = curNumInNum2 * Character.getNumericValue(num1.charAt(num1Index));
                curMul += carry;
                int curDigit = curMul % 10;
                carry = curMul / 10;
                curRowBuilder.append(curDigit);
            }

            if (carry != 0) {
                curRowBuilder.append(carry);
            }

            multiRows.add(curRowBuilder.toString());
        }


        int maxLen = -1;
        for (String row : multiRows) {
            maxLen = Math.max(maxLen, row.length());
        }

        // doing the summation of all rows here
        int carry = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            int curSum = 0;
            for (String row : multiRows) {
                if (i < row.length()) {
                    curSum += Character.getNumericValue(row.charAt(i));
                }
            }
            curSum += carry;
            int curDigit = curSum % 10;
            carry = curSum / 10;
            ans.append(curDigit);
        }

        if (carry != 0) {
            ans.append(carry);
        }


        return ans.reverse().toString();
    }
}
