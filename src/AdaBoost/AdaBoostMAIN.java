package AdaBoost;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Thanakorn on 7/18/15.
 */
public class AdaBoostMAIN {

    private static final String trainingMatrixFilePath = "/Users/Thanakorn/IdeaProjects/Experiments/src/AdaBoost/data/instance_matrix_train.txt";
    private static final String trainingLabelsFilePath = "/Users/Thanakorn/IdeaProjects/Experiments/src/AdaBoost/data/label_train.txt";

    private static int featDimension = 60;
    private static int numData = 2175;

    private static double[][] features = new double[numData][featDimension];
    private static double[] labels = new double[numData];

    private static int numIterations = 200;

    public static void main(String[] args) {

        loadFeatureMatrix();
        loadLabels();

        AdaBoost adaBoost = new AdaBoost();
        adaBoost.train(features, labels, numIterations);
        adaBoost.displayAccuracy(features, labels);

    }

    public static void loadFeatureMatrix() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(trainingMatrixFilePath));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] featVals = line.split("\t");
                int j = 0;
                for (String featVal : featVals) {
                    features[i][j] = Double.parseDouble(featVal);
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
            int pos = 0;
            int neg = 0;
            while ((line = br.readLine()) != null) {
                double label = Double.parseDouble(line);
                if (label == 1) pos++;
                if (label == -1) neg++;
                labels[i] = label;
                i++;
            }
            System.out.println("Num positive label: " + pos);
            System.out.println("Num negative label: " + neg);
        } catch (Exception e) {

        }
    }
}
