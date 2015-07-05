package DPScoreFinder;

/**
 * Created by Thanakorn on 6/1/15.
 */
public class DPScoreFinder {

    public static void main(String[] args) {
        double[][] matrix = MatrixUtils.generateRandomDoubleMatrix(10, 10);
        matrix = new double[][] { {0.6,0.2,0.3,0.9} , {0.3,0.2,0.1,0.7} , {0.1,0.2,0.8,0.4}};

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

        boolean[] visited = new boolean[scores[0].length];

        return findOptimalScoreHelper(scores, visited, 0);
    }

    public static double findOptimalScoreHelper(final double[][] scores, boolean[] visitedCols, int row) {
        if (row >= scores.length) {
            return 0;
        }

        double maxScore = Double.MIN_VALUE;

        for (int col = 0; col < scores[row].length; col++) {
            if (visitedCols[col] == false) {
                visitedCols[col] = true;
                double score =  scores[row][col] + findOptimalScoreHelper(scores, visitedCols, row+1);
                visitedCols[col] = false;
                maxScore = Math.max(maxScore, score);
            }
        }

        return maxScore;
    }

}
