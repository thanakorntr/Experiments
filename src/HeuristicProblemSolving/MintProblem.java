package HeuristicProblemSolving;

import com.sun.tools.javac.util.Pair;

import java.util.Arrays;

/**
 * Created by Thanakorn on 9/18/15.
 */
public class MintProblem {

    private static final int numDenominations = 7;

    public static void main(String[] args) {
        int maxPrice = 249;
        int N = 5;
        Pair<Integer, int[]> ans = designDenominations(maxPrice, N);
        System.out.println("Score: " + ans.fst);
        System.out.println("Denominations: " + Arrays.toString(ans.snd));
    }

    private static Pair<Integer, int[]> bruteForceSol(int maxPrice, int N) {
        int smallestScore = Integer.MAX_VALUE;
        int[] bestDenomination = null;


        Pair<Integer, int[]> ans = new Pair<>(smallestScore, bestDenomination);
        return ans;
    }

    private static Pair<Integer, int[]> designDenominations(int maxPrice, int N) {
        int smallestScore = Integer.MAX_VALUE;
        int[] bestDenomination = null;


        Pair<Integer, int[]> ans = new Pair<>(smallestScore, bestDenomination);
        return ans;
    }

    private static int determineScore(int[] numMinCoinArray, int N) {
        int score = 0;
        for (int i = 1; i < numMinCoinArray.length; i++) {
            if (i % 5 != 0) {
                score += numMinCoinArray[i];
            } else {
                score += N * numMinCoinArray[i];
            }
        }
        return score;
    }

    private static int smallestNumberOfChange(int price, int[] denominations) {
        int[] ansArray = new int[price+1];
        ansArray[0] = 0;

        for (int curPrice = 1; curPrice <= price; curPrice++) {
            ansArray[curPrice] = Integer.MAX_VALUE;
            for (int denomination : denominations) {
                if (curPrice == denomination) {
                    ansArray[curPrice] = 1;
                    break;
                } else if (curPrice > denomination) {
                    ansArray[curPrice] =
                            Math.min(ansArray[curPrice], 1 + ansArray[curPrice - denomination]);
                }
            }
            if (ansArray[curPrice] == Integer.MAX_VALUE) {
                ansArray[curPrice] = curPrice;
            }
        }

        return ansArray[price];
    }
}
