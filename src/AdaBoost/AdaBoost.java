package AdaBoost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thanakorn on 7/18/15.
 */

class WeakClassifier {
    public double threshold;
    public boolean direction;  // if false then classifier x < threshold as -1
    public int dimension;
    public double error;
    public double alpha;
    public double normalizingFactor;

    public WeakClassifier(){

    }

    public WeakClassifier(double threshold, boolean direction, int dimension){
        this.threshold = threshold;
        this.direction = direction;
        this.dimension = dimension;
    }
}

public class AdaBoost {

    // m training data with n features

    private List<WeakClassifier> weakClassifiers;

    public AdaBoost() {
        weakClassifiers = new ArrayList<>();
    }

    public double predict(double[] features) {
        if (weakClassifiers == null || weakClassifiers.size() == 0) {
            System.out.println("Need training before prediction.");
            return 0;
        }

        double sum = 0;
        for (int t = 0; t < weakClassifiers.size(); t++) {
            WeakClassifier curWeakClassifier = weakClassifiers.get(t);
            double alpha = curWeakClassifier.alpha;
            int dimension = curWeakClassifier.dimension;
            double threshold = curWeakClassifier.threshold;
            if (curWeakClassifier.direction == false) {  // classify feat value < threshold as -1
                 if (features[dimension] < threshold) {  // classify -1
                     sum -= alpha;
                 } else {  // classify +1
                     sum += alpha;
                 }
            } else {  // classify feat value < threshold as +1
                if (features[dimension] < threshold) {  // classify +1
                    sum += alpha;
                } else {  // classify -1
                    sum -= alpha;
                }
            }
        }
        return sum >= 0 ? 1 : -1;
    }

    public void train(double[][] features, double[] labels, int numIterations) {
        double[][] sortedFeatures = transposeMatrix(features);
        for (int featID = 0; featID < sortedFeatures.length; featID++) {
            Arrays.sort(sortedFeatures[featID]);
        }

        double[] distribution = new double[features.length];
        Arrays.fill(distribution, 1D / features.length);

        for (int t = 1; t <= numIterations; t++) {
            WeakClassifier baseClassifier = getBaseClassifier(sortedFeatures, labels, distribution);
            weakClassifiers.add(baseClassifier);

            if (baseClassifier.error > 0.5) {
                break;
            }

            updateDistribution(features, labels, distribution, baseClassifier);

            // printing out sum of distribution
            double dSum = 0;
            for (double d : distribution) {
                dSum += d;
            }
            System.out.println("Distribution sum after round " + t + ": " + dSum);
        }

    }

    private void updateDistribution(double[][] features, double[] labels, double[] distribution, WeakClassifier baseClassifier) {
        double threshold = baseClassifier.threshold;
        double alpha = baseClassifier.alpha;
        double normalizingFactor = baseClassifier.normalizingFactor;
        int dimension = baseClassifier.dimension;


        for (int trainingDatumID = 0; trainingDatumID < features.length; trainingDatumID++) {
            if (baseClassifier.direction == false) {  // classify feat value < threshold as -1
                if (features[trainingDatumID][dimension] < threshold) {  // classify as -1
                    if (labels[trainingDatumID] == -1) {  // correctly classified
                        distribution[trainingDatumID] = distribution[trainingDatumID] * Math.exp(-alpha) / normalizingFactor;
                    } else {  // incorrectly classified
                        distribution[trainingDatumID] = distribution[trainingDatumID] * Math.exp(alpha) / normalizingFactor;
                    }
                } else {  // classify as +1
                    if (labels[trainingDatumID] == 1) {  // correctly classified
                        distribution[trainingDatumID] = distribution[trainingDatumID] * Math.exp(-alpha) / normalizingFactor;
                    } else {  // incorrectly classified
                        distribution[trainingDatumID] = distribution[trainingDatumID] * Math.exp(alpha) / normalizingFactor;
                    }
                }
            } else {  // classify feat value < threshold as +1
                if (features[trainingDatumID][dimension] < threshold) {  // classify as +1
                    if (labels[trainingDatumID] == 1) {  // correctly classified
                        distribution[trainingDatumID] = distribution[trainingDatumID] * Math.exp(-alpha) / normalizingFactor;
                    } else {  // incorrectly classified
                        distribution[trainingDatumID] = distribution[trainingDatumID] * Math.exp(alpha) / normalizingFactor;
                    }
                } else {  // classify as -1
                    if (labels[trainingDatumID] == -1) {  // correctly classified
                        distribution[trainingDatumID] = distribution[trainingDatumID] * Math.exp(-alpha) / normalizingFactor;
                    } else {  // incorrectly classified
                        distribution[trainingDatumID] = distribution[trainingDatumID] * Math.exp(alpha) / normalizingFactor;
                    }
                }
            }
        }
    }

