package application;

public class CubicModelPlot extends ModelPlot {
	
	private static double DEFAULT_A0 = 0;
	private static double DEFAULT_A1 = 1;
	private static double DEFAULT_A2 = 1;
	private static double DEFAULT_A3 = 1;
	
	double a0;
	double a1;
	double a2;
	double a3;
	
	CubicModelPlot(){
		this( DEFAULT_A0, DEFAULT_A1, DEFAULT_A2, DEFAULT_A3, DEFAULT_SIZE, DEFAULT_RANGE_XMINIMUM, DEFAULT_RANGE_XMAXIMUM,
				DEFAULT_RANGE_YMINIMUM, DEFAULT_RANGE_YMAXIMUM );
	}
	
	CubicModelPlot( double a0, double a1, double a2, double a3, int plotSize, 
			double xmin, double xmax, double ymin, double ymax ) {
		super( plotSize, xmin, xmax, ymin, ymax );
		this.a0 = a0;
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
	}
	
	
	@Override
	public DoublePoint[] generateRandomPlot() {
		Double Xrange = range_Xmax-range_Xmin;
		int loopTime = getSize(); 
		for(int i = 0; i < loopTime; i++){
			double nextX = random.nextDouble()*Xrange+range_Xmin;
			double nextY = a0 + a1*nextX + a2*Math.pow(nextX, 2) + a3*Math.pow(nextX, 3);
			DoublePoints[i] = SingleRandomPlot(nextX, nextY);
		}
		return DoublePoints;
	}

}
