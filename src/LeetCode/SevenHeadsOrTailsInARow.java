package LeetCode;

/**
 * Created by Thanakorn on 7/27/15.
 */
public class SevenHeadsOrTailsInARow {

    public static void main(String[] args) {
        while (true){
            monteCarlo(100, 7, Double.parseDouble(args[0]));
        }
    }

    public static void monteCarlo(int n, int k, double T) {

        double numWin = 0;

        for (int t = 1; t <= T; t++) {
            int numConsecutive = 0;
            int lastToss = -1;
            for (int i = 0; i < n; i++) {
                int toss = Math.random() > 0.5 ? 1 : 0;
                if (i == 0) {
                    lastToss = toss;
                    numConsecutive = 1;
                } else {
                    if (toss == lastToss) {
                        numConsecutive++;
                        if (numConsecutive == k) {
                            numWin++;
                            break;
                        }
                    } else {
                        lastToss = toss;
                        numConsecutive = 1;
                    }
                }
            }
        }

        double pWinning = numWin / T;

        System.out.println(pWinning);
    }

    public static double getWinningProbability(int n, int k) {


        return 0;
    }
}
