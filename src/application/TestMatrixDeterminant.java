package application;

public class TestMatrixDeterminant {

	public static void main(String[] args){
		double[][] matrix = {{6,1,1 },
                {4,-2,5},
                {2,8,7}};
		
		double detA = DoubleMatrix.matrixDeterminant(matrix);
		System.out.println(detA);
	}
}
