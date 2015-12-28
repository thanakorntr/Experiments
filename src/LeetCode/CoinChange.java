package LeetCode;

/**
 *
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

 Example 1:
 coins = [1, 2, 5], amount = 11
 return 3 (11 = 5 + 5 + 1)

 Example 2:
 coins = [2], amount = 3
 return -1.

 Note:
 You may assume that you have an infinite number of each kind of coin.
 * Created by Thanakorn on 12/27/15.
 */
public class CoinChange {

    public static void main(String[] args) {

    }

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return 0;
        }

        int[] numCoinForEachPrice = new int[amount + 1];
        numCoinForEachPrice[0] = 0;
        for (int amt = 1; amt <= amount; amt++) {
            numCoinForEachPrice[amt] = -1;
            for (int coin : coins) {
                if (coin == amt) {
                    numCoinForEachPrice[amt] = 1;
                    break;
                }
                int remainingAmt = amt - coin;
                if (remainingAmt > 0 && numCoinForEachPrice[remainingAmt] != -1) {
                    if (numCoinForEachPrice[amt] == -1) {
                        numCoinForEachPrice[amt] = 1 + numCoinForEachPrice[remainingAmt];
                    } else {
                        numCoinForEachPrice[amt] =
                                Math.min(numCoinForEachPrice[amt], 1 + numCoinForEachPrice[remainingAmt]);
                    }
                }
            }
        }

        return numCoinForEachPrice[amount];
    }
}
