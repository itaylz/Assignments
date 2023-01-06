package Exe.Ex4;


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
		return false;
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
		Point2D translated1 = new Point2D(this._point1.x() + vec.x(),this._point1.y() + vec.y());
		Point2D translated2 = new Point2D(this._point2.x() + vec.x(),this._point2.y() + vec.y());
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Segment2D(_point1,_point2);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		return new Point2D[] { new Point2D(_point1), new Point2D(_point2) };
	}
	
}