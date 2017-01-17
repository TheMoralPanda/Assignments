package com.Assignments.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    /* Data members */
    private ArrayList<LineSegment> lineSegments;
    private HashMap<Point,Point> lineSeg;


    public BruteCollinearPoints(Point[] points) {
        /* Constructor for the class
        *  Also executes and finds all the line segments containing 4 points*/
        if (points == null)
            throw new java.lang.NullPointerException();
        for (int i = 0; i < points.length; i++)
            if (points[i] == null)
                throw new java.lang.NullPointerException();
        lineSegments = new ArrayList<LineSegment>();
        lineSeg = new HashMap<Point, Point>();
        LineSegment ls;
        for (int i = 0; i < points.length; i++)
            for (int j = 0; j < points.length; j++){
                if(j==i)
                    continue;
                for (int k = 0; k < points.length; k++){
                    if(k==i || k==j)
                        continue;
                    for (int l = 0; l < points.length; l++){
                        if(l==i || l==j || l==k)
                            continue;
                        if (checkDistinct(points[i], points[j], points[k], points[l])) {
                            if (checkCollinear(points[i], points[j], points[k], points[l])) {
                                Point[] p = {points[i], points[j], points[k], points[l]};
                                Arrays.sort(p);
                                if(checkDuplicate(p[0],p[3])==false) {
                                    lineSeg.put(p[0], p[3]);
                                    ls = new LineSegment(p[0], p[p.length - 1]);
                                    lineSegments.add(ls);
                                }
                            }
                        }
           }   }   }
    }

    private boolean checkDuplicate(Point p, Point q) {
        if (lineSeg.containsKey(p) == false && lineSeg.containsKey(q) == false)
            return false;
        else if (lineSeg.containsKey(p) == true) {
            if (lineSeg.get(p).compareTo(q) == 0)
                return true;
        } else if (lineSeg.containsKey(q) == true) {
            if (lineSeg.get(q).compareTo(p) == 0)
                return true;
        }
        return false;
    }

    private boolean checkCollinear(Point p, Point q, Point r, Point s){
        double first = p.slopeTo(q), second = p.slopeTo(r), third = p.slopeTo(s);
        if(first == second)
            if(second == third){
                return true;
            }
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

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
