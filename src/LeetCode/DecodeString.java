package LeetCode;

/**
 * Given an encoded string, return it's decoded string.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
 exactly k times. Note that k is guaranteed to be a positive integer.

 You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

 Furthermore, you may assume that the original data does not contain any digits and that digits are only for those
 repeat numbers, k. For example, there won't be input like 3a or 2[4].

 Examples:

 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 *
 * Created by Thanakorn on 9/17/16.
 */
public class DecodeString {

    public static void main(String[] args) {

        DecodeString decodeString = new DecodeString();

        String str = "3[a]2[bc]";
        System.out.println(decodeString.decodeString(str));  // aaabcbc

        str = "3[a2[c]]";
        System.out.println(decodeString.decodeString(str));  // accaccacc

        str = "2[abc]3[cd]ef";
        System.out.println(decodeString.decodeString(str));  // abcabccdcdcdef
    }

    public String decodeString(String s) {

        StringBuilder stringBuilder = new StringBuilder();

        int startNumIndex = -1;
        int endNumIndex = -1;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                if (startNumIndex == -1) {
                    startNumIndex = i;
                } else {
                    endNumIndex = i;
                }
            } else {
                if (s.charAt(i) == '[') {
                    endNumIndex = i - 1;
                }
                if (endNumIndex == -1) {
                    stringBuilder.append(s.charAt(i));
                } else {
                    int numDup = Integer.parseInt(s.substring(startNumIndex, endNumIndex + 1));
                    int leftBracketCount = 1;
                    int leftBracketIndex = i;
                    for (int j = i + 1; j < s.length(); j++) {
                        if (s.charAt(j) == '[') {
                            leftBracketCount++;
                        } else if (s.charAt(j) == ']') {
                            leftBracketCount--;
                        }
                        if (leftBracketCount == 0) {
                            String inBracket = s.substring(leftBracketIndex + 1, j);
                            String decodedInBracket = decodeString(inBracket);
                            for (int k = 0; k < numDup; k++) {
                                stringBuilder.append(decodedInBracket);
                            }

                            String nextDecode = decodeString(s.substring(j + 1));
                            stringBuilder.append(nextDecode);
                            return stringBuilder.toString();
                        }
                    }
                }
            }
        }

        return stringBuilder.toString();
    }

}
