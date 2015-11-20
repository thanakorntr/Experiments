package LeetCode;

/**
 * Created by Thanakorn on 11/17/15.
 *
 * Additive number is a positive integer whose digits can form additive sequence.

 A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

 For example:
 "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8

 "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.

 1 + 99 = 100, 99 + 100 = 199

 Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

 Given a string represents an integer, write a function to determine if it's an additive number.

 Follow up:
 How would you handle overflow for very large input integers?
 */
public class AdditiveNumber {

    public static void main(String[] args) {

        System.out.println(isAdditiveNumber("123"));

    }

    public static boolean isAdditiveNumber(String num) {

        if (num.length() < 3) {
            return false;
        }

        int maxInitialFirstNum = (num.length() - 1) / 2;

        for (int firstNumEndIndex = 1; firstNumEndIndex <= maxInitialFirstNum; firstNumEndIndex++) {
            for (int secondNumEndIndex = firstNumEndIndex + 1;
                Math.max(firstNumEndIndex, secondNumEndIndex - firstNumEndIndex) <= num.length() - secondNumEndIndex;
                 secondNumEndIndex++) {

                String num1 = num.substring(0, firstNumEndIndex);
                String num2 = num.substring(firstNumEndIndex, secondNumEndIndex);
                String remainingNum = num.substring(secondNumEndIndex);
                if (isAdditiveHelper(num1, num2,  remainingNum)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isAdditiveHelper(String num1, String num2, String remainingNum) {

        if (remainingNum.isEmpty()) {
            return true;
        }

        if (remainingNum.startsWith("0")) {
            return false;
        }

        int len1 = Math.max(num1.length(), num2.length());
        int len2 = len1 + 1;

        if (remainingNum.length() < len1) {
            return false;
        }

        long num1Long = Long.parseLong(num1);
        long num2Long = Long.parseLong(num2);
        String nextPossibleSeqStr = remainingNum.substring(0, len1);
        long nextPossibleSeq = Long.parseLong(nextPossibleSeqStr);

        if (num1Long + num2Long == nextPossibleSeq) {
            if (isAdditiveHelper(num2, nextPossibleSeqStr, remainingNum.substring(len1))) {
                return true;
            }
        }

        if (remainingNum.length() < len2) {
            return false;
        }

        nextPossibleSeqStr = remainingNum.substring(0, len2);
        nextPossibleSeq = Long.parseLong(nextPossibleSeqStr);

        if (num1Long + num2Long == nextPossibleSeq) {
            if (isAdditiveHelper(num2, nextPossibleSeqStr, remainingNum.substring(len2))) {
                return true;
            }
        }

        return false;
    }
}
