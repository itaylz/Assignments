package Exe.Ex4;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
    private ArrayList<GUI_Shapeable> _shapes;

    private static final File home = FileSystemView.getFileSystemView().getHomeDirectory();

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
        _shapes.removeAll(_shapes);
        //////////////////////////////////////////
    }

    @Override
    public void save(String file) {
        //////////add your code below ///////////
        try {
            Files.writeString(home.toPath(), file, StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //////////////////////////////////////////
    }

    @Override
    public void load(String file) {
        ////////// add your code below ///////////
        try {
            File myObj = new File(home.toURI());
            Scanner myReader = new Scanner(myObj);
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
        if(_shapes.size()==0){
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
            Collections.addAll(shapes, g.getPoints());
        }
        sortPoints(shapes);

        ans = new Rect2D(,,,);
        //////////////////////////////////////////
        return ans;
    }

    private Point2D sortPoints(ArrayList<Point2D> points){
        Iterator<Point2D> it = points.iterator();
        while(it.hasNext()){
            if(it.next().x()<it.hasNext().x()){

            }
        }
        return null;
    }
    @Override
    public String toString() {
        String ans = "";
        for(int i=0;i<size();i=i+1) {
            ans += this.get(i);
        }
        return ans;
    }


}