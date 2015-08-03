import java.util.*;

/**
 * Created by Thanakorn on 7/21/15.
 */
public class RepeatedDNASequences {

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> repeatedDNA = findRepeatedDnaSequences(s);
        System.out.println(repeatedDNA.toString());
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        if (s.isEmpty() || s.length() < 10) {
            return ans;
        }

        Map<Character, Integer> charMap = new HashMap<>();
        charMap.put('A', 0);
        charMap.put('T', 1);
        charMap.put('C', 2);
        charMap.put('G', 3);

        Set<Integer> visitedHashes = new HashSet<>();
        Set<Integer> addedHashes = new HashSet<>();

        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i < 9) {
                hash = (hash << 2) + charMap.get(s.charAt(i));
            } else {
                hash = (hash << 2) + charMap.get(s.charAt(i));
                hash = hash & (1 << 20) - 1;
                if (!visitedHashes.contains(hash)) {
                    visitedHashes.add(hash);
                } else {
                    if (!addedHashes.contains(hash)) {
                        addedHashes.add(hash);
                        ans.add(s.substring(i-9, i+1));
                    }
                }
            }
        }


        return ans;
    }
}
