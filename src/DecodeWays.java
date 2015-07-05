import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanakorn on 6/27/15.
 */
public class DecodeWays {

    public static void main(String[] args) {

        String str = "12";

        System.out.println(numDecodings(str));

    }

    public static int numDecodings(String s) {
        Map<String, Integer> memo = new HashMap<>();
        return numDecodingsHelper(s, memo);
    }

    public static int numDecodingsHelper(String s, Map<String, Integer> memo) {

        if (s.isEmpty()) {
            return 0;
        }

        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        if (s.length() == 1) {
            if (isDecodable(s)) {
                memo.put(s, 1);
                return 1;
            }
            memo.put(s, 0);
            return 0;
        } else {
            int len1 = 0;
            String part1 = s.substring(0,1);
            String part1Next = s.substring(1);
            if (isDecodable(part1)) {
                memo.put(part1, 1);
                if (memo.containsKey(part1Next)) {
                    if (memo.get(part1Next) != 0) {
                        len1 = memo.get(part1Next);
                    }
                } else {
                    int part1NextLen = numDecodingsHelper(part1Next, memo);
                    memo.put(part1Next, part1NextLen);
                    if (part1NextLen != 0) {
                        len1 = part1NextLen;
                    }
                }
            }


            int len2 = 0;
            String part2 = s.substring(0,2);
            String part2Next = s.substring(2);
            if (isDecodable(part2)) {
                if (!part2Next.isEmpty()) {
                    if (memo.containsKey(part2Next)) {
                        if (memo.get(part2Next) != 0) {
                            len2 = memo.get(part2Next);
                        }
                    } else {
                        int part2NextLen = numDecodingsHelper(part2Next, memo);
                        memo.put(part2Next, part2NextLen);
                        if (part2NextLen != 0) {
                            len2 = part2NextLen;
                        }
                    }
                } else {
                    len2 = 1;
                }
            }

            memo.put(s, len1 + len2);
            return len1 + len2;
        }

    }

    public static boolean isDecodable(String token) {
        if (token.isEmpty() || (token.startsWith("0") && token.length() > 1)) {
            return false;
        }

        try {
            int i = Integer.parseInt(token);
            return i >= 1 && i <= 26;
        } catch (NumberFormatException e) {
            return  false;
        }

    }
}
