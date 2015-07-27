import java.util.*;

/**
 * Created by Thanakorn on 7/21/15.
 */
public class RepeatedDNASequences {

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> repeatedDNA = findRepeatedDnaSequences(s);
        System.out.println(repeatedDNA.toString());
//        String a = "ABC";
//        String b = "ABC";
//        System.out.println(a.hashCode());
//        System.out.println(b.hashCode());
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        if (s.isEmpty() || s.length() < 10) {
            return ans;
        }

        Map<Integer, Integer> visitedHash = new HashMap<>();

        int endIndex = 10;
        for (int startIndex = 0; endIndex <= s.length(); startIndex++, endIndex++) {
            String subStr = s.substring(startIndex, endIndex);
            if (!visitedHash.containsKey(subStr.hashCode())) {
                visitedHash.put(subStr.hashCode(), 1);
            } else {
                visitedHash.put(subStr.hashCode(), 2);
            }
        }

        endIndex = 10;
        for (int startIndex = 0; endIndex <= s.length(); startIndex++, endIndex++) {
            String subStr = s.substring(startIndex, endIndex);
            int hashCode = subStr.hashCode();
            if (visitedHash.get(hashCode) > 1) {
                ans.add(subStr);
            }
        }

        return ans;
    }
}
