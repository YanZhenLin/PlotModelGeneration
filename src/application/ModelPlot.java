package application;

import java.util.Random;

abstract class ModelPlot {
	protected final static int DEFAULT_SIZE = 100; 
	protected final static double DEFAULT_RANGE_XMINIMUM = -100.0;
	protected final static double DEFAULT_RANGE_XMAXIMUM = 100.0;
	protected final static double DEFAULT_RANGE_YMINIMUM = -100.0;
	protected final static double DEFAULT_RANGE_YMAXIMUM = 100.0;
	
	protected int plotSize = 0;
	protected double range_Xmin = -100.0;
	protected double range_Xmax = 100;
	protected double range_Ymin = -100.0;
	protected double range_Ymax = 100.0;
	protected Random random;
	
	DoublePoint[] DoublePoints;
	
	public ModelPlot(){
		this(DEFAULT_SIZE, DEFAULT_RANGE_XMINIMUM, DEFAULT_RANGE_XMAXIMUM,
				DEFAULT_RANGE_YMINIMUM, DEFAULT_RANGE_YMAXIMUM );
	}

	public ModelPlot(int size, double xmin, double xmax, double ymin, double ymax ){
		random = new Random();
		plotSize = size;
		//if the users choose some bad values for min and max values there's little we can do
		range_Xmin = xmin;
		range_Xmax = xmax;
		range_Ymin = ymin;
		range_Ymax = ymax;
		
		DoublePoints = new DoublePoint[plotSize];
	}
	
	//given a point coordinate, generate a point near the neighbor of the real coordinate
	protected DoublePoint SingleRandomPlot( double realX, double realY) throws RuntimeException{
		if(realX > range_Xmax || realX < range_Xmin )
			throw new RuntimeException("X coordinate out of range");
		
		double unit = realY*.10; // maximum 1/10 Y deviation from real Y value
		double ymin = realY - unit;
		double ymax = realY + unit;
		
		if(ymin < DEFAULT_RANGE_YMINIMUM)
			ymin = DEFAULT_RANGE_YMINIMUM;
		
		if(ymax > DEFAULT_RANGE_YMAXIMUM)
			ymax = DEFAULT_RANGE_YMAXIMUM;
		
		double totalSpread = ymax-ymin;
		
		double nextRandomY = ( random.nextDouble()%totalSpread )+ymin;
		return new DoublePoint(realX, nextRandomY);
	}
	
	public abstract DoublePoint[] generateRandomPlot(); //will be implemented by the concrete subclass which extends the ModelPlot
	
	public DoublePoint[] getPlot(){
		return DoublePoints;
	}
	
	public int getSize(){
		return plotSize;
	}
	
}
