package LeetCode;

/**
 * Painter Problems
 * <p>
 * Paint Fence
 * <p>
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 * Return the total number of ways you can paint the fence.
 * Note
 * <p>
 * n and k are non-negative integers.
 * <p>
 * Created by Thanakorn on 7/9/16.
 */
public class PaintFences {

    public static void main(String[] args) {

        int n = 5;
        int k = 3;
        System.out.println(numWays(n, k));
        System.out.println(numWays2(n, k));

        n = 4;
        k = 10;
        System.out.println(numWays(n, k));
        System.out.println(numWays2(n, k));

        n = 3;
        k = 7;
        System.out.println(numWays(n, k));
        System.out.println(numWays2(n, k));
    }

    // my own implementation
    private static int numWays2(int n, int k) {
        if (n == 0 || k == 0) {
            return 0;
        }

        int endSingle = 1;
        int endDouble = 0;

        for (int i = 1; i < n; i++) {
            int tmpEndSingle = endSingle;
            endSingle = (endSingle + endDouble) * (k - 1);
            endDouble = tmpEndSingle;
        }

        return (endSingle + endDouble) * k;
    }

    // website's implementation
    private static int numWays(int n, int k) {
        if (n == 0 || k == 0) {
            return 0;
        }
        int same = k;
        if (n == 1)
            return same;
        int noSame = k * (k - 1);
        for (int i = 2; i < n; i++) {
            int tmp = noSame;
            noSame = (same + noSame) * (k - 1);
            same = tmp * 1;
        }
        return noSame + same;
    }

}
