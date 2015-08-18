package LeetCode;

/**
 * Created by Thanakorn on 8/17/15.
 */
public class AddBinary {

    public String addBinary(String a, String b) {
        if (a == null || b == null || a.isEmpty() || b.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int aIndex = a.length()-1, bIndex = b.length()-1; aIndex >= 0 || bIndex >= 0; aIndex--, bIndex--) {
            int aVal = aIndex >= 0 ? a.charAt(aIndex) - '0' : 0;
            int bVal = bIndex >= 0 ? b.charAt(bIndex) - '0' : 0;
            int curVal = aVal + bVal + carry;
            carry = curVal > 1 ? 1 : 0;
            curVal = curVal % 2;
            sb.append(curVal);
        }
        if (carry == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }
}
