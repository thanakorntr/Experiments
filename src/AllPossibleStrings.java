import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 6/5/15.
 */
public class AllPossibleStrings {

    public static void main(String[] args) {
        String test = "10?01?011?100?";
        List<String> allStrings = getAllStrings(test);

        printAllStrings(allStrings);
    }

    public static List<String> getAllStrings(String str) {
        if (str.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> allStrings = new ArrayList<>();

        int i = str.indexOf('?');

        if (i == -1) {
            allStrings.add(str);
            return allStrings;
        }

        String s0 = str.substring(0, i);
        String s1 = str.substring(0, i);
        s0 += "0";
        s1 += "1";

        String nextStr = i+1 <= str.length() ? str.substring(i+1) : "";
        List<String> allNextStrs = getAllStrings(nextStr);

        if (allNextStrs.isEmpty()) {
            allStrings.add(s0);
            allStrings.add(s1);
            return allStrings;
        }

        for (String subStr : allNextStrs) {
            String ans1 = s0 + subStr;
            String ans2 = s1 + subStr;
            allStrings.add(ans1);
            allStrings.add(ans2);
        }

        return allStrings;
    }

    public static void printAllStrings(List<String> strs) {
        for (String string : strs) {
            System.out.println(string);
        }
    }
}
