package Exe.Ex4;

import javax.swing.text.Segment;
import java.nio.channels.Pipe;
import java.util.ArrayList;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{

	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;
	private ArrayList<Point2D> _points;
	public Triangle2D(Point2D p1, Point2D p2, Point2D p3){
		_p1 = new Point2D(p1);
		_p2 = new Point2D(p2);
		_p3 = new Point2D(p3);
	}

	public Triangle2D(ArrayList<Point2D> points){
		if(points.size()>3){
			throw new ArrayIndexOutOfBoundsException("cannot cast with more than 3 points");
		}
		else{
			_points = new ArrayList<>();
			_p1 = new Point2D(points.get(0));
			_p2 = new Point2D(points.get(1));
			_p3 = new Point2D(points.get(2));
		}
	}
	public Triangle2D(String[] pointStrings) {
		if (pointStrings.length < 6) throw new IllegalArgumentException("minimum 6 elements required to cast triangle");
		try {
			_p1 = new Point2D(Double.parseDouble(pointStrings[0]), Double.parseDouble(pointStrings[1]));
			_p2 = new Point2D(Double.parseDouble(pointStrings[2]), Double.parseDouble(pointStrings[3]));
			_p3 = new Point2D(Double.parseDouble(pointStrings[4]), Double.parseDouble(pointStrings[5]));
		} catch (IllegalArgumentException e) {
			System.err.println("ERROR: cannot cast triangle " + e.getMessage());
		}
	}



	public String toString() {
		return "" + _p1 + ',' + _p2 + ',' + _p3;
	}

	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		boolean ans = false;
		double a = (_p1.x() - ot.x()) * (_p2.y() - ot.y()) - (_p1.y() - ot.y()) * (_p2.x() - ot.x());
		double b = (_p2.x() - ot.x()) * (_p3.y() - ot.y()) - (_p2.y() - ot.y()) * (_p3.x() - ot.x());
		double c = (_p3.x() - ot.x()) * (_p1.y() - ot.y()) - (_p3.y() - ot.y()) * (_p1.x() - ot.x());
		if ((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0)) {
			ans = true;
		}
		return ans;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		double area = Math.abs(_p1.x()*(_p2.y()-_p3.y())+_p2.x()*(_p3.y()-_p1.y())+_p3.x()*(_p1.y()-_p2.y()));
		return area/2;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		return _p1.distance(_p2)+ _p2.distance(_p3)+ _p3.distance(_p1);
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Triangle2D(_p1, _p2, _p3);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
		_p3.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
		_p3.rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		return new Point2D[] { _p1, _p2, _p3 };
	}

	public double[] get_X() {
		double[] ans = new double[3];
		for(int i =0;i<3;i++){
			ans[i] = this.getPoints()[i].x();
		}
		return ans;
	}

	public double[] get_Y() {
		double[] ans = new double[3];
		for(int i =0;i<3;i++){
			ans[i] = this.getPoints()[i].y();
		}
		return ans;
	}

	public void addPoint(Point2D point){
		_points.add(point);
	}
	
}
