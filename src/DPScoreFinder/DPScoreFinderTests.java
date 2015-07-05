package DPScoreFinder;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Thanakorn on 6/3/15.
 */

public class DPScoreFinderTests {

    private static double delta = 0.001;

    private static void eval(double[][] scores) {
        double result1 = DPScoreFinder.findOptimalScore(scores);
        double result2 = DPScoreFinderNew.findOptimalScore(scores);
        assertEquals(result1, result2, delta);
    }

    @Test
    public void MatrixTests() {

        double[][] matrix = new double[][] { {1,2,3} , {4,5,6} , {7,8,9} };
        eval(matrix);

        matrix = new double[][] { {0.2,0.4,0.6,0.3} , {0.1,0.4,0.2,0.8} , {0.5,0.2,0.5,0.1} };
        eval(matrix);

        matrix = new double[][] { {2,4,1,6,7,6,4} , {2,1,5,6,3,5,7} , {33,12,51,32,63,12,63} , {31,35,33,15,76,45,23} };
        eval(matrix);
    }

    @Test
    public void RandomMatrixTests() {
        double[][] matrix = MatrixUtils.generateRandomIntMatrix(3, 3);
        MatrixUtils.printMatrix(matrix);
        eval(matrix);
        System.out.println();
    }
}
