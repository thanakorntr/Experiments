package LeetCode;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

 Example:
 Given a = 1 and b = 2, return 3.
 *
 * Created by Thanakorn on 7/4/16.
 */
public class SumOfTwoIntegers {

    public static void main(String[] args) {
        SumOfTwoIntegers sumOfTwoIntegers = new SumOfTwoIntegers();
        int a = 1;
        int b = 2;
        System.out.println(sumOfTwoIntegers.getSum(a, b));  // 3
    }

    public int getSum(int a, int b) {
        int carry = a & b;
        a = a ^ b;
        while (carry != 0) {
            carry = carry << 1;
            int tmpCarry = a & carry;
            a = a ^ carry;
            carry = tmpCarry;
        }
        return a;
    }

}
