package LeetCode;

/**
 * Created by Thanakorn on 7/11/15.
 */
public class ReverseInteger {

    public static void main(String[] args) {

        int x = -123;

        System.out.println(reverse(x));
    }

    public static int reverse(int x) {
        StringBuilder sb = new StringBuilder();
        if (x < 0) {
            x *= -1;
            sb.append("-");
        }

        char[] ints = Integer.toString(x).toCharArray();
        for (int i = ints.length - 1; i >= 0; i--) {
            sb.append(ints[i]);
        }

        try {
            int ans = Integer.parseInt(sb.toString());
            return ans;
        } catch (Exception e) {
            return 0;
        }
    }
}
