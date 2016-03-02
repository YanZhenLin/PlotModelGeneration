package application;

public class DoubleMatrix extends GenericMatrix<Double> {

	@Override
	protected Double add(Double o1, Double o2) {
		return o1 + o2;
	}

	@Override
	protected Double multiply(Double o1, Double o2) {
		return o1*o2;
	}

	@Override
	protected Double zero() {
		return 0.0;
	}
	
	
	//double specific methods
	//for a determinant computation to work, we will need a square matrix
	public static double matrixDeterminant( double[][] matrix ){
		if(matrix.length != matrix[0].length){
			throw new IllegalArgumentException("Non square matrix as parameter");
		}
		
		int[][] permutationResult = DoubleModelUtilities.PermutationSet(matrix.length);
		return DoubleModelUtilities.LeibnizFormula(permutationResult, matrix);
	}
	
	public static double[][] inverseMatrix(double[][] matrix){
		double[][] result = new double[matrix.length][matrix[0].length];
		double determinant = matrixDeterminant(matrix);
		result = AdjugateMatrix(matrix);
		//we need a matrix scalar product method
		scalarProduct(result,1/determinant);
		return result;
	}
	
	public static void scalarProduct(double[][] matrix, double scalar){
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				matrix[i][j] = matrix[i][j]*scalar;
			}
		}
	}
	
	//double matrix specific operations
	//the adjugate matrix wouldn't work with integers, each the determinant is a double return value
	public static double[][] AdjugateMatrix(double[][] matrix ){
		double[][] result = new double[matrix[0].length][matrix.length]; //
		int sign = 1;
		//for each cell we generate the minor and the subsequent determinant of each minor
		for(int i = 0; i < matrix.length; i++ ){
			for(int j = 0; j < matrix.length; j++){
				double[][] minor = getMinor(matrix, i, j);
				//we need to add a sign base on (-1)^i+j
				sign = (int)Math.pow(-1, i+j);
				result[i][j] = sign*matrixDeterminant(minor);
			}
		}
		//we need to transpose it afterwards
		result = matrixTranspose(result);
		return result;
	}
	
	public static double[][] matrixTranspose( double[][] matrix){
		double[][] result = new double[matrix[0].length][matrix.length]; //initiallize the result matrix
		for( int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				result[j][i] = result[i][j];
			}
		}
		return result;
	}
	
	public static double[][] getMinor(double[][] matrix, int ignoredRowIndex,int ignoredColumnIndex){
		double[][] result = new double[matrix.length-1][matrix[0].length-1]; //one row and one column less
		int minorRowIndex = 0;
		int minorColumnIndex = 0;
		for(int i = 0; i < matrix.length; i++){
			if( i != ignoredRowIndex ){ //if the not the ignoredRow
				for(int j = 0; j < matrix[0].length; j++){
					if( j != ignoredColumnIndex ){
						result[minorRowIndex][minorColumnIndex] = matrix[i][j];
						minorColumnIndex++;
					}
				}
				minorRowIndex++;
			}
		}
		return result;
	}
	
	//TODO we still need a multiple method for two double[][] matrix 
	
	
}
