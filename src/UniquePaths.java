import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by Thanakorn on 6/18/15.
 */
public class UniquePaths {

    public static void main(String[] args) {
        System.out.println(uniquePathsCombinatorics(36, 7));
        System.out.println(uniquePathsDP(36, 7));
        System.out.println(uniquePathsDPOptimized(36, 7));

        System.out.println(uniquePathsCombinatorics(7, 3));
        System.out.println(uniquePathsDP(7, 3));
        System.out.println(uniquePathsDPOptimized(7, 3));
    }

    public static int uniquePathsDPOptimized(int row, int col) {
        if (row < 1 || col < 1) {
            return 0;
        }

        if (row == 1 || col == 1) {
            return 1;
        }

        int[] bottomRowMemo = new int[col];
        int[] upperRowMemo = new int[col];
        Arrays.fill(bottomRowMemo, 1);

        for (int numRow = row - 2; numRow >= 0; numRow--) {
            upperRowMemo[col-2] = bottomRowMemo[col-2] + 1;
            for (int numCol = col-3; numCol >= 0; numCol--) {
                upperRowMemo[numCol] = upperRowMemo[numCol+1] + bottomRowMemo[numCol];
            }

            bottomRowMemo = upperRowMemo;
        }

        return upperRowMemo[0];
    }

    public static int uniquePathsDP(int row, int col) {
        if (row < 1 || col < 1) {
            return 0;
        }

        if (row == 1 || col == 1) {
            return 1;
        }

        int[][] memo = new int[row][col];

        for (int i = 0; i < col; i++) {
            memo[row-1][i] = 1;
        }
        for (int i = 0; i < row; i++) {
            memo[i][col-1] = 1;
        }

        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                memo[i][j] = memo[i+1][j] + memo[i][j+1];
            }
        }

        return memo[0][0];
    }

    public static int uniquePathsCombinatorics(int m, int n) {
        if (m <= 1 || n <= 1) {
            return 1;
        }

        BigInteger x = factorial(m+n-2);
        BigInteger y1 = factorial(m-1);
        BigInteger y2 = factorial(n-1);

        // return x / y1 / y2
        return x.divide(y1).divide(y2).intValue();
    }

    public static BigInteger factorial(int n) {
        if (n <= 1) {
            return BigInteger.ONE;
        }

        return BigInteger.valueOf(n).multiply(factorial(n-1));
    }
}
