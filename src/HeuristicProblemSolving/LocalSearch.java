package HeuristicProblemSolving;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thanakorn on 9/20/15.
 */
public class LocalSearch {

    private static int N = 2;

    public static void main(String[] args) {

        long t0 = System.currentTimeMillis();

        Pair<int[], Double> sol = designDenominationsLocalSearch(N);

        System.out.println("N: " + N);
        System.out.println("Best denominations found: " + Arrays.toString(sol.fst));
        System.out.println("Score: " + sol.snd);

        long t1 = System.currentTimeMillis();

        System.out.println("Time taken: " + (t1-t0)*Math.pow(10, -3) + " secs.");
    }

    public static Pair<int[], Double> designDenominationsLocalSearch(int N) {

        int[] originalInitialSolution = getInitialSolution(N);
        int[] curSolution = Arrays.copyOf(originalInitialSolution, originalInitialSolution.length);
        double curMinScore = MintProblem.determineScore(N, curSolution);

        boolean hasBetterSolution = true;
        while (hasBetterSolution) {
            hasBetterSolution = false;
            List<int[]> neighbors = getNeighbors(curSolution, originalInitialSolution);
            for (int[] neighbor : neighbors) {
                double neighborScore = MintProblem.determineScore(N, neighbor);
                if (neighborScore < curMinScore) {
                    curMinScore = neighborScore;
                    curSolution = neighbor;
                    hasBetterSolution = true;
                    break;
                }
            }
        }

        return new Pair<>(curSolution, curMinScore);
    }

    private static List<int[]> getNeighbors(int[] denominations, int[] originalInitialSolution) {
        List<int[]> neighbors = new ArrayList<>();
        int numNeighbors = 700000;
        int k = MintProblem.maxPrice / 10;
        for (int c = 0; c < numNeighbors; c++) {
            int[] neighbor = Arrays.copyOf(denominations, denominations.length);
            for (int i = 1; i < denominations.length; i++) {
                neighbor[i] = originalInitialSolution[i] + (int)(Math.random() * k);
            }
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    private static int[] getInitialSolution(int N) {
//        int[] initialSol = new int[MintProblem.numDenominations];
//        int maxPrice = MintProblem.maxPrice;

        int[] initialSol = new int[] {1, 8, 13, 33, 51, 65, 70};  // 239
        //initialSol = new int[] {1, 3, 10, 25, 55, 71, 75};
        //int[] initialSol = new int[] {1,4,5,8,10,25,50};

        return initialSol;
    }
}
