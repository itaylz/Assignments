package Exe.Ex4;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
    private ArrayList<GUI_Shapeable> _shapes;

    public ShapeCollection() {
        _shapes = new ArrayList<GUI_Shapeable>();
    }
    @Override
    public GUI_Shapeable get(int i) {
        return _shapes.get(i);
    }

    @Override
    public int size() {
        return _shapes.size();
    }

    @Override
    public GUI_Shapeable removeElementAt(int i) {
        //////////add your code below ///////////
        _shapes.remove(i);
        return _shapes.get(i);
        //////////////////////////////////////////
    }

    @Override
    public void addAt(GUI_Shapeable s, int i) {
        //////////add your code below ///////////
        _shapes.add(i,s);
        //////////////////////////////////////////
    }
    @Override
    public void add(GUI_Shapeable s) {
        if(s!=null && s.getShape()!=null) {
            _shapes.add(s);
        }
    }
    @Override
    public ShapeCollectionable copy() {
        //////////add your code below ///////////
        ShapeCollection _shapes_copy = new ShapeCollection();
        for (GUI_Shapeable shape : _shapes) {
            _shapes_copy.add(shape);
        }
        return _shapes_copy;
        //////////////////////////////////////////
    }

    @Override
    public void sort(Comparator<GUI_Shapeable> comp) {
        //////////add your code below ///////////
        _shapes.sort(comp);
        //////////////////////////////////////////
    }

    @Override
    public void removeAll() {
        //////////add your code below ///////////
        _shapes.clear();
        //////////////////////////////////////////
    }

    @Override
    public void save(String file) {
        //////////add your code below ///////////
        try {
            FileWriter saved_file = new FileWriter(file);
            for (GUI_Shapeable shape : _shapes) {
                saved_file.append(shape.toString()).append(String.valueOf('\n'));
            }
            saved_file.close();
        } catch (IOException e) {
            System.err.println("ERROR: Could not open \"" + file + "\" for writing: " + e.getMessage());
            System.err.println("Looked in " + System.getProperty("user.dir"));
        }
    }
        //////////////////////////////////////////


    @Override
    public void load(String file) {
        ////////// add your code below ///////////
        try {
            File saved_file = new File(file);
            Scanner myReader = new Scanner(saved_file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //////////////////////////////////////////
    }
    @Override
    public Rect2D getBoundingBox() {
        Rect2D ans = null;
        //////////add your code below ///////////
        Point2D bottom_left = _shapes.get(0).getShape().getPoints()[0];
        Point2D top_right = _shapes.get(0).getShape().getPoints()[0];
        Point2D point;
        for (GUI_Shapeable shape : _shapes) {
            for (int i = 0; i < shape.getShape().getPoints().length; i++) {
                point = shape.getShape().getPoints()[i];
                if (point.y() > top_right.y())
                    top_right = shape.getShape().getPoints()[i];
                if (point.x() < bottom_left.x())
                    bottom_left = shape.getShape().getPoints()[i];
            }
        }
        ans = new Rect2D(bottom_left, top_right);
        return ans;
    }

        /**if(_shapes.size()==0){
            return null;
        }
        ArrayList<Point2D> shapes = new ArrayList<Point2D>();
        for (GUI_Shapeable shape:_shapes) {
            GeoShapeable g = shape.getShape();
            if(g instanceof Circle2D){
               Point2D[] circle_points = g.getPoints();
               Point2D up = new Point2D(circle_points[0].x(),circle_points[0].y()+((Circle2D) g).getRadius());
               Point2D down = new Point2D(circle_points[0].x(),circle_points[0].y()-((Circle2D) g).getRadius());
               Point2D left = new Point2D(circle_points[0].x()-((Circle2D) g).getRadius(),circle_points[0].y()-((Circle2D) g).getRadius());
               Point2D right = new Point2D(circle_points[0].x()-((Circle2D) g).getRadius(),circle_points[0].y()+((Circle2D) g).getRadius());

               shapes.add(up);
               shapes.add(down);
               shapes.add(left);
               shapes.add(right);
            }
            //Collections.addAll(shapes, g.getPoints());
        }
        //ans = new Rect2D(sortPoints(shapes));
        //////////////////////////////////////////
        return ans;
    }


    public static ArrayList<Point2D> sortPoints(ArrayList<Point2D> points) {
        // Use an anonymous inner class to create a comparator that compares
        // the size of two points.
        Comparator<Point2D> comparator = new Comparator<Point2D>(){
            @Override
            public int compare(Point2D p1, Point2D p2) {
                return Double.compare(p1.x(), p2.x()), Double.compare(p1.y(),p2.y());
            }
        };

         Use an iterator to sort the points in the list using the comparator.
        Iterator<Point2D> iterator = points.iterator();
        ArrayList<Point2D> box_points = new ArrayList<Point2D>();
        double minX;
        double minY;
        double maxX;
        double maxY;
        while (iterator.hasNext()) {
            Point2D p1 = iterator.next();
            Point2D p2 = iterator.next();
            minX = Math.min(p1.x(), p2.x());
            minY = Math.min(p1.y(), p2.y());
            maxX = Math.max(p1.x(), p2.x());
            maxY = Math.min(p1.x(), p2.x());

            Point2D point1 = new Point2D(minX,minY);
            Point2D point2 = new Point2D(minX, maxY);
            Point2D point3 = new Point2D(maxX,minY);
            Point2D point4 = new Point2D(maxX,maxY);

            box_points.add(point1);
            box_points.add(point2);
            box_points.add(point3);
            box_points.add(point4);
        }
        return box_points;
    }*/


    @Override
    public String toString() {
        String ans = "";
        for(int i=0;i<size();i=i+1) {
            ans += this.get(i);
        }
        return ans;
    }
}

