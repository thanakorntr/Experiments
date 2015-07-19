package AdaBoost;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Thanakorn on 7/18/15.
 */
public class AdaBoostMAIN {

    private static final String trainingFeaturesFilePath = "/Users/Thanakorn/IdeaProjects/Experiments/src/AdaBoost/data/instance_matrix_train.txt";
    private static final String trainingLabelsFilePath = "/Users/Thanakorn/IdeaProjects/Experiments/src/AdaBoost/data/label_train.txt";
    private static final String testFeaturesFilePath = "/Users/Thanakorn/IdeaProjects/Experiments/src/AdaBoost/data/instance_matrix_test.txt";
    private static final String testLabelsFilePath = "/Users/Thanakorn/IdeaProjects/Experiments/src/AdaBoost/data/label_test.txt";


    private static int featDimension = 60;
    private static int numTrainingData = 2175;
    private static int numTestData = 1000;

    private static double[][] trainingFeatures = new double[numTrainingData][featDimension];
    private static double[] trainingLabels = new double[numTrainingData];
    private static double[][] testFeatures = new double[numTestData][featDimension];
    private static double[] testLabels = new double[numTestData];

    private static int numIterations = 55;

    public static void main(String[] args) {

        loadFeatures();
        loadLabels();

        AdaBoost adaBoost = new AdaBoost();
        adaBoost.train(trainingFeatures, trainingLabels, numIterations);
        adaBoost.displayAccuracy(trainingFeatures, trainingLabels);
        adaBoost.displayAccuracy(testFeatures, testLabels);

        runXORProblem();
    }

    public static void runXORProblem() {
        double[] x1 = new double[] {0,0};
        double[] x2 = new double[] {0,1};
        double[] x3 = new double[] {1,1};
        double[] x4 = new double[] {1,0};
        double[] labels = new double[] {0, 1, 0, 1};
        double[][] features = new double[4][2];
        features[0] = x1;
        features[1] = x2;
        features[2] = x3;
        features[3] = x4;

        AdaBoost adaBoost = new AdaBoost();
        adaBoost.train(features, labels, 10);
        adaBoost.displayAccuracy(features, labels);
    }

    public static void loadFeatures() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(trainingFeaturesFilePath));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] featVals = line.split("\t");
                int j = 0;
                for (String featVal : featVals) {
                    trainingFeatures[i][j] = Double.parseDouble(featVal);
                    j++;
                }
                i++;
            }

            br = new BufferedReader(new FileReader(testFeaturesFilePath));
            i = 0;
            while ((line = br.readLine()) != null) {
                String[] featVals = line.split("\t");
                int j = 0;
                for (String featVal : featVals) {
                    testFeatures[i][j] = Double.parseDouble(featVal);
                    j++;
                }
                i++;
            }
        } catch (Exception e) {

        }
    }

    public static void loadLabels() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(trainingLabelsFilePath));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                double label = Double.parseDouble(line);
                trainingLabels[i] = label;
                i++;
            }

            br = new BufferedReader(new FileReader(testLabelsFilePath));
            i = 0;
            while ((line = br.readLine()) != null) {
                double label = Double.parseDouble(line);
                testLabels[i] = label;
                i++;
            }
        } catch (Exception e) {

        }
    }
}
