package Exe.EX3;

import java.util.Queue;
import java.nio.file.Path;
import java.util.LinkedList;
/**
 * This class implements the Map2D interface.
 * You should change (implement) this class as part of Ex3. */
public class MyMap2D implements Map2D {
	private int[][] _map;

	public MyMap2D(int w, int h) {
		init(w, h);
	}

	public MyMap2D(int size) {
		this(size, size);
	}

	public MyMap2D(int[][] data) {
		this(data.length, data[0].length);
		init(data);
	}

	@Override
	public void init(int w, int h) {
		_map = new int[w][h];

	}

	@Override
	public void init(int[][] arr) {
		init(arr.length, arr[0].length);
		for (int x = 0; x < this.getWidth() && x < arr.length; x++) {
			for (int y = 0; y < this.getHeight() && y < arr[0].length; y++) {
				this.setPixel(x, y, arr[x][y]);
			}
		}
	}

	@Override
	public int getWidth() {
		return _map.length;
	}

	@Override
	public int getHeight() {
		return _map[0].length;
	}

	@Override
	public int getPixel(int x, int y) {
		return _map[x][y];
	}

	@Override
	public int getPixel(Point2D p) {
		return this.getPixel(p.ix(), p.iy());
	}


	public void setPixel(int x, int y, int v) {
		_map[x][y] = v;
	}

	public void setPixel(Point2D p, int v) {
		setPixel(p.ix(), p.iy(), v);
	}

	@Override
	public void drawSegment(Point2D point, Point2D next_point, int v) {
		setPixel(point, v);
		double x_diff = Math.abs(next_point.x() - point.x());
		double y_diff = Math.abs(next_point.y() - point.y());
		double max_dist = Math.max(x_diff, y_diff);
		if (max_dist == 0) {
			return;
		}
		double x = x_diff / max_dist, y = y_diff / max_dist;
		Point2D vector = new Point2D(x, y);
		double length = vector.distance();
		Point2D click = new Point2D(point);
		while (!click.close2equals(next_point, length / 2)) {
			setPixel(click, v);
			click = click.add(vector);
		}
		long min_x = Math.round(Math.min(point.x(), next_point.x())),
				min_y = Math.round(Math.min(point.y(), next_point.y())),
				max_x = Math.round(Math.max(point.x(), next_point.x())),
				max_y = Math.round(Math.max(point.y(), next_point.y()));
		if ((click.ix() >= min_x) && (click.ix() <= max_x) && (click.iy() >= min_y) && (click.iy() <= max_y)){
			setPixel(click, v);
		setPixel(next_point, v);
		}
	}

	@Override
	public void drawRect(Point2D p1, Point2D p2, int col) {
		// TODO Auto-generated method stub
		int x1 = p1.ix();
		int x2 = p2.ix();
		int y1 = p1.iy();
		int y2 = p2.iy();
		for(int x = Math.min(x1, x2);x<=Math.max(x1, x2);x++){
			for(int y = Math.min(y1,y2);y<=Math.max(y1, y2);y++){
				setPixel(x,y,col);
			}
		}
	}

	@Override
	public void drawCircle(Point2D p, double rad, int col) {
		// TODO Auto-generated method stub
	
		int init_r = p.ix() + (int)Math.floor(rad), init_l = p.ix() - (int)Math.round(rad);
		int init_u = p.iy() + (int)Math.floor(rad), init_d = p.iy() - (int)Math.round(rad);
		
		int counter = 0;

		if(init_r>getWidth()){
			init_r = getWidth();
		}
		if (init_l<0){
			init_l = 0;
		}
		if (init_u>getHeight()){
			init_u = getHeight();
		}
		if(init_d<0){
			init_d = 0;
		}				
		for(int l = init_l;l<init_r;l++){
			for(int h = init_d;h<init_u;h++){
				Point2D init = new Point2D(l,h);
				double length = p.distance(init);
				counter++;
				System.out.println(counter);
				if(length<=(int)rad){
					setPixel(l, h, col);


				}
			}
		}
	}

