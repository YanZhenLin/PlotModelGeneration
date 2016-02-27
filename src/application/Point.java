package application;
import java.util.*;

public abstract class Point<E extends Number> implements Comparable<Point>{
	protected E x;
	protected E y;
	
	protected Point<E> ClosestNeighbor;
	
	public Point(E x, E y){
		this.x = x;
		this.y = y;
	}
	
	public E getX(){
		return x;
	}
	
	public void setX(E x){
		this.x = x;
	}
	
	public E getY(){
		return y;
	}
	
	public void setY(E y){
		this.y = y;
	}
	
	public Point<E> getClosestNeighbor(){
		return this.ClosestNeighbor;
	}
	
	public void setClosestNeighbor(Point<E> neighbor){
		this.ClosestNeighbor = neighbor;
	}

	@Override
	public abstract int compareTo(Point o);
	
	@Override
	public String toString(){
		return "("+this.x+","+this.y+")";
	}
	
	public static double EuclideanDistance(Point point1, Point point2){
		return EuclideanDistance((double)point1.getX(), (double)point1.getY(), (double)point2.getX(), (double)point2.getY() );
	}
	
	private static double EuclideanDistance(double x1, double y1, double x2,double y2){
		double xsquarediff = diffSquare(x1, x2);
		double ysquarediff = diffSquare(y1, y2);
		return Math.sqrt(xsquarediff+ysquarediff);
	}
	
	//always positive value
	private static double diffSquare(double a, double b){
		return (Math.pow((a-b) , 2) );
	}
	
}
