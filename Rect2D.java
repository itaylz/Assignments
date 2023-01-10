package Exe.Ex4;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class  Rect2D implements GeoShapeable {
	private Point2D _p1, _p2, _p3, _p4;

	public Rect2D(Point2D p1, Point2D p2,Point2D p3, Point2D p4) {

		this._p1 = p1;
		this._p2 = p2;
		this._p3 = p3;
		this._p4 = p4;
	}

	public Rect2D(ArrayList<Point2D> points){
		if(points.size()>4){
			throw new RuntimeException("cannot cast rectangle with more than 4 points.");
		}
		this._p1 = points.get(0);
		this._p2 = points.get(1);
		this._p3 = points.get(2);
		this._p4 = points.get(3);
	}


	public Rect2D(Rect2D _new) {
		this._p1 = new Point2D(_new._p1);
		this._p2 = new Point2D(_new._p2);
		this._p3 = new Point2D(_new._p3);
		this._p4 = new Point2D(_new._p4);
	}

	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		points.add(this.getPoints()[0]);
		points.add(this.getPoints()[1]);
		points.add(this.getPoints()[2]);
		points.add(this.getPoints()[3]);

		points = ShapeCollection.sortPoints(points);
		return ot.x() >= points.get(0).x() && ot.x() <= points.get(2).x() & ot.y() >= points.get(0).y() && ot.y() <= points.get(3).y();
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		double length = Math.abs(this._p1.x() - this._p2.x());
		double height = Math.abs(this._p1.y() - this._p2.y());

		return length*height;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		double length = Math.abs(this._p1.x() - this._p2.x());
		double height = Math.abs(this._p1.y() - this._p2.y());

		return 2*length + 2*height;
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub

	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Rect2D(_p1,_p2,_p3,_p4);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		_p1.scale(center,ratio);
		_p2.scale(center,ratio);
		_p3.scale(center,ratio);
		_p4.scale(center,ratio);


	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		_p1.rotate(center,angleDegrees);
		_p2.rotate(center,angleDegrees);
		_p3.rotate(center,angleDegrees);
		_p4.rotate(center,angleDegrees);
		
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] _points = new Point2D[4];
		_points[0] = _p1;
		_points[1] = _p2;
		_points[2] = _p3;
		_points[3] = _p4;

		return _points;
	}

}
