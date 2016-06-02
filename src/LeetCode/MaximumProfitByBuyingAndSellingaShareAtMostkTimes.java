package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-k-times/
 *
 * In share trading, a buyer buys shares and sells on future date. Given stock price of n days, the trader is allowed to make at most k transactions, where new transaction can only start after previous transaction is complete, find out maximum profit that a share trader could have made.

 Input:
 Price = [10, 22, 5, 75, 65, 80]
 K = 2
 Output:  87
 Trader earns 87 as sum of 12 and 75
 Buy at price 10, sell at 22, buy at
 5 and sell at 80

 Input:
 Price = [12, 14, 17, 10, 14, 13, 12, 15]
 K = 3
 Output:  12
 Trader earns 12 as sum of 5, 4 and 3
 Buy at price 12, sell at 17, buy at 10
 and sell at 14 and buy at 12 and sell
 at 15

 Input:
 Price = [100, 30, 15, 10, 8, 25, 80]
 K = 3
 Output:  72
 Only one transaction. Buy at price 8
 and sell at 80.

 Input:
 Price = [90, 80, 70, 60, 50]
 K = 1
 Output:  0
 Not possible to earn.
 *
 * Created by Thanakorn on 6/1/16.
 */
public class MaximumProfitByBuyingAndSellingaShareAtMostkTimes {

    public static void main(String[] args) {
        int[] prices = new int[]{10, 22, 5, 75, 65, 80};
        int k = 2;
        System.out.println(findMaximumProfit(prices, k));  // 87

        prices = new int[]{12, 14, 17, 10, 14, 13, 12, 15};
        k = 3;
        System.out.println(findMaximumProfit(prices, k));  // 12

        prices = new int[]{100, 30, 15, 10, 8, 25, 80};
        k = 3;
        System.out.println(findMaximumProfit(prices, k));  // 72

        prices = new int[]{90, 80, 70, 60, 50};
        k = 1;
        System.out.println(findMaximumProfit(prices, k));  // 0
    }

    private static int findMaximumProfit(int[] prices, int k) {
        Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();
        return findMaximumProfitHelper(prices, k, 0, memo);
    }

    private static int findMaximumProfitHelper(int[] prices, int k,
                                               int startIndex,
                                               Map<Integer, Map<Integer, Integer>> memo) {

        if (memo.containsKey(startIndex) && memo.get(startIndex).containsKey(k)) {
            return memo.get(startIndex).get(k);
        }

        if (k == 0 || startIndex >= prices.length - 1) {
            return 0;
        }

        if (!memo.containsKey(startIndex)) {
            memo.put(startIndex, new HashMap<>());
        }

        int maxPrice = 0;
        int localMin = prices[startIndex];
        int localMax = Integer.MIN_VALUE;
        for (int i = startIndex + 1; i < prices.length; i++) {
            localMax = Math.max(localMax, prices[i] - localMin);
            localMin = Math.min(localMin, prices[i]);
            if (!memo.containsKey(i + 1) || !memo.get(i + 1).containsKey(k - 1)) {
                if (!memo.containsKey(i + 1)) {
                    memo.put(i + 1, new HashMap<>());
                }
                memo.get(i + 1).put(k - 1, findMaximumProfitHelper(prices, k - 1, i + 1, memo));
            }
            int maxPriceSubproblem = memo.get(i + 1).get(k - 1);
            localMax = localMax > 0 ? localMax : 0;
            maxPrice = Math.max(maxPrice, localMax + maxPriceSubproblem);
        }

        memo.get(startIndex).put(k, maxPrice);
        return maxPrice;
    }

}
