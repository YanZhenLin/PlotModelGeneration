package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class ShowDataPlot extends Application {

	private LinkedList<DoublePoint> data;
	private double min_x = Double.POSITIVE_INFINITY;
	private double max_x = 0;
	private double min_y = Double.POSITIVE_INFINITY;
	private double max_y = 0;
	
	@Override 
	public void start(Stage primaryStage){
		//we want to have a border pane layout the topology
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(20, 20, 20, 20));
		final FileChooser fileChooser = new FileChooser();
		Button openButton = new Button("Select File From Desktop");
		pane.setTop(openButton);
		openButton.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                	try {
						processFile(file);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
                	
                	//the data list is populated at this point, we need to process the data
                	//create a scatter chart
                	//loopthrough the list and add each point to the scatter chart
                	
                	//create a new Scatter Chart client and pass it the list, the boundaries, and get back the chart
                	ExtendedScatterChart chart = new ExtendedScatterChart(data, min_x, max_x, min_y, max_y);
                	ScatterChart<Number, Number> sc = chart.getChart();
                	pane.setCenter(sc);
                	
                	//we will need to analyze the data at this point
                	//we need to build a new dataHandler, this will be analytics system
                	
                	//the analytics will generate a mathematical model
                	
                	
                }
            }});
		
		Scene scene = new Scene(pane, 500, 500);
		primaryStage.setTitle("ShowDataPlot");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void processFile(File file) throws FileNotFoundException{
		
		//read the file here, we can use a scanner
		Scanner input = new Scanner(file);
		
		if(input != null){
			if(data == null)
				data = new LinkedList<DoublePoint>();
		}
		
		
		while( input.hasNext() ){
			//we need to insert it into the linkedList
			String next = input.next();
			if( next.length() > 3){
				DoublePoint nextPoint = new DoublePoint(next);
				//System.out.println("Adding next point"+nextPoint);
				data.add( nextPoint ); //add it
				checkBoundary(nextPoint);
			}
		}
		
		
	}
	
	//reset the x,y minimum and maximum boundaries, if the current value is greater or smaller than the current min and max
	private void checkBoundary(DoublePoint currentPoint){
		if( currentPoint.x < min_x )
			min_x = currentPoint.x;
		
		if( currentPoint.x > max_x )
			max_x = currentPoint.x;
		
		if( currentPoint.y < min_y)
			min_y = currentPoint.y;
		
		if( currentPoint.y > max_y )
			max_y = currentPoint.y;
	}
	
	public static void main(String[] args){
		launch(args);
	}
	
}
