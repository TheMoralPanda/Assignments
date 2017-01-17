package com.Assignments.week3;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
        Point[] sPoints ;
        for(Point p : points){
            sPoints = points.clone();
            Arrays.sort(sPoints,p.slopeOrder());
            checkCollinear(sPoints);
        }




    }

    private void checkCollinear(Point[] points){
        ArrayList<Point> segment = new ArrayList<Point>();
        for(int i=1;i<points.length;i++){
            for(int j=i+1;j<points.length;j++){
                double slopeI = points[0].slopeTo(points[i]);
                double slopeJ = points[0].slopeTo(points[j]);
                if(slopeI==slopeJ)
                    segment.add(points[i]);
                    segment.add(points[j]);
        }

    }

    public int numberOfSegments(){
        /* Returns the total number of Segments */
        return 0;
    }

    public LineSegment[] segments(){
        /* Returns the list of Line Segments */
        return null;
    }
}
