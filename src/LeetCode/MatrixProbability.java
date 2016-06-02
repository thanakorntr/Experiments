package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/a-matrix-probability-question/
 *
 * A matrix probability question
 Given a rectangular matrix, we can move from current cell in 4 directions with equal probability.
 The 4 directions are right, left, top or bottom. Calculate the Probability that after N moves from a given
 position (i, j) in the matrix, we will not cross boundaries of the matrix at any point.
 *
 * Created by Thanakorn on 6/1/16.
 */
public class MatrixProbability {

    public static void main(String[] args) {
        int m = 5, n = 5;
        int x = 1, y = 1;
        int numSteps = 2;
        System.out.println(findProbability(x, y, m, n, numSteps));  // 0.875
    }

    private static class ProbPair {
        public int x;
        public int y;

        public ProbPair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = ((hash + x) << 5) - (hash + x);
            hash = ((hash + y) << 5) - (hash + y);
            return hash;
        }

        @Override
        public boolean equals(Object p) {
            return this.x == ((ProbPair)p).x && this.y == ((ProbPair)p).y;
        }
    }

    private static double findProbability(int x, int y, int m, int n, int numSteps) {
        Map<ProbPair, Map<Integer, Double>> memo = new HashMap<>();
        return findProbabilityHelper(x, y, m, n, numSteps, memo);
    }

    private static double findProbabilityHelper(int x, int y,
                                                int m, int n,
                                                int numSteps, Map<ProbPair, Map<Integer, Double>> memo) {

        if (x < 0 || x >= m || y < 0 || y >= n) {
            return 0;
        }
        if (numSteps == 0) {
            return 1;
        }

        ProbPair probPair = new ProbPair(x, y);
        if (memo.containsKey(probPair) && memo.get(probPair).containsKey(numSteps)) {
            return memo.get(probPair).get(numSteps);
        }

        if (!memo.containsKey(probPair)) {
            memo.put(probPair, new HashMap<>());
        }

        double prob = 0;

        prob += 0.25 * findProbabilityHelper(x - 1, y, m, n, numSteps - 1, memo);
        prob += 0.25 * findProbabilityHelper(x + 1, y, m, n, numSteps - 1, memo);
        prob += 0.25 * findProbabilityHelper(x, y - 1, m, n, numSteps - 1, memo);
        prob += 0.25 * findProbabilityHelper(x, y + 1, m, n, numSteps - 1, memo);

        memo.get(probPair).put(numSteps, prob);
        return prob;
    }
}
