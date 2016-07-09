package LeetCode;

import java.util.*;

/**
 * You are given a range [first, last], initially white. You need to paint it black.
 * For this purpose you have a set of triples
 * [(f, l, cost), ...] - where each triple means that you can paint range [f, l] for `cost` coins (limitations: cost is floating point >= 0, f, l, first, last are integers).
 * Find minimum cost needed to paint the whole range [first, last] or return -1 if it's impossible
 * Example:
 * <p>
 * <p>
 * [first, last] = [0, 5] and set of triples is
 * [[0, 5, 10], [0, 4, 1], [0, 2,5], [2, 5, 1]]
 * Clearly the answer is to take [0, 4, 1] and [2, 5, 1] - the total cost will be 2.
 * Another example:
 * <p>
 * <p>
 * [first, last] = [0, 5]
 * triples are [[1,4, 10], [2, 5, 6]]
 * answer is -1, because it's impossible to color whole range.
 * <p>
 * <p>
 * <p>
 * Created by Thanakorn on 7/8/16.
 */
class RangeCost {

    public int startIndex;
    public int endIndex;
    public float cost;

    public RangeCost(int startIndex, int endIndex, float cost) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.cost = cost;
    }

}

public class PaintBoard {

    public static void main(String[] args) {

        int firstIndex = 0, lastIndex = 5;
        List<RangeCost> rangeCosts = new ArrayList<>();
        rangeCosts.add(new RangeCost(0, 5, 10));
        rangeCosts.add(new RangeCost(0, 4, 1));
        rangeCosts.add(new RangeCost(0, 2, 5));
        rangeCosts.add(new RangeCost(2, 5, 1));
        System.out.println(minCost(firstIndex, lastIndex, rangeCosts));  // 2

        firstIndex = 0;
        lastIndex = 5;
        rangeCosts = new ArrayList<>();
        rangeCosts.add(new RangeCost(1, 4, 10));
        rangeCosts.add(new RangeCost(2, 5, 6));
        System.out.println(minCost(firstIndex, lastIndex, rangeCosts));  // -1

    }

    private static float minCost(int L, int R, List<RangeCost> rangeCosts) {
        rangeCosts.sort((r1, r2) -> {
            if (r1.endIndex == r2.endIndex) {
                return r2.startIndex - r1.startIndex;
            }
            return r1.endIndex - r2.endIndex;
        });

        float[] dp = new float[R + 1];
        for (int j = L + 1; j <= R; j++) {
            float min = Float.MAX_VALUE;
            int i = rangeCosts.size() - 1;
            while (i >= 0 && rangeCosts.get(i).endIndex >= j) {
                if (rangeCosts.get(i).startIndex < j) {
                    if (dp[rangeCosts.get(i).startIndex] != -1 && dp[rangeCosts.get(i).startIndex] + rangeCosts.get(i).cost < min) {
                        min = dp[rangeCosts.get(i).startIndex] + rangeCosts.get(i).cost;
                    }
                }
                i--;
            }
            if (min == Float.MAX_VALUE) {
                dp[j] = -1;
            } else {
                dp[j] = min;
            }
        }
        return dp[R];
    }

}
