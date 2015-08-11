package AdaBoost.FMLassignment;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class A_rho {

	public static File instance_matrix_train_file;
	public static File label_train_file;
	public static File instance_matrix_validation_file;
	public static File label_validation_file;
	public static File instance_matrix_test_file = new File("C:/Users/Thanakorn/Desktop/data/instance_matrix_test.txt");
	public static File label_test_file = new File("C:/Users/Thanakorn/Desktop/data/label_test.txt");
	
	
	public static double[] label_train;  // 1953
	public static double[][] instance_matrix_train;  // 1953x60  
	public static double[] label_test;  // 1000
	public static double[][] instance_matrix_test;  // 1000x60
	public static double[] label_validation;  // 217
	public static double[][] instance_matrix_validation;  // 217x60
	
	public static double[][] sorted_features;  // 60x2175
	public static int m = 1953;  // number of training data
	public static int m_test = 1000;  // number of testing data
	public static int m_validation = 217;  // number of validation data
	public static int N = 60;  // number of features
	public static double[] D = new double[m];  // distribution matrix
	
		
	
	
	public static void main(String[] args) throws IOException{
			
		loadTestData();
		
		int[] T_values = new int[]{100,200,500,1000};
		double[] rho_values = new double[]{Math.pow(2, -10),Math.pow(2, -9),
										   Math.pow(2, -8),Math.pow(2, -7),
										   Math.pow(2, -6),Math.pow(2, -5),
										   Math.pow(2, -4),Math.pow(2, -3),
										   Math.pow(2, -2),Math.pow(2, -1)};
		
		int[] cv_sets = new int[]{1,2,3,4,5,6,7,8,9,10};
		String path = "C:/Users/Thanakorn/Desktop/data/set";
		
		//////////// For AdaBoost algorithm ////////////////////
		///////////////////////////////////////////////////////
		PrintWriter adaboost_test_writer = new PrintWriter("C:/Users/Thanakorn/Desktop/data/AdaBoost_test_results.txt");
		PrintWriter adaboost_cv_writer = new PrintWriter("C:/Users/Thanakorn/Desktop/data/AdaBoost_cv_results.txt");
		double smallest_CV_error = Double.MAX_VALUE;
		int best_T = 0;
		
		for(int i=0;i<T_values.length;i++){
			double[] test_errors = new double[10];
			double[] cv_errors = new double[10];
			for(int k=0;k<cv_sets.length;k++){
				instance_matrix_train_file = new File(path+cv_sets[k]+"/instance_matrix_train.txt");
				label_train_file = new File(path+cv_sets[k]+"/label_train.txt");
				instance_matrix_validation_file = new File(path+cv_sets[k]+"/instance_matrix_test.txt");
				label_validation_file = new File(path+cv_sets[k]+"/label_test.txt");
				loadData();
				loadValidationData();
				sortFeatures();
				classifier[] H_t = performBoost(T_values[i], 0);
				double test_error = calculateTestError(H_t,0);
				test_errors[k] = test_error;
				double validation_error = calculateValidationError(H_t,0);
				cv_errors[k] = validation_error;
				System.out.println("Validation error = "+validation_error);
				System.out.println("LeetCode.Test error = "+test_error);
				
			}
			double avg_test_error = getMean(test_errors);
			double std_test_error = getStd(test_errors);
			double avg_cv_error = getMean(cv_errors);
			double std_cv_error = getStd(cv_errors);
			if(avg_cv_error<smallest_CV_error){
				smallest_CV_error = avg_cv_error;
				best_T = T_values[i];
			}
			System.out.println("Average cv error for T = "+T_values[i]+" is "+avg_cv_error+" with std = "+std_cv_error);
			System.out.println("Average test error for T = "+T_values[i]+" is "+avg_test_error+" with std = "+std_test_error);
			adaboost_cv_writer.println("Average cv error for T = "+T_values[i]+" is "+avg_cv_error+" with std = "+std_cv_error);
			adaboost_test_writer.println("Average test error for T = "+T_values[i]+" is "+avg_test_error+" with std = "+std_test_error);
							
		}
		adaboost_cv_writer.close();
		adaboost_test_writer.close();
		System.out.println("Best T = "+best_T);
		
		//////////////// For A_rho algorithm //////////////////
		///////////////////////////////////////////////////////
		
		double best_rho = 0;
		best_T = 0;
		smallest_CV_error = Double.MAX_VALUE;
		
		PrintWriter test_writer = new PrintWriter("C:/Users/Thanakorn/Desktop/data/A_rho_test_results.txt");
		PrintWriter cv_writer = new PrintWriter("C:/Users/Thanakorn/Desktop/data/A_rho_cv_results.txt");
		
		for(int i=0;i<T_values.length;i++){
			for(int j=0;j<rho_values.length;j++){
				double[] test_errors = new double[10];
				double[] cv_errors = new double[10];
				for(int k=0;k<cv_sets.length;k++){
					instance_matrix_train_file = new File(path+cv_sets[k]+"/instance_matrix_train.txt");
					label_train_file = new File(path+cv_sets[k]+"/label_train.txt");
					instance_matrix_validation_file = new File(path+cv_sets[k]+"/instance_matrix_test.txt");
					label_validation_file = new File(path+cv_sets[k]+"/label_test.txt");
					loadData();
					loadValidationData();
					sortFeatures();
					classifier[] H_t = performBoost(T_values[i], rho_values[j]);
					double test_error = calculateTestError(H_t,rho_values[j]);
					test_errors[k] = test_error;
					double validation_error = calculateValidationError(H_t,rho_values[j]);
					cv_errors[k] = validation_error;
					System.out.println("Validation error = "+validation_error);
					System.out.println("LeetCode.Test error = "+test_error);
				}
				double avg_test_error = getMean(test_errors);
				double std_test_error = getStd(test_errors);
				double avg_cv_error = getMean(cv_errors);
				double std_cv_error = getStd(cv_errors);
				if(avg_cv_error<smallest_CV_error){
					smallest_CV_error = avg_cv_error;
					best_rho = rho_values[j];
					best_T = T_values[i];
				}
				System.out.println("Average cv error for T = "+T_values[i]+" and rho = "+rho_values[j]+" is "+avg_cv_error+" with std = "+std_cv_error);
				System.out.println("Average test error for T = "+T_values[i]+" and rho = "+rho_values[j]+" is "+avg_test_error+" with std = "+std_test_error);
				cv_writer.println("Average cv error for T = "+T_values[i]+" and rho = "+rho_values[j]+" is "+avg_cv_error+" with std = "+std_cv_error);
				test_writer.println("Average test error for T = "+T_values[i]+" and rho = "+rho_values[j]+" is "+avg_test_error+" with std = "+std_test_error);
				
			}
		}
		cv_writer.close();
		test_writer.close();
		
		System.out.println("Best rho = "+best_rho);
		System.out.println("Best T = "+best_T);
		//////////////////////// end of problem A.6 ///////////////////////
		///////////////////////////////////////////////////////////////////
		
		//////////////////////// start of problem A.7 //////////////////
		instance_matrix_train_file = new File("C:/Users/Thanakorn/Desktop/data/instance_matrix_train.txt");
		label_train_file = new File("C:/Users/Thanakorn/Desktop/data/label_train.txt");
		loadOriginalData();
		
		double[] A_rho_fraction = new double[10];
		for(int j=0;j<rho_values.length;j++){
			classifier[] H_t = performBoost(500, rho_values[j]);
			double fraction = 0;
			for(int i=0;i<2175;i++){
				if(applyH_t(H_t, instance_matrix_train[i])*label_train[i]<=rho_values[j]){
					fraction++;
				}
			}
			fraction /= 2175;
			A_rho_fraction[j] = fraction;
		}
		PrintWriter A_rho_fraction_writer = new PrintWriter("C:/Users/Thanakorn/Desktop/data/A_rho_fraction.txt");
		for(int i=0;i<A_rho_fraction.length;i++){
			A_rho_fraction_writer.println("Theta = "+rho_values[i]+", fraction of training points with margin less than or equal to theta is "+A_rho_fraction[i]);
		}
		A_rho_fraction_writer.close();
		
		double[] AdaBoost_fraction = new double[10];
		for(int j=0;j<rho_values.length;j++){
			classifier[] H_t = performBoost(500, 0);
			double fraction = 0;
			for(int i=0;i<2175;i++){
				if(applyH_t(H_t, instance_matrix_train[i])*label_train[i]<=rho_values[j]){
					fraction++;
				}
			}
			fraction /= 2175;
			AdaBoost_fraction[j] = fraction;
		}
		PrintWriter AdaBoost_fraction_writer = new PrintWriter("C:/Users/Thanakorn/Desktop/data/AdaBoost_fraction.txt");
		for(int i=0;i<AdaBoost_fraction.length;i++){
			AdaBoost_fraction_writer.println("Theta = "+rho_values[i]+", fraction of training points with margin less than or equal to theta is "+AdaBoost_fraction[i]);
		}
		AdaBoost_fraction_writer.close();
		
	} 
	
	public static classifier[] performBoost(int T, double rho){
		classifier[] H_t = new classifier[T];
		// initializing a set of weak classifiers
		for(int i=0;i<T;i++){
			H_t[i] = new classifier();
		}
		// setting the initial distribution
		for(int i=0;i<m;i++){
			D[i] = (double)1/m;
		}
		for(int t=0;t<T;t++){
			//System.out.println("In round: "+(t+1));
			classifier h_t = getBaseClassifier(rho);  
			double epsilon_t = h_t.error;
			//System.out.println("epsilon_t = "+epsilon_t);
			if(epsilon_t>=(1-rho)/2){
				break;
			}
			double alpha_t = 0.5*Math.log((1-epsilon_t)*(1-rho)/(epsilon_t*(1+rho)));
			double u = (1-rho)/(1+rho);
			double Z_t = (Math.pow(u, (1+rho)/2)+Math.pow(u, (rho-1)/2))*Math.sqrt(Math.pow(epsilon_t, 1-rho)*Math.pow(1-epsilon_t, 1+rho));
			h_t.alpha = alpha_t;
			H_t[t] = h_t;
			//System.out.println("alpha_t = "+alpha_t);
			//System.out.println("Z_t = "+Z_t);
			
			// updating the distribution
			classifier[] temp_H_t = new classifier[1];
			temp_H_t[0] = h_t;
			for(int i=0;i<m;i++){
				if(applyH_t(temp_H_t, instance_matrix_train[i])*label_train[i]<=rho){
					D[i] = D[i]*Math.exp(alpha_t*(rho+1))/Z_t;
				}
				else{
					D[i] = D[i]*Math.exp(alpha_t*(rho-1))/Z_t;
				}
			}
			
			// printing the sum of D[i]
//			double sum = 0;
//			for(int i=0;i<m;i++){
//				sum += D[i];
//			}
//			System.out.println("Sum of D[i] = "+sum);
		}
		// end of boosting
		
		return H_t;
	}
	
	public static double calculateValidationError(classifier[] H_t, double rho){
		double error = 0;
		for(int i=0;i<m_validation;i++){
			if(applyH_t(H_t, instance_matrix_validation[i])*label_validation[i]<=rho){
				error++;
			}
		}
		return error/m_validation;
	}
	
	public static double calculateTestError(classifier[] H_t, double rho){
		double error = 0;
		for(int i=0;i<m_test;i++){
			if(applyH_t(H_t, instance_matrix_test[i])*label_test[i]<=rho){
				error++;
			}
		}
		return error/m_test;
	}
	
	public static double calculateEmpiricalError(classifier[] H_t, double rho){
		double error = 0;
		for(int i=0;i<m;i++){
			if(applyH_t(H_t, instance_matrix_train[i])*label_train[i]<=rho){
				error++;
			}
		}
		return error/m;
	}
	
	// return the sum of h_t and a given point
	public static double applyH_t(classifier[] H_t, double[] point_features){
		int T = H_t.length;
		double sum = 0;
		for(int i=0;i<T;i++){
			classifier h_t = H_t[i];
			double alpha_t = h_t.alpha;
			int dimension = h_t.dimension;
			double threshold = h_t.threshold;
			if(h_t.direction==false){  // if the current weak classifier classifies value < threshold as -1
				if(point_features[dimension]<threshold){  
					sum -= alpha_t;
				}
				else{
					sum += alpha_t;
				}
			}
			else{  // if the current weak classifier classifies value < threshold as +1
				if(point_features[dimension]<threshold){  
					sum += alpha_t;
				}
				else{
					sum -= alpha_t;
				}
			}
		}
		
		return sum;
	}
	
	public static classifier getBaseClassifier(double rho){
		classifier best_stump = new classifier();
		double smallest_error = Double.MAX_VALUE;
		for(int i=0;i<N;i++){
			for(int j=1;j<m;j++){
				if(sorted_features[i][j]!=sorted_features[i][j-1]){  // no threshold if identical coordinates
					double threshold = (sorted_features[i][j]+sorted_features[i][j-1])/2;
					classifier current_stump1 = new classifier(threshold,false,i);  // classify point with value < threshold as -1
					double stump1_error = 0;
					for(int k=0;k<m;k++){
						if(instance_matrix_train[k][i]<threshold){  // classified as -1
							if(label_train[k]==1){
								stump1_error += D[k];
							}
							else{
								double margin = Math.abs(threshold-instance_matrix_train[k][i]);
								if(margin<=rho){
									stump1_error += D[k];
								}
							}
						}
						else{  // classified as +1
							if(label_train[k]==-1){
								stump1_error += D[k];
							}
							else{
								double margin = Math.abs(threshold-instance_matrix_train[k][i]);
								if(margin<=rho){
									stump1_error += D[k];
								}
							}
						}
					}
					current_stump1.error = stump1_error;
					if(stump1_error<smallest_error){
						smallest_error = stump1_error;
						best_stump = current_stump1;
					}
					
					classifier current_stump2 = new classifier(threshold,true,i);  // classify point with value < threshold as +1
					double stump2_error = 0;
					for(int k=0;k<m;k++){
						if(instance_matrix_train[k][i]<threshold){  // classified as +1
							if(label_train[k]==-1){
								stump2_error += D[k];
							}
							else{
								double margin = Math.abs(threshold-instance_matrix_train[k][i]);
								if(margin<=rho){
									stump2_error += D[k];
								}
							}
						}
						else{  // classified as -1
							if(label_train[k]==1){
								stump2_error += D[k];
							}
							else{
								double margin = Math.abs(threshold-instance_matrix_train[k][i]);
								if(margin<=rho){
									stump2_error += D[k];
								}
							}
						}
					}
					current_stump2.error = stump2_error;
					if(stump2_error<smallest_error){
						smallest_error = stump2_error;
						best_stump = current_stump2;
					}
				}
			}
		}
		return best_stump;
	}
	

	
	public static void sortFeatures(){
		sorted_features = transposeMatrix(instance_matrix_train);  // 60x2175
		for(int row=0;row<sorted_features.length;row++){
			Arrays.sort(sorted_features[row]);
		}
	}
	
	
	
	// load data into label_train and instance_matrix train for CV
	public static void loadData() throws IOException{
		Scanner scanner_label = new Scanner(label_train_file);
		label_train = new double[m];
		instance_matrix_train = new double[m][N];
		
		int i = 0;
		while(scanner_label.hasNext()){
			double label = Double.parseDouble(scanner_label.next());
			label_train[i++] = label;
		}
		
		int row = 0;
		int col = 0;
		BufferedReader reader = new BufferedReader(new FileReader(instance_matrix_train_file));
		String current_line;
		while((current_line=reader.readLine())!=null){
			Scanner scanner = new Scanner(current_line);
			while(scanner.hasNext()){
				double val = Double.parseDouble(scanner.next());
				instance_matrix_train[row][col++] = val;
				if(col>59){
					col = 0;
					row++;
				}
			}
		}
	
	}
	
	// load the original (unspliited) data into label_train and instance_matrix train
	public static void loadOriginalData() throws IOException{
		Scanner scanner_label = new Scanner(label_train_file);
		label_train = new double[2175];
		instance_matrix_train = new double[2175][N];
		
		int i = 0;
		while(scanner_label.hasNext()){
			double label = Double.parseDouble(scanner_label.next());
			label_train[i++] = label;
		}
		
		int row = 0;
		int col = 0;
		BufferedReader reader = new BufferedReader(new FileReader(instance_matrix_train_file));
		String current_line;
		while((current_line=reader.readLine())!=null){
			Scanner scanner = new Scanner(current_line);
			while(scanner.hasNext()){
				double val = Double.parseDouble(scanner.next());
				instance_matrix_train[row][col++] = val;
				if(col>59){
					col = 0;
					row++;
				}
			}
		}
	
	}
	
	// load test data into label_test and instance_matrix_test
	public static void loadTestData() throws IOException{
		Scanner scanner_label = new Scanner(label_test_file);
		label_test = new double[m_test];
		instance_matrix_test = new double[m_test][N];
		
		int i = 0;
		while(scanner_label.hasNext()){
			double label = Double.parseDouble(scanner_label.next());
			label_test[i++] = label;
		}
		
		int row = 0;
		int col = 0;
		BufferedReader reader = new BufferedReader(new FileReader(instance_matrix_test_file));
		String current_line;
		while((current_line=reader.readLine())!=null){
			Scanner scanner = new Scanner(current_line);
			while(scanner.hasNext()){
				double val = Double.parseDouble(scanner.next());
				instance_matrix_test[row][col++] = val;
				if(col>59){
					col = 0;
					row++;
				}
			}
		}
	
	}
	
	// load validation data into label_validation and instance_matrix_validation
	public static void loadValidationData() throws IOException{
		Scanner scanner_label = new Scanner(label_validation_file);
		label_validation = new double[m_validation];
		instance_matrix_validation = new double[m_validation][N];
		
		int i = 0;
		while(scanner_label.hasNext()){
			double label = Double.parseDouble(scanner_label.next());
			label_validation[i++] = label;
		}
		
		int row = 0;
		int col = 0;
		BufferedReader reader = new BufferedReader(new FileReader(instance_matrix_validation_file));
		String current_line;
		while((current_line=reader.readLine())!=null){
			Scanner scanner = new Scanner(current_line);
			while(scanner.hasNext()){
				double val = Double.parseDouble(scanner.next());
				instance_matrix_validation[row][col++] = val;
				if(col>59){
					col = 0;
					row++;
				}
			}
		}
	
	}
	
	public static double randomDouble(double a, double b){
		Random random = new Random();
		double rand = random.nextDouble();
		return a+(b-a)*rand;
	}
	
	public static double getMean(double[] d){
		double avg = 0;
		for(int i=0;i<d.length;i++){
			avg += d[i];
		}
		return avg/d.length;
	}
	
	public static double getStd(double[] d){
		double mean = getMean(d);
		double std = 0;
		for(int i=0;i<d.length;i++){
			std += Math.pow(d[i]-mean, 2);
		}
		std = std/(d.length-1);
		return Math.sqrt(std);
	}
		
    public static double[][] transposeMatrix(double [][] m){
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m[0].length; j++){
            	temp[j][i] = m[i][j];
            }
        }
        return temp;
    }


	
}
