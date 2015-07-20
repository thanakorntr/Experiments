/**
 * Created by Thanakorn on 7/19/15.
 */
public class MyAtoi {

    public static void main(String[] args) {

    }

    public static int myAtoi(String str) {

        int startIndex = -1;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ') {
                startIndex = i;
                break;
            }
        }

        if (str.charAt(startIndex) == '+') {

        }
        if (str.charAt(startIndex) == '-') {

        }
        return 0;
    }
}
