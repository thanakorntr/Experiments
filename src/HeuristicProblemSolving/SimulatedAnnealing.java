package HeuristicProblemSolving;

import java.util.Arrays;

/**
 * Created by Thanakorn on 9/20/15.
 */
public class SimulatedAnnealing {

    private static int N = 2;

    private static int[] optimalSolFound;
    private static double minScoreFound;

    public static void main(String[] args) {

        long t0 = System.currentTimeMillis();

        designDenominationsSimulatedAnnealing(N);

        long t1 = System.currentTimeMillis();

        System.out.println("N: " + N);
        System.out.println("Best denominations found: " + Arrays.toString(optimalSolFound));
        System.out.println("Score: " + minScoreFound);
        System.out.println("Time taken: " + (t1-t0)*Math.pow(10, -3) + " secs.");
    }

    public static void designDenominationsSimulatedAnnealing(int N) {

        int[] originalInitialSolution = getInitialSolution(N);
        int[] curSolution = Arrays.copyOf(originalInitialSolution, originalInitialSolution.length);

        double maxT = MintProblem.determineScore(N, curSolution);
        double T = maxT;
        double boltzmannConstant = 1.38 * Math.pow(10, -23);
        while (T > 0.001) {
            double diff = maxT - T;
            for (int i = 1; i <= diff; i++) {
                int[] neighbor = getNeighbor(curSolution, originalInitialSolution);
                double neighborScore = MintProblem.determineScore(N, neighbor);
                double diffScore = neighborScore - minScoreFound;
                if (diffScore < 0 || Math.random() < Math.exp(-diffScore / (boltzmannConstant * T))) {
                    curSolution = neighbor;
                    minScoreFound = neighborScore;
                    optimalSolFound = neighbor;
                    System.out.println("N: " + N);
                    System.out.println("Best denominations found: " + Arrays.toString(curSolution));
                    System.out.println("Score: " + minScoreFound);
                }
            }
            T *= 0.999;
        }
    }

    private static int[] getNeighbor(int[] denominations, int[] originalInitialSolution) {
        int k = MintProblem.maxPrice / 10;
        int[] neighbor = Arrays.copyOf(denominations, denominations.length);
        for (int i = 1; i < denominations.length; i++) {
            neighbor[i] = originalInitialSolution[i] + (int)(Math.random() * k);
        }
        return neighbor;
    }

    private static int[] getInitialSolution(int N) {

        int[] initialSol = new int[] {1, 8, 13, 33, 51, 65, 70};  // 239
        initialSol = new int[] {1, 3, 10, 25, 55, 71, 75};
        //int[] initialSol = new int[] {1,4,5,8,10,25,50};

        return initialSol;
    }
}
