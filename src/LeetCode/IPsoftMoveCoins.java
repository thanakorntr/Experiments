package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 8/31/15.
 */
public class IPsoftMoveCoins {

    public static int maxNum = Integer.MIN_VALUE;

    public static void main(String[] args) {
        findAns(2);
    }

    private static void findAns(int numCoins) {
        List<Integer> coins = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i != 0) {
                coins.add(0);
            } else {
                coins.add(numCoins);
            }
        }
        coins.set(0,0);
        coins.set(1, 8);
        findAnsHelper(coins);
    }

    public static void findAnsHelper(List<Integer> coins) {
        if (coins.get(0) == 0 && coins.get(1) == 0 && coins.get(2) == 0) {
            if (coins.get(3) > maxNum) {
                maxNum = coins.get(3);
                System.out.println(maxNum);
            }
            return;
        }

        if (coins.get(0) > 0) {
            // add 2 to next
            findAnsHelper(add2ToNext(coins, 0));

            // swap next 2
            findAnsHelper(swapNext2(coins, 0));
        }
        if (coins.get(1) > 0) {
            // add 2 to next
            findAnsHelper(add2ToNext(coins, 1));

            // swap next 2
            findAnsHelper(swapNext2(coins, 1));
        }
        if (coins.get(2) > 0) {
            // add 2 to next
            findAnsHelper(add2ToNext(coins, 2));
        }
    }

    private static List<Integer> add2ToNext(List<Integer> coins, int index) {
        List<Integer> newCoins = new ArrayList<>(coins);
        newCoins.set(index, coins.get(index) - 1);
        newCoins.set(index + 1, coins.get(index + 1) + 2);
        return newCoins;
    }

    private static List<Integer> swapNext2(List<Integer> coins, int index) {
        List<Integer> newCoins = new ArrayList<>(coins);
        newCoins.set(index, coins.get(index) - 1);
        int tmp = coins.get(index + 1);
        newCoins.set(index + 1, coins.get(index + 2));
        newCoins.set(index + 2, tmp);
        return newCoins;
    }
}
