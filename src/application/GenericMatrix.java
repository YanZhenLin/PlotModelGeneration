package application;

public abstract class GenericMatrix <E extends Number > {
	
	/*since this is an abstract class, we do not neeed a 
	 * default constructor, as we will never call new 
	 * GenericMatrix */
	
	protected abstract E add(E o1, E o2);
	protected abstract E multiply(E o1, E o2);
	protected abstract E zero();
	
	//return type a mtarix E[][] of type E
	//inputs 2 E type matrix parameter
	public E[][] addMatrix(E[][] matrix1, E[][] matrix2){
		//if the matrix dimensions are not appropriate
		if( (matrix1.length != matrix2.length) || 
				(matrix1[0].length != matrix2[0].length ) ){
			throw new RuntimeException(
					"The matrices do not have the same size");
		}
		
		//ignore compiler warning
		//initialize the result matrix using casting (E[][]) 
		E[][] result = (E[][])new Number[matrix1.length][matrix1[0].length];
		
		//perform the addition and write into the result matrix
		for(int i = 0; i < result.length; i++){
			for(int j = 0; j < result[0].length; j++){
				result[i][j] = add(matrix1[i][j], matrix2[i][j]); 
			}
		}
		
		return result;
	}
	
	public E[][] multiplyMatrix(E[][] matrix1, E[][] matrix2){
		if(  matrix1[0].length != matrix2.length ){
			throw new RuntimeException(
					"The matrices do not have the same size");
		}
		E[][] result = (E[][])new Number[matrix1.length][matrix2[0].length];
		for(int i = 0; i < matrix1.length; i++){
			E[] currentRow = matrix1[i];
			E Sum;
			for(int j = 0; j < matrix2[0].length; j++ ){
				result[i][j] = zero();
				
				for(int z = 0; z < matrix1[i].length; z++){
					result[i][j] = add(result[i][j],
							multiply(matrix1[i][z], matrix2[z][j])); 
				}
			}	
		}
		return result;
	}
	
	//generate a new transpose matrix of the passed in parameter matrix 
	public E[][] matrixTranspose( E[][] matrix){
		E[][] result = (E[][])new Number[matrix[0].length][matrix.length]; //initiallize the result matrix
		for( int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				result[j][i] = result[i][j];
			}
		}
		return result;
	}
	
	//we need a better print fucntion
	//this won't work for all matrix, only for square matrix
	public static void printMatrix(Number[][] m1){
		for(int i = 0; i < m1.length; i++){
			for(int j = 0; j < m1[0].length; j++){
				System.out.print(" " + m1[i][j]);
			}
			System.out.println();
		}
	}
	
	
}
