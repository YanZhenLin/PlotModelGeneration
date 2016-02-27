package application;

import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class ExtendedScatterChart {

	private ScatterChart<Number, Number> chart;
	private LinkedList<DoublePoint> data;
	private DoublePoint[] dataArray;
	private NumberAxis xAxis;
	private NumberAxis yAxis;
	
	public ExtendedScatterChart(LinkedList<DoublePoint> data, 
			double min_x, double max_x, double min_y, double max_y){
		this.data = data;
		
		dataArray = data.toArray(new DoublePoint[data.size()]);
		xAxis = new NumberAxis(min_x, max_x, Math.round((max_x-min_x)/20) );
	    yAxis = new NumberAxis(min_y, max_y, Math.round((max_y-min_y)/20) );
	    chart = new ScatterChart<Number,Number>(xAxis, yAxis);
		
	    xAxis.setLabel("X"); //text label for the x-axis
    	yAxis.setLabel("Y");
	    
	    fill();
	}
	
	public void setXAxisLabel(String xLabel){
		xAxis.setLabel(xLabel);
	}
	
	public void setYAxisLabel(String yLabel){
		xAxis.setLabel(yLabel);
	}
	
	private void fill(){
		if(chart.getData() == null){ //chart has no data definition, we define it
    		chart.setData(FXCollections.<XYChart.Series<Number, Number>>observableArrayList() );
    	}
		
    	//note the data will be added now
    	ScatterChart.Series<Number, Number> series = new ScatterChart.Series<Number, Number>();
    	series.setName("Data Plot "+(chart.getData().size()+1));
    	
    	int dataSize = data.size();
    	for (int i = 0; i < dataSize; i++){ 
    		series.getData().add( new ScatterChart.Data<Number, 
                    Number>( dataArray[i].x, dataArray[i].y));
    	}
    	chart.getData().add(series);
	}
	
	public ScatterChart<Number, Number> getChart(){
		return chart;
	}
	
}
