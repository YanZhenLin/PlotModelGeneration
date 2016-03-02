package application;

public class PolynomialRegressionModel {
	private int degree;
	
	private double[][] M;
	private double[][] Y;
	private double[][] A; //coefficientMatrix 
	
	private double[] x_coordinates;
	private double[] y_coordinates;
	
	private double[] DynamicStorage; //the size of this array is 2*polynomial degree + 1
	//we always take an array and the degree of polynomial we want to work with
	private DoublePoint[] data;
	
	public PolynomialRegressionModel(DoublePoint[] data, int degree){
		this.data = data;
		this.degree = degree;
		//although we are increase our memory size, we will have less logical headaches later on
		x_coordinates = new double[data.length];
		y_coordinates = new double[data.length];
		for(int i = 0; i < data.length; i++ ){
			x_coordinates[i] = data[i].x;
			y_coordinates[i] = data[i].y;
		}
		
		M = new double[degree+1][degree+1];
		Y = new double[degree+1][1];
		
		DynamicStorage = new double[degree*2+1];
		//we need to generate the dynamic storage first
		
		generateDynamicStorage();
		generateM_Matrix();
		generateY_Matrix();
		solve_A();
	}
	
	// XA = Y, in our terminology we have M = X so we do (M^-1)M A = M^-1 Y ===> A = M^-1 Y
	private void solve_A(){
		double[][] m_inverse = DoubleMatrix.inverseMatrix(M);
		A = DoubleMatrix.multiplyMatrix(m_inverse, Y);
	}
	
	public double[][] getA(){
		return A;
	}
	
	private void generateDynamicStorage(){
		for(int i = 0; i < DynamicStorage.length; i++){
			//use the mode
			if( i > 0 )
				DynamicStorage[i] = DoubleModelUtilities.CumulativeSumOfPower(x_coordinates, i);
			else
				DynamicStorage[i] = DoubleModelUtilities.CumulativeSum(x_coordinates); //sum all of the coordinates from x_coordinates
		}
	}
	
	//let's write the code for this
	private void generateM_Matrix(){
		int currentDynamicIndex = 0;
		for(int i = 0; i < M.length; i++ ){
			for( int j = 0; j < M[0].length; j++ ){
				currentDynamicIndex = j + i;
				M[i][j] = DynamicStorage[currentDynamicIndex];
			}
		}
	}
	
	private void generateY_Matrix(){
		for( int i = 0; i < Y.length; i++){ 
			Y[i][0] = DoubleModelUtilities.CumulativeSumOfMethod(x_coordinates, y_coordinates, i);
		}
	}

}

