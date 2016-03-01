package application;

public class TestPermutationGenerator {

	public static void main(String[] args){
		int[][] permutationResult = DoubleModelUtilities.PermutationSet(4);
		
		int sizeOfPermutation = permutationResult.length;
		for(int i = 0; i < sizeOfPermutation; i++){
			for(int j = 0; j < permutationResult[i].length; j++){
				System.out.print(permutationResult[i][j]);
			}
			System.out.println("");
		}
		
	}
	
}
