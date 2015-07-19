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
