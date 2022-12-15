package Exe.EX3;

import java.util.Queue;
import java.nio.file.Path;
import java.util.LinkedList;
/**
 * This class implements the Map2D interface.
 * You should change (implement) this class as part of Ex3. */
public class MyMap2D implements Map2D{
	private int[][] _map;
	
	public MyMap2D(int w, int h) {init(w,h);}
	public MyMap2D(int size) {this(size,size);}
	
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
		init(arr.length,arr[0].length);
		for(int x = 0;x<this.getWidth()&& x<arr.length;x++) {
			for(int y=0;y<this.getHeight()&& y<arr[0].length;y++) {
				this.setPixel(x, y, arr[x][y]);
			}
		}
	}
	@Override
	public int getWidth() {return _map.length;}
	@Override
	public int getHeight() {return _map[0].length;}
	@Override
	public int getPixel(int x, int y) { return _map[x][y];}
	@Override
	public int getPixel(Point2D p) { 
		return this.getPixel(p.ix(),p.iy());
	}

	
	public void setPixel(int x, int y, int v) {_map[x][y] = v;}
	public void setPixel(Point2D p, int v) { 
		setPixel(p.ix(), p.iy(), v);
	}

	@Override
	public void drawSegment(Point2D p1, Point2D p2, int v) {
		// TODO Auto-generated method stub
		int x1 = p1.ix(), x2 = p2.ix();
		int y1 = p1.iy(), y2 = p2.iy();
		while(x1!=x2&&y1!=y2&&x1<=_map.length&&y1<=_map[0].length){
			this.setPixel(x1,y1,v);
			
			if(x1==x2){
				x1=x2;
			}
			if(y1==y2){
				y1=y2;
			}
			if(y1==y2&&x1==x2){
				break;
			}
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
	
		int init_r = p.ix() + (int)Math.round(rad), init_l = p.ix() - (int)Math.round(rad);
		int init_u = p.iy() + (int)Math.round(rad), init_d = p.iy() - (int)Math.round(rad);
	
		if(init_r>getWidth()){
			init_r = getWidth();
		}
		else if (init_l<0){
			init_l = 0;
		}
		else if (init_u>getHeight()){
			init_u = getHeight();
		}
		else if(init_d<0){
			init_d = 0;
		}				
		for(int l = init_l;l<init_r;l++){
			for(int h = init_d;h<init_u;h++){
				Point2D current = new Point2D(l,h);
				double length = p.distance(current);
				if(length<=rad){
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
		Point2D start = new Point2D(x,y);


		
		Queue <Point2D> to_fill = new LinkedList<Point2D>();
		to_fill.add(start);
		
		for(int l = 0;l<getWidth();l++){
			for(int h =0;h<getHeight();h++){
				if(getPixel(x,y)!=WHITE){
					break;
				}
				fill(new_v);
			}
		}
	
		int been_x = start.ix();
		int been_y = start.iy();
		while(getPixel(x,y)!=WHITE){
			if(getPixel(been_x,been_y)==getPixel(next_point(x,y,0))&&y<getHeight()-1){
				to_fill.add(next_point(x,y,0));
				y++;
			}
			
			if(getPixel(been_x,been_y)==getPixel(next_point(x,y,2))&&y>0){
				to_fill.add(next_point(x,y,2));
				y--;
			}
			
			if(getPixel(been_x,been_y)==getPixel(next_point(x,y,3))&&x>0){
				to_fill.add(next_point(x,y,3));
				x--;
			}
			
			if(getPixel(been_x,been_y)==getPixel(next_point(x,y,1))&&x<getWidth()-1){
				to_fill.add(next_point(x,y,1));
				x++;
			}
		}
		for(Point2D item:to_fill){
			setPixel(item, new_v);
		}
			return getPixel(x,y);
	}

	private int next_point(Point2D p){
		return next_point(p.ix(),p.iy());
	}

	private Point2D next_point(int x, int y, int direction){
		Point2D next_point = new Point2D(x,y);
		switch(direction){
			case(0):
				Point2D up = new Point2D(x,y+1);
				next_point = up;
				break;
			case(1):
				Point2D right = new Point2D(x+1,y);
				next_point = right;
				break;
			case(2):
				Point2D down = new Point2D(x,y-1);
				next_point = down;
				break;
			case(3):
				Point2D left = new Point2D(x-1,y);
				next_point = left;
				break;
		}

		return next_point;
		
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
		// TODO Auto-generated method stub
		
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
