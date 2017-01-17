package com.Assignments.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class BruteCollinearPoints {
    /* Data members */
    private ArrayList<LineSegment> lineSegments;


    public BruteCollinearPoints(Point[] points){
        /* Constructor for the class
        *  Also executes and finds all the line segments containing 4 points*/
        if(points == null)
            throw new java.lang.NullPointerException();
        for(int i=0;i<points.length;i++)
            if(points[i]==null)
                throw new java.lang.NullPointerException();
        lineSegments = new ArrayList<LineSegment>();
        LineSegment ls;
        for( int i=0;i<points.length;i++)
            for(int j=i+1;j<points.length;j++)
                for(int k=j+1;k<points.length;k++)
                    for(int l=k+1;l<points.length;l++)
                        if(checkDistinct(points[i],points[j],points[k],points[l])){
                            if(checkCollinear(points[i],points[j],points[k],points[l])){
                                ls = new LineSegment(points[i],points[l]);
                                lineSegments.add(ls);
                                System.out.println(ls.toString());
                            }
                        }
    }

    private boolean checkCollinear(Point p, Point q, Point r, Point s){
        double first = p.slopeTo(q), second = p.slopeTo(r), third = p.slopeTo(s);
        if(first == second)
            if(second == third)
                return true;
        return false;
    }

    private boolean checkDistinct(Point p, Point q, Point r, Point s){
        if(p.compareTo(q)!=0 && p.compareTo(r)!=0 && p.compareTo(s)!=0 && q.compareTo(r)!=0 && q.compareTo(s)!=0 && r.compareTo(s)!=0)
            return true;
        else
            return false;
    }
    public  int numberOfsegments(){
        /* Returns the total number of segments that is currently available
        * in the given set of points*/

        return lineSegments.size();
    }

    public LineSegment[] segments(){
        /* To return the list of line segments found in the given points */
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        System.out.println(points.length);
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
