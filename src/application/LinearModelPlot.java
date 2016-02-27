package application;

public class LinearModelPlot extends ModelPlot {

	private static double DEFAULT_M = 1;
	private static double DEFAULT_B = 0;
	
	double m;
	double b;
	
	LinearModelPlot(){
		this(DEFAULT_M, DEFAULT_B, DEFAULT_SIZE, DEFAULT_RANGE_XMINIMUM, DEFAULT_RANGE_XMAXIMUM,
				DEFAULT_RANGE_YMINIMUM, DEFAULT_RANGE_YMAXIMUM );
	}
	
	LinearModelPlot(double m, double b, int plotSize, double xmin, double xmax, double ymin, double ymax){
		super( plotSize, xmin, xmax, ymin, ymax );
		this.m = m;
		this.b = b;
	}
	
	@Override
	public DoublePoint[] generateRandomPlot() { 
		//linear model y = mx+b
		Double Xrange = range_Xmax-range_Xmin;
		int loopTime = getSize(); 
		for(int i = 0; i < loopTime; i++){
			double nextX = random.nextDouble()*Xrange+range_Xmin;
			double nextY = m*nextX+b;
			DoublePoints[i] = SingleRandomPlot(nextX, nextY);
		}
		return DoublePoints;
	}

}
