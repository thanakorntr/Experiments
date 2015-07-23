import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thanakorn on 7/21/15.
 */
public class RepeatedDNASequences {

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> repeatedDNA = findRepeatedDnaSequences(s);
        System.out.println(repeatedDNA.toString());
        String a = "ABC";
        String b = "ABC";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        if (s.isEmpty() || s.length() < 10) {
            return ans;
        }

        Map<String, Integer> strings = new HashMap<>();
        int endIndex = 10;

        for (int startIndex = 0; endIndex <= s.length(); startIndex++, endIndex++) {
            String subStr = s.substring(startIndex, endIndex);
            if (!strings.containsKey(subStr)) {
                strings.put(subStr, 1);
            } else {
                strings.put(subStr, strings.get(subStr) + 1);
            }
        }

        for (String subStr : strings.keySet()) {
            if (strings.get(subStr) > 1) {
                ans.add(subStr);
            }
        }

        return ans;
    }
}
