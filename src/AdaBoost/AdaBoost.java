package AdaBoost;

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

    }

    public void train(double[][] features, double[] labels, int numIterations) {

        for (int i = 1; i <= numIterations; i++) {

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
}
