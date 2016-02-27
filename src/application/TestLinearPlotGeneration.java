package application;

public class TestLinearPlotGeneration {

	public static void main(String[] args){
		LinearModelPlot newplot = new LinearModelPlot(1,0, 300, 0, 100, 0, 100);
		DoublePoint[] plots = newplot.generateRandomPlot();
		
		int plotSize = plots.length;
		for(int i = 0; i < plotSize; i++){
			System.out.println(i+"). "+plots[i]);
		}
		
	}
}
