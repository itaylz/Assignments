
package Exe.Ex4;

/**
 * This class represents a 2D point in the plane.
 * Do NOT change this class! It would be used as is for testing.
 * Ex4: you should edit and update this class!
 * @author boaz.benmoshe
 */




public class Point2D{
    //public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1,2), EPS=EPS2;
    public static final Point2D ORIGIN = new Point2D(0,0);
    private double _x,_y;
    public Point2D(double x,double y) {
    	_x=x; _y=y;
    }
    public Point2D(Point2D p) {
       this(p.x(), p.y());
    }
    public Point2D(String s) {
        try {
            String[] a = s.split(",");
            _x = Double.parseDouble(a[0]);
            _y = Double.parseDouble(a[1]);
        }
        catch(IllegalArgumentException e) {
            System.err.println("ERR: got wrong format string for Point2D init, got:"+s+"  should be of format: x,y");
            throw(e);
        }
    }
    public double x() {return _x;}
    public double y() {return _y;}
 
    public int ix() {return (int)_x;}
    public int iy() {return (int)_y;}
  
    public Point2D add(Point2D p) {
    	Point2D a = new Point2D(p.x()+x(),p.y()+y());
    	return a;
    }
    public String toString()
    {
        return _x+","+_y;
    }

    public double distance()
    {
        return this.distance(ORIGIN);
    }
    public double distance(Point2D p2)
    {
        double dx = this.x() - p2.x();
        double dy = this.y() - p2.y();
        double delta = (dx*dx+dy*dy);
        return Math.sqrt(delta);
    }

    public double distance(Segment2D seg) {
        Point2D[] points = seg.getPoints();
        double dist = Math.sqrt(Math.pow((points[0].x() - points[1].x()), 2) + Math.pow((points[0].y() - points[1].y()), 2));
        return dist * 2;
    }

    @Override
    public boolean equals(Object p)
    {
        if(p==null || !(p instanceof Point2D)) {return false;}
        Point2D p2 = (Point2D)p;
        return ( (_x==p2._x) && (_y==p2._y));
    }
    public boolean close2equals(Point2D p2, double eps)
    {
        return ( this.distance(p2) < eps );
    }
    public boolean close2equals(Point2D p2)
    {
        return close2equals(p2, Ex4_Const.EPS);
    }
    /**
     * This method returns the vector between this point and the target point. The vector is represented as a Point2D.
     * @param target
     * @return
     */
    public Point2D vector(Point2D target) {
    	double dx = target.x() - this.x();
    	double dy = target.y() - this.y();
    	return new Point2D(dx,dy);
    }
	
	public void move(Point2D vec) {
		this._x += vec.x();
		this._y += vec.y();
	}
	
	/////////////////////// You should implement the methods below ///////////////////////////
	public void scale(double ratio) {
		//////////add your code below ///////////
        this._x*=ratio;
        this._y*=ratio;
        /////////////////////////////////////////
	}

    public void scale(Point2D cen,double ratio){
            Point2D vector = cen.vector(this);
            vector.scale(ratio);
            Point2D after_scale = cen.add(vector);
            this._x = after_scale.x();
            this._y = after_scale.y();
    }
    public double angleFromP(Point2D pAngle) {

        double x = pAngle.x() - this._x;
        double y = pAngle.y() - this._y;
        return Math.atan2(y, x);
    }
	public void rotate(Point2D cen, double angleDegrees) {
		//////////add your code below ///////////
        Point2D directionVec = cen.vector(this);
        directionVec.rotate(angleDegrees);
        Point2D newPoint = cen.add(directionVec);
        this._x = newPoint._x;
        this._y = newPoint._y;
        }

    public void rotate(double angleDegrees) {
        double mag = this.distance();
        double oldAngleRadians = Math.atan2(_y, _x);
        double oldAngleDegrees = Math.toDegrees(oldAngleRadians);
        double newAngleDegrees = oldAngleDegrees + angleDegrees;
        double newAngleRadians = Math.toRadians(newAngleDegrees);
        _x = mag * Math.cos(newAngleRadians);
        _y = mag * Math.sin(newAngleRadians);
        }
        /**
         * This method return the angle in degrees of the vector pointing at the point, in relation to the x axis
         * @return the angle in degrees
         */

    public double angleDegrees() {
        double angleRadians = Math.atan2(_y, _x);
        return Math.toDegrees(angleRadians);
    }
		/////////////////////////////////////////
}


