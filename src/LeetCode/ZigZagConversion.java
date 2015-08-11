package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 6/27/15.
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        String str = "ABCDEF";
        System.out.println(convert(str, 3));
    }

    public static String convert(String s, int numRows) {
        List<List<String>> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new ArrayList<String>());
        }

        int curRow = 0;
        int direction = 1;
        for (int i = 0; i < s.length(); i++) {
            rows.get(curRow).add(s.substring(i, i+1));
            curRow += direction;
            if (curRow == numRows) {
                curRow = numRows-2;
                direction = -1;
                if (curRow <= 0) {
                    curRow = 0;
                    direction = 1;
                }
            }
            else if (curRow == -1) {
                curRow = 1;
                direction = 1;
                if (curRow >= numRows) {
                    curRow = 0;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (List<String> row : rows) {
            for (String c : row) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
