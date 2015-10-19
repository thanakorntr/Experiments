package LeetCode;

/**
 * Created by Thanakorn on 10/18/15.
 */
public class KnapSack {
    
    // 0/1 knapsack
    private static int knapSack(int[] weights, int[] prices, int W) {

        int numItems = weights.length;
        int[] prevKnapSackPrices = new int[W+1];

        for (int itemId = 0; itemId < numItems; itemId++) {
            int[] curKnapSackPrices = new int[W+1];
            for (int remainingWeight = 1; remainingWeight < W+1; remainingWeight++) {
                if (remainingWeight >= weights[itemId]) {
                    curKnapSackPrices[remainingWeight] = Math.max(prevKnapSackPrices[remainingWeight-weights[itemId]] + prices[itemId],
                                                                  prevKnapSackPrices[remainingWeight]);
                } else {
                    curKnapSackPrices[remainingWeight] = prevKnapSackPrices[remainingWeight];
                }
            }
            prevKnapSackPrices = curKnapSackPrices;
        }

        return prevKnapSackPrices[W];
    }
}