    /**
     *
     * @param sortedFeatures: n (feature dimension) x m (number of training data)
     * @return
     */
    private WeakClassifier getBaseClassifier(double[][] sortedFeatures, double[] labels, double[] distribution) {
        WeakClassifier baseClassifier = null;
        double smallestError = Double.MAX_VALUE;

        for (int n = 0; n < sortedFeatures.length; n++) {
            for (int m = 1; m < sortedFeatures[n].length; m++) {
                if (sortedFeatures[n][m] != sortedFeatures[n][m-1]) {
                    double threshold = (sortedFeatures[n][m] + sortedFeatures[n][m-1]) / 2;

                    WeakClassifier c1 = new WeakClassifier(threshold, false, n);  // classify point with value < threshold as -1
                    double c1Error = 0;
                    for (int trainingDatumID = 0; trainingDatumID < sortedFeatures[n].length; trainingDatumID++) {
                        if (sortedFeatures[n][trainingDatumID] < threshold) { // classify -1
                            if (labels[trainingDatumID] == 1) {
                                c1Error += distribution[trainingDatumID];
                            }
                        } else {  // classify +1
                            if (labels[trainingDatumID] == -1) {
                                c1Error += distribution[trainingDatumID];
                            }
                        }
                    }
                    if (c1Error < smallestError) {
                        baseClassifier = c1;
                        smallestError = c1Error;
                    }

                    WeakClassifier c2 = new WeakClassifier(threshold, true, n);  // classify point with value < threshold as +1
                    double c2Error = 0;
                    for (int trainingDatumID = 0; trainingDatumID < sortedFeatures[n].length; trainingDatumID++) {
                        if (sortedFeatures[n][trainingDatumID] < threshold) { // classify +1
                            if (labels[trainingDatumID] == -1) {
                                c2Error += distribution[trainingDatumID];
                            }
                        } else {  // classify -1
                            if (labels[trainingDatumID] == 1) {
                                c2Error += distribution[trainingDatumID];
                            }
                        }
                    }
                    if (c2Error < smallestError) {
                        baseClassifier = c2;
                        smallestError = c2Error;
                    }
                }
            }
        }

        baseClassifier.error = smallestError;
        baseClassifier.alpha = 0.5 * Math.log((1 - smallestError) / smallestError);
        baseClassifier.normalizingFactor = 2 * Math.sqrt(smallestError * (1 - smallestError));
        return baseClassifier;
    }

    public void displayAccuracy(double[][] features, double[] labels) {
        if (weakClassifiers == null || weakClassifiers.size() == 0) {
            System.out.println("Need training before prediction.");
            return;
        }

        int numData = features.length;
        double numCorrectPrediction = 0D;
        for (int i = 0; i < numData; i++) {
            double predictedLabel = predict(features[i]);
            if (predictedLabel == labels[i]) {
                numCorrectPrediction++;
            }
        }
        double accuracy = numCorrectPrediction * 100 / numData;
        System.out.println(numCorrectPrediction + " / " + numData + " correctly predicted.");
        System.out.println("Accuracy: " + accuracy + " %");
    }

    private double[][] transposeMatrix(double [][] m){
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m[0].length; j++){
                temp[j][i] = m[i][j];
            }
        }
        return temp;
    }
}
