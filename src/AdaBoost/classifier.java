package AdaBoost;

public class classifier {

	public double threshold;
	public boolean direction;  // if false then classifier x < threshold as -1
	public int dimension;
	public double alpha;
	public double error;
	
	public classifier(){
		
	}
	
	public classifier(double threshold, boolean direction, int dimension){
		this.threshold = threshold;
		this.direction = direction;
		this.dimension = dimension;
	}
	

}
