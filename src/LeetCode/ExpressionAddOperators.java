package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators (not unary) +, -, or *
 * between the digits so they evaluate to the target value.

 Examples:
 "123", 6 -> ["1+2+3", "1*2*3"]
 "232", 8 -> ["2*3+2", "2+3*2"]
 "105", 5 -> ["1*0+5","10-5"]
 "00", 0 -> ["0+0", "0-0", "0*0"]
 "3456237490", 9191 -> []
 *
 * Created by Thanakorn on 5/16/16.
 */
public class ExpressionAddOperators {

    public static void main(String[] args) {
        ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();

        String num = "123";
        int target = 6;
        System.out.println(expressionAddOperators.addOperators(num, target).toString());

        num = "232";
        target = 8;
        System.out.println(expressionAddOperators.addOperators(num, target).toString());

        num = "105";
        target = 5;
        System.out.println(expressionAddOperators.addOperators(num, target).toString());

        num = "00";
        target = 0;
        System.out.println(expressionAddOperators.addOperators(num, target).toString());

        num = "3456237490";
        target = 9191;
        System.out.println(expressionAddOperators.addOperators(num, target).toString());
    }

    public List<String> addOperators(String num, int target) {
        List<String> allPossibilities = new ArrayList<>();
        if (num == null || num.isEmpty()) {
            return allPossibilities;
        }
        addOperatorsHelper(allPossibilities, num, target, "", 0, 0, 0);
        return allPossibilities;
    }

    private void addOperatorsHelper(List<String> allPossibilities,
                                    String num, int target, String path,
                                    int startIndex, long curVal, long lastTokenVal) {

        if (startIndex == num.length()) {
            if (curVal == target) {
                allPossibilities.add(path);
            }
            return;
        }

        for (int i = startIndex; i < num.length(); i++) {
            if (num.charAt(startIndex) == '0' && i > startIndex) {
                break;
            }
            long leftVal = Long.parseLong(num.substring(startIndex, i + 1));
            if (startIndex == 0) {
                addOperatorsHelper(allPossibilities, num, target, leftVal + "", i + 1, leftVal, leftVal);
            } else {
                addOperatorsHelper
                        (allPossibilities, num, target, path + "+" + leftVal, i + 1, curVal + leftVal, leftVal);

                addOperatorsHelper
                        (allPossibilities, num, target, path + "-" + leftVal, i + 1, curVal - leftVal, -leftVal);

                addOperatorsHelper
                        (allPossibilities, num, target, path + "*" + leftVal, i + 1,
                                curVal - lastTokenVal + lastTokenVal * leftVal, lastTokenVal * leftVal);

            }
        }
    }

}
