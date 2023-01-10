package Exe.Ex4;


import java.awt.*;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{
	private Point2D _point1;
	private  Point2D _point2;

	public Segment2D(Point2D p1, Point2D p2){
		_point1 = new Point2D(p1);
		_point2 = new Point2D(p2);
	}

	public Segment2D(double x1, double x2, double y1, double y2){
		_point1 = new Point2D(x1,y1);
		_point2 = new Point2D(x2,y2);
	}

	public Segment2D(Segment2D seg) {
		this(seg.getPoints()[0], seg.getPoints()[1]);
	}


	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		double minX = Math.min(_point1.x(), _point2.x()), maxX = Math.max(_point1.x(), _point2.x()), minY = Math.min(_point1.y(), _point2.y()), maxY = Math.max(_point1.y(), _point2.y());
		if (ot.x() < minX || ot.x() > maxX || ot.y() < minY || ot.y() > maxY) return false;
		return !(ot.distance(this) > 0.1);
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		double dist = Math.sqrt(Math.pow((_point2.x()- _point1.x()),2)+Math.pow((_point2.y()- _point1.y()),2));
		return dist*2;
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		_point1.move(vec);
		_point2.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Segment2D(_point1,_point2);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		_point1.scale(center,ratio);
		_point2.scale(center,ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		_point1.rotate(center,angleDegrees);
		_point2.rotate(center,angleDegrees);
	}

	public double distance(Segment2D seg){
		Point2D[] point = seg.getPoints();
		return Math.sqrt(Math.pow(point[1].x()-point[0].x(),2)+Math.pow(point[1].y()-point[0].y(),2));
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		return new Point2D[] { new Point2D(_point1), new Point2D(_point2) };
	}
	
}









