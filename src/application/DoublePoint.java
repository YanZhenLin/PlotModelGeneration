package application;

import java.text.DecimalFormat;

public class DoublePoint extends Point<Double>{
	
	public DoublePoint(String coupleString){
		super(new Double(0), new Double(0));
		Double x = 0.0;
		Double y = 0.0;
		
		String[] split = coupleString.split(",");
		x = Double.parseDouble( split[0].replace("(", "") );
		y = Double.parseDouble( split[1].replace(")", "") );
		setX(x);
		setY(y);
	}
	
	public DoublePoint(Double x, Double y) {
		super(x, y);
	}

	@Override
	public int compareTo(Point o) {
		int xcomp = ((Double)this.x).compareTo((Double)o.getX());
		if( xcomp > 0 ){
			return 1;
		}else if(xcomp == 1){ //if the x coordinates are the same
			int ycomp = ((Double)this.y).compareTo((Double)o.getY());
			if(ycomp > 0){
				return 1;
			}else if(ycomp == 0){ //if the y coordinates are the same well
				return 0;
			}else{
				return -1;
			}
		}else{
			return -1;
		}
	}
	
	
	@Override
	public String toString(){
		return "("+round(this.x)+","+round(this.y)+")";
	}
	
	private double round(double original){
		return Double.parseDouble(new DecimalFormat("#.#").format(original));
	}
}
