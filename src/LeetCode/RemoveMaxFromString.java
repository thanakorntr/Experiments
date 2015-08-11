package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanakorn on 6/12/15.
 */
public class RemoveMaxFromString {

    public static void main(String[] args) {
        String s1 = "aaabbcccc";

        System.out.println(removeCharMax(s1, 2));
        System.out.println(removeCharMax(s1, 1));
    }

    public static String removeCharMax(String s, int max) {
        if (s.isEmpty() || max <= 0) {
            return "";
        }

        Map<Character, Integer> charFrequency = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charFrequency.containsKey(c)) {
                if (charFrequency.get(c) < max) {
                    sb.append(c);
                }
                charFrequency.put(c, charFrequency.get(c) + 1);
            } else {
                sb.append(c);

                charFrequency.put(c, 1);
            }
        }

        return sb.toString();
    }
}
