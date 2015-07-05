import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thanakorn on 7/3/15.
 */
public class IngenuousCubrency {

    public static void main(String[] args) {

        int amount = 9999;

        long t0 = System.currentTimeMillis();
        System.out.println(countWays(amount));
        long t1 = System.currentTimeMillis();
        System.out.println("Time taken: " + (t1-t0)*Math.pow(10, -3) + " sec");

        System.out.println(countWays(10));  // 2
        System.out.println(countWays(21));  // 3
        System.out.println(countWays(77));  // 22
        System.out.println(countWays(9999));  // 440022018293

    }


    public static long countWays(int amount) {
        List<Integer> cubicCoins = new ArrayList<>();
        int i = 1;
        int cube = 1;
        while (cube <= 9261) {
            cubicCoins.add(cube);
            i++;
            cube = (int)Math.pow(i,3);
        }

        // key - startIndex, value - map of remaining amount; key - num of ways
        Map<Integer, Map<Integer, Long>> memo = new HashMap<>();
        for (int j = 0; j <= cubicCoins.size(); j++) {
            memo.put(j, new HashMap<>());
        }

        return countWaysHelper(amount, cubicCoins, 0, memo);
    }

    public static long countWaysHelper(int amount, List<Integer> cubicCoins, int startIndex, Map<Integer, Map<Integer, Long>> memo) {
        if (startIndex == cubicCoins.size()) {
            return 0;
        }

        if (cubicCoins.get(startIndex) > amount) {
            memo.get(startIndex).put(amount, (long)0);
            return 0;
        }

        if (cubicCoins.get(startIndex) == amount) {
            memo.get(startIndex).put(amount, (long)1);
            return 1;
        }

        if (memo.get(startIndex).containsKey(amount)) {
            return memo.get(startIndex).get(amount);
        }

        int multiple = amount / cubicCoins.get(startIndex);
        long totalWays = (amount % cubicCoins.get(startIndex) == 0) ? 1 : 0;

        for (int i = 0; i <= multiple; i++) {
            int remainingAmount = amount - i * cubicCoins.get(startIndex);
            if (memo.get(startIndex+1).containsKey(remainingAmount)) {
                totalWays += memo.get(startIndex+1).get(remainingAmount);
            } else {
                long nextTotalWays = countWaysHelper(remainingAmount, cubicCoins, startIndex+1, memo);
                memo.get(startIndex+1).put(remainingAmount, nextTotalWays);
                totalWays += nextTotalWays;
            }
        }

        memo.get(startIndex).put(amount, totalWays);
        return totalWays;
    }


}
