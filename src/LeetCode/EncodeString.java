package LeetCode;

/**
 * Encode a string so that any repeated characters in a row are translated to
 * <number of repetition>x<char>.
 * For example, abbbc => a3xbc. a3xxxc => a33xxc
 * https://www.glassdoor.com/Interview/Google-Software-Engineer-Interview-Questions-EI_IE9079.0,6_KO7,24_IP11.htm
 *
 * Created by Thanakorn on 2/6/16.
 */
public class EncodeString {

    public static void main(String[] args) {

        String str1 = "abbbc";
        System.out.println(encodeString(str1));  // a3xbc
        String str2 = "a3xxxc";
        System.out.println(encodeString(str2));  // a33xxc

    }

    private static String encodeString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str == null || str.isEmpty()) {
            return str;
        }

        char lastChar = str.charAt(0);
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            char curChar = str.charAt(i);
            if (curChar == lastChar) {
                count++;
            } else {
                if (count > 1) {
                    stringBuilder.append(count).append('x').append(lastChar);
                } else {
                    stringBuilder.append(lastChar);
                }
                lastChar = curChar;
                count = 1;
            }
        }
        if (count > 1) {
            stringBuilder.append(count).append('x').append(lastChar);
        } else {
            stringBuilder.append(lastChar);
        }

        return stringBuilder.toString();
    }
}
