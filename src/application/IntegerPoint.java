package application;

public class IntegerPoint extends Point<Integer>{
	
	public IntegerPoint(Integer x, Integer y) {
		super(x, y);
	}

	@Override
	public int compareTo(Point o) {
		int xcomp = ((Integer)this.x).compareTo((Integer)o.getX());
		if( xcomp > 0 ){
			return 1;
		}else if(xcomp == 1){ //if the x coordinates are the same
			int ycomp = ((Integer)this.y).compareTo((Integer)o.getY());
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
}
