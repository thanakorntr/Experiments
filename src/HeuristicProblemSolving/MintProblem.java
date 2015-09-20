package HeuristicProblemSolving;

/**
 * Created by Thanakorn on 9/18/15.
 */
public class MintProblem {

    public static final int numDenominations = 7;
    public static final int maxPrice = 239;

    public static double determineScore(int N, int[] denominations) {
        int[] numMinCoinArray = new int[maxPrice+1];
        numMinCoinArray[0] = 0;
        double score = 0;

        for (int curPrice = 1; curPrice <= maxPrice; curPrice++) {
            numMinCoinArray[curPrice] = Integer.MAX_VALUE;
            for (int denomination : denominations) {
                if (curPrice == denomination) {
                    numMinCoinArray[curPrice] = 1;
                    break;
                } else if (curPrice > denomination) {
                    numMinCoinArray[curPrice] =
                            Math.min(numMinCoinArray[curPrice], 1 + numMinCoinArray[curPrice - denomination]);
                }
            }
            if (numMinCoinArray[curPrice] == Integer.MAX_VALUE) {
                numMinCoinArray[curPrice] = curPrice;
            }

            if (curPrice % 5 == 0) {
                score += N * numMinCoinArray[curPrice];
            } else {
                score += numMinCoinArray[curPrice];
            }
        }

        return score;
    }
}
