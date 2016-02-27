package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GenerateData {

	public static void main(String[] args) throws IOException{
		String resourceFolder = System.getProperty("user.dir")+File.separator+"resources";
		int plotsize = 100;
		double xmin = 0;
		double xmax = 100;
		double ymin = 0;
		double ymax = 100;
		String targetFile = resourceFolder+File.separator+"LinearPlot_"+plotsize+".txt";
		File file= FileHandler.createFile(targetFile);
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		
		//LinearModelPlot(m, b, size, xmin, xmax, ymin, ymax)
		LinearModelPlot newplot = new LinearModelPlot(1, 0, plotsize, xmin, xmax, ymin, ymax);
		DoublePoint[] plots = newplot.generateRandomPlot();
		StringBuffer buffer = new StringBuffer("");
		int j = 1;
		for(int i = 0; i < plotsize; i++){
			if(j%10 == 0){
				buffer.append(plots[i] + "\n");
			}else{
				buffer.append(plots[i] + " ");
			}
			j++;
		}
		out.write(buffer.toString());
		out.close();
	}
}
