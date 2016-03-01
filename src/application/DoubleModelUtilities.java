package application;

public class DoubleModelUtilities {
	
	private static int currentPermutationIndex = 0;
	
	public static double add(double first, double second) {
		return first + second;
	}

	
	public static double Power(double base, int exponent) {
		return Math.pow(base, exponent);
	}

	public static double multiply(double num1, double num2) {
		return num1*num2;
	}
	
	public static double CumulativeSum(double[] data){
		double Sum = 0;
		for(int i = 0; i < data.length; i++){
			Sum = add(Sum, data[i]);
		}
		return Sum;
	}
	
	//pass in a specific exponent, all elements will use this exponent and then each will then be cumulative to a total sum
	public static double CumulativeSumOfPower(double[] data ,int exp){
		double Sum = 0;
		int size = data.length;
		for(int i = 0; i < size; i++){ //for each item in the array, we sum the result of its power by the exponent parameter 
			Sum = add(Sum, Power( data[i], exp) );
		}
		return Sum;
	}
	
	//we will need to revise, we don't need functional programming for this
	public static double factorOfPower( double y, double x, int exp ){
		return multiply(y, Power(x, exp));
	}
	
	public static double CumulativeSumOfMethod( double[] x_s, double[] y_s, int degree ){
		double sum = 0;
		for(int i = 0; i < x_s.length; i++ ){ //length of y_s and x_s should be the same
			sum = add(sum, factorOfPower( y_s[i], x_s[i], degree) );
		}
		return sum;
	}
	
	//interesting problem we have here
	// permutation of a set of n numbers has n! possible outcomes namely n factorial. 
	public static int[][] PermutationSet( int n ){
		//we need to determine the size of our problem space
		int permutationSize = factorial(n);
		
		System.out.println("permutation size:"+permutationSize);
		int[] integerSet = new int[n];
		for(int i = 0; i < n; i++){
			integerSet[i] = i+1;
		}
		
		int[][] permutationSets = new int[permutationSize][n];
		currentPermutationIndex = 0;
		//initial permutation row index at zero, if our calculations are correct we should not be getting indexOutofBounds errors
		heaps_algorithm(n, integerSet, permutationSets);
		
		return permutationSets;
	}
	
	/* 
	 * according to the Leibniz formula: for each permutation sigma, sgn(sigma) denotes the the signature of sigma, a value that is 
	 * +1 whenever the reordering 'CAN' be achieved successively by interchanging two values an even number of times, and -1 
	 * whenever it 'CAN' be achieved by an odd number of times. By this formula, we will not need to find the exact number of times of 
	 * swaps we need to perform inorder to achieve any particular permutation 
	 */
	public static void heaps_algorithm(int n, int[] original, int[][] permutationSets){
		if( n == 1 ){
			System.arraycopy(original, 0, permutationSets[currentPermutationIndex], 0, original.length);
			currentPermutationIndex++;
		}else{
			for(int i = 0; i < n; i++ ){
				heaps_algorithm(n-1, original, permutationSets);
				if (n%2 == 0 ){//if even
					swap(i, n-1, original);
				}else{ //else odd
					swap(0, n-1, original);
				}
			}
			//heaps_algorithm(n-1, original, permutationSets);
		}
	}
	
	//for integer array only is this module usable
	public static void swap(int index1, int index2, int[] arr ){
		int temp = arr[index2];
		arr[index2] = arr[index1];
		arr[index1] = temp;
	}
	
	
	
	//this is just a router module
	public static int factorial(int n){
		return factorial(n, 1);
	}
	
	
	//write the factorial method here
	//first things first, let's develop the factorial module first
	
	//initially the multiplier has to be set to one, if it is set to less than one, throw exception
	private static int factorial( int n, int multiplier){
		if(multiplier <= 0){
			throw new RuntimeException("zero multipler detected");
		}
		
		// 1! and 0! both produce 1
		if( n <= 1 ){ //this will be the same as (n * n-1 * ..... * 2 ) * 1, everything inside the bracket represent the multiplier value 
			return multiplier;
		}
		
		return factorial(n-1, multiplier*n);
	}
	
	
	//let's implement the determinant of any n by n matrix, the determinant using the leibniz formula is a double value return
	protected static double LeibnizFormula( int[][] permutationSets, double[][] matrix ){//we will need the permutation set as well as our matrix
		boolean currentIsEven = true; //we start at zero, the zero index is considered even
		//for each row in the permutationSet, we take that row and find the matrix
		double result = 0;
		for(int i = 0; i < permutationSets.length; i++ ){
			int[] currentSet = permutationSets[i]; 
			//the length of the currentSet should be n, we will denoted as the column index
			//our Ai selection, our i represents the row index always
			double currentSegment = getCurrentProductSeries(matrix, currentSet);
			if(currentIsEven){
				result += currentSegment;
				//if current is even right now, change it to odd for the next product series
				currentIsEven = false;
			}else{
				result -= currentSegment;	
				//if current is NOT even right now, change it to odd for the next product series
				currentIsEven = true;
			}	
		}	
		return result;
	}
	
	private static double getCurrentProductSeries(double[][] matrix, int[] permutationSet){
		double productSeries = 1;
		for(int i = 0; i < matrix.length; i++){
			productSeries *= matrix[i][permutationSet[i]-1];
		}
		return productSeries;
	}
	
	
}
