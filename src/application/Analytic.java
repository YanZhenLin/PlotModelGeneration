package application;

import java.util.LinkedList;


//this class will decide which polynomial regression model is the best fit
// which judge best fit by highest utility
public class Analytic {

	DoublePoint[] dataArray;
	
	public Analytic( LinkedList<DoublePoint> data ){
		dataArray = data.toArray(new DoublePoint[data.size()]);
	}
	
	public Analytic( DoublePoint[] data){
		dataArray = data;
	}
	
	//this class will be the controller to the model classification system
	//first things first, we need to get the matrix
	
	
	
}
