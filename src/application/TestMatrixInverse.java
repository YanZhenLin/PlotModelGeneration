package application;

public class TestMatrixInverse {

	public static void main(String[] args){
		double[][] matrix = {{6,1,1 },
                {4,-2,5},
                {2,8,7}};
		
		double[][] inverse = DoubleMatrix.inverseMatrix(matrix);
		
		DoubleMatrix.printMatrix(inverse);
		
		double[][] identity = DoubleMatrix.multiplyMatrix(matrix, inverse);
		DoubleMatrix.printMatrix(identity); //look correct if we round, seems working
	}
}
