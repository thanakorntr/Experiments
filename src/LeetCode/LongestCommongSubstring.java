package LeetCode;

/**
 * Created by Thanakorn on 12/1/15.
 */
public class LongestCommongSubstring {


    public static void main(String[] args) {

        String t1 = "Thanakorn";
        String t2 = "simplenakomd";
        System.out.println(longestCommonSubstring(t1, t2));

    }

    private static int longestCommonSubstring(String textString, String hypothesisString) {

        int lcsLen = 0;

        int[][] longestCommonSuffix = new int[textString.length() + 1][hypothesisString.length() + 1];
        for (int row = 0; row < longestCommonSuffix.length; row++) {
            longestCommonSuffix[row][0] = 0;
        }
        for (int col = 0; col < longestCommonSuffix[0].length; col++) {
            longestCommonSuffix[0][col] = 0;
        }

        for (int tindex = 1; tindex < longestCommonSuffix.length; tindex++) {
            for (int hindex = 1; hindex < longestCommonSuffix[tindex].length; hindex++) {
                if (textString.charAt(tindex - 1) == hypothesisString.charAt(hindex - 1)) {
                    longestCommonSuffix[tindex][hindex] = longestCommonSuffix[tindex - 1][hindex - 1] + 1;
                } else {
                    longestCommonSuffix[tindex][hindex] = 0;
                }
                lcsLen = Math.max(lcsLen, longestCommonSuffix[tindex][hindex]);
            }
        }

        return lcsLen;
    }
}