	@Override
	public int fill(Point2D p, int new_v) {
		// TODO Auto-generated method stu
		return fill(p.ix(),p.iy(),new_v);
	}

	@Override
	public int fill(int x, int y, int new_v) {
		// TODO Auto-generated method stub
		Point2D init = new Point2D(x,y);
		Queue<Point2D> to_fill = new LinkedList<Point2D>(); 
		to_fill.add(init);

		boolean[][] checked = new boolean[getWidth()][getHeight()];

		int counter = 0;
		while(!to_fill.isEmpty()){
			checked[x][y] = true;
			Point2D vector = to_fill.remove();
			LinkedList<Point2D> points_to_fill = points_to_fill(vector, checked);
			setPixel(vector, new_v);
			to_fill.addAll(points_to_fill);
			counter+=1;
			System.out.println(counter);
			for(Point2D point : points_to_fill)
			{
				checked[point.ix()][point.iy()] = true;
			}
		}

			return getPixel(x,y);
	}


	private LinkedList<Point2D> points_to_fill(Point2D p, boolean[][] v){
		return points_to_fill(p.ix(),p.iy(), v);
	}

	private LinkedList<Point2D> points_to_fill(int x, int y, boolean[][] v){
		LinkedList<Point2D> to_fill = new LinkedList<Point2D>();
		Point2D current = new Point2D(x,y);
		Point2D up = new Point2D(x,y+1);
		Point2D down = new Point2D(x, y-1);
		Point2D left = new Point2D(x-1,y);
		Point2D right = new Point2D(x+1,y);
		
		if(within_map(up)&& !v[up.ix()][up.iy()] && getPixel(up)==getPixel(current)){
			to_fill.add(up);
		}
		if(within_map(down) && !v[down.ix()][down.iy()] && getPixel(down)==getPixel(current)){
			to_fill.add(down);
		}
		if(within_map(left) && !v[left.ix()][left.iy()] && getPixel(left)==getPixel(current)){
			to_fill.add(left);
		}
		if(within_map(right) && !v[right.ix()][right.iy()] && getPixel(right)==getPixel(current)){
			to_fill.add(right);
		}
		return to_fill;
		
	}

	private boolean within_map(Point2D p){
		return (p.ix() >= 0 && p.ix() <= getWidth() && p.iy() >= 0 && p.iy() <= getHeight());
	}


	@Override
	public Point2D[] shortestPath(Point2D p1, Point2D p2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int shortestPathDist(Point2D p1, Point2D p2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void nextGenGol() {

		int[][] copy= new int[_map.length][_map.length];

		for (int x=0; x<_map.length; ++x) {

			for (int y=0; y<_map.length; ++y) {

				copy[x][y] = WHITE;

				int voisin = voisin(x,y);

				if( (getPixel(x,y)!=WHITE) ) {

					if(voisin==2 || voisin==3) { copy[x][y]=BLACK;
					}
				}
				else { if(voisin==3) {copy[x][y]=BLACK;}
				}
			}
		}
		//
		for(int x=0; x<_map.length; x++) {

			for(int y=0; y<_map.length; y++) { //manual array copy

				_map[x][y]=copy[x][y];
			}
		}
	}

	private int voisin(int x, int y) {

		int count=0;

		for(int i=-1; i<=1; ++i) {

			for(int j=-1; j<=1; ++j) {


				if( ((i!=0)||(j!=0)) && (x+i>=0) && ((x+i)<_map.length) && ((y+j)>=0) && ((y+j)<_map.length) && (getPixel(x+i,y+j)!=WHITE) ) { ++count; }
			}
		}
		return count;
	}
	@Override
	public void fill(int c) {
		for(int x = 0;x<this.getWidth();x++) {
			for(int y = 0;y<this.getHeight();y++) {
				this.setPixel(x, y, c);
			}
		}
		
	}

	

}
