package DPScoreFinder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Thanakorn on 6/3/15.
 */
public class DPScoreFinderNew {

    public static void main(String[] args) {
        double[][] matrix = MatrixUtils.generateRandomDoubleMatrix(200, 200);
        //matrix = new double[][] { {0.6,0.2,0.3,0.9} , {0.3,0.2,0.1,0.7} , {0.1,0.2,0.8,0.4}};
        matrix = new double[][] { {1,2,3} , {4,5,6} , {7,8,9}};
        matrix = new double[][] { {4,7,5} , {2,1,3} , {0,2,1}};
        matrix = new double[][] { {3,2,3} , {3,3,3} , {5,1,3}};

        double t0 = System.currentTimeMillis();
        System.out.println(findOptimalScore(matrix));
        double t1 = System.currentTimeMillis();

        System.out.println("Time taken: " + (t1 - t0) * Math.pow(10, -3) + " seconds.");
    }

    public static double findOptimalScore(double[][] scores) {
        if (scores == null) {
            throw new NullPointerException();
        }

        if (scores.length > scores[0].length) {
            scores = MatrixUtils.getTranspose(scores);
        }

        if (scores.length == 1) {
            double maxScore = Double.MIN_VALUE;
            for (int col = 0; col < scores[0].length; col++) {
                maxScore = Math.max(maxScore, scores[0][col]);
            }
            return maxScore;
        }


        // initiating helper data structures
        double[][] maxTable = new double[scores.length][scores[0].length];
        for (int col = 0; col < maxTable[0].length; col++) {
            maxTable[0][col] = scores[0][col];
        }

        List<List<Set<Integer>>> visited = new ArrayList<>();
        List<Set<Integer>> firstRowVisited = new ArrayList<>();
        for (int col = 0; col < scores[0].length; col++) {
            Set<Integer> visitedCols = new HashSet<>();
            visitedCols.add(col);
            firstRowVisited.add(visitedCols);
        }
        visited.add(firstRowVisited);

        // core algorithm here
        for (int row = 1; row < scores.length; row++) {
            List<Set<Integer>> currentRowVisited = new ArrayList<>();
            List<Set<Integer>> previousRowVisited = visited.get(row - 1);

            for (int col = 0; col < scores[row].length; col++) {
                double tempMaxScore = Double.MIN_VALUE;
                int optimalIndex = -1;

                for (int i = 0; i < previousRowVisited.size(); i++) {
                    if (i != col) {
                        Set<Integer> set = previousRowVisited.get(i);
                        if (!set.contains(col)) {
                            double tempScore = scores[row][col] + maxTable[row-1][i];
                            if (tempScore > tempMaxScore) {
                                tempMaxScore = tempScore;
                                optimalIndex = i;
                            }
                        }
                    }
                }

                if (optimalIndex != -1) {
                    maxTable[row][col] = tempMaxScore;
                    Set<Integer> currentSet = new HashSet<>(previousRowVisited.get(optimalIndex));
                    currentSet.add(col);
                    currentRowVisited.add(currentSet);
                } else {
                    maxTable[row][col] = Double.MIN_VALUE;
                }
            }

            visited.add(currentRowVisited);
        }

        double maxScore = Double.MIN_VALUE;
        for (int col = 0; col < scores[0].length; col++) {
            maxScore = Math.max(maxScore, maxTable[maxTable.length-1][col]);
        }

        return maxScore;
    }

}
