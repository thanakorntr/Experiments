package LeetCode;

/**
 * Created by Thanakorn on 11/29/15.
 * <p>
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * <p>
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 * <p>
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 */
public class BuyStockWithCooldown {

    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices) {

        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int b0 = -prices[0], b1 = b0, b2 = b0;
        int s0 = 0, s1 = s0, s2 = s0;

        for (int i = 1; i < prices.length; i++) {
            b0 = Math.max(b1, s2 - prices[i]);
            s0 = Math.max(s1, b1 + prices[i]);
            b2 = b1;
            b1 = b0;
            s2 = s1;
            s1 = s0;
        }

        return s0;
    }
}
