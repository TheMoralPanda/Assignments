
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class FastCollinearPoints {

    private ArrayList<LineSegment> lineSegments;

    //Constructor for the class.
    public FastCollinearPoints(Point[] points){
        /* To find all the line segments containing 4 or more points */
        if (points == null)
            throw new java.lang.NullPointerException();
        for (int i = 0; i < points.length; i++)
            if (points[i] == null)
                throw new java.lang.NullPointerException();
        if(checkDistinct(points)!=true)
            throw new java.lang.IllegalArgumentException();

        ArrayList<Point> sPoints ;
        lineSegments = new ArrayList<LineSegment>();
        Arrays.sort(points);
        for(Point p : points){
            sPoints = new ArrayList<Point>(Arrays.asList(points));
            Collections.sort(sPoints,p.slopeOrder());
            int x=1;
            while(x<sPoints.size()){
                ArrayList<Point> segment = new ArrayList();
                Double slopeRef = p.slopeTo(sPoints.get(x));
                do{
                    segment.add(sPoints.get(x++));
                }while( x<sPoints.size() && p.slopeTo(sPoints.get(x))==slopeRef );

                if(segment.size()>=3 && p.compareTo(segment.get(0))<0){
                    lineSegments.add(new LineSegment(p,segment.get(segment.size()-1)));
                }
            }

        }
    }

    private boolean checkDistinct(Point[] p){
        for(int i=0;i<p.length;i++)
            for(int j=i+1;j<p.length;j++)
                if(p[i].compareTo(p[j])==0)
                    return false;
        return true;
    }

    public LineSegment[] segments(){
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    public int numberOfSegments(){
        /* Returns the total number of Segments */
        return lineSegments.size();
    }

}
