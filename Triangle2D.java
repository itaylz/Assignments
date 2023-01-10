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

	public Triangle2D(Point2D p1, Point2D p2, Point2D p3){
		this._p1 = p1;
		this._p2 = p2;
		this._p3 = p3;
	}

	public String toString() {
		return "" + _p1 + ',' + _p2 + ',' + _p3;
	}

	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		//find equation of each side of triangle using points and segment
		Segment2D seg1 = new Segment2D(_p1,_p2);
		Segment2D seg2 = new Segment2D(_p2,_p3);
		Segment2D seg3 = new Segment2D(_p3,_p1);


		return false;
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
	
}
