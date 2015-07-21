/**
 * Created by Thanakorn on 7/19/15.
 */
public class MyAtoi {

    public static void main(String[] args) {

    }

    public static int myAtoi(String str) {

        if (str.isEmpty()) {
            return 0;
        }

        int startIndex = -1;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ') {
                startIndex = i;
                break;
            }
        }

        int mul = 1;
        if (str.charAt(startIndex) == '+') {
            startIndex++;
        } else if (str.charAt(startIndex) == '-') {
            mul = -1;
            startIndex++;
        }

        if (startIndex >= str.length()) {
            return 0;
        }
        if (!str.substring(startIndex, startIndex+1).matches("[0-9]")) {
            return 0;
        }

        int endIndex = str.length();
        for (int i = startIndex+1; i < str.length(); i++) {
            String curChar = str.substring(i,i+1);
            if (!curChar.matches("[0-9]")) {
                endIndex = i;
                break;
            }
        }

        try {
            int num = Integer.parseInt(str.substring(startIndex, endIndex));
            return num * mul;
        } catch (NumberFormatException e) {
            if (mul == 1) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }
    }
}
