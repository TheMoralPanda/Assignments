package com.Assignments.week3;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import javax.sound.sampled.Line;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments;
    private HashMap<Point,Point> lineSeg;


    //Constructor for the class.
    public FastCollinearPoints(Point[] points){
        /* To find all the line segments containing 4 or more points */
        if (points == null)
            throw new java.lang.NullPointerException();
        for (int i = 0; i < points.length; i++)
            if (points[i] == null)
                throw new java.lang.NullPointerException();
        Point[] sPoints ;
        lineSegments = new ArrayList<LineSegment>();
        lineSeg = new HashMap<Point, Point>();

        for(Point p : points){
            System.out.println("The point is"+p);
            sPoints = points.clone();
            printArray(sPoints);
            Arrays.sort(sPoints,p.slopeOrder());
           printArray(sPoints);
            checkCollinear(sPoints);

        }
    }

    private void printArray(Point[] p){
        for(Point p1: p)
            System.out.print(p1+", ");
        System.out.println();
    }

    private void checkCollinear(Point[] points){
        ArrayList<Point> segment = new ArrayList<Point>();
        LineSegment lS;
        segment.add(points[0]);

        for(int i=1, start =i, end=i+1;i<points.length-1;i++){
            double slopeI = points[0].slopeTo(points[i]);
            double slopeJ = points[0].slopeTo(points[i+1]);
            if(slopeI==slopeJ) {
                end++;
                continue;
            }else{

                if(end-start>=3){
                    for(int k=start;k<end;k++)
                        segment.add(points[k]);
                    //System.out.println("Before Sorting the segment");
                    //printArray(segment.toArray(new Point[segment.size()]));
                    //System.out.println("After Sorting the segment");
                    Collections.sort(segment);
                    //printArray(segment.toArray(new Point[segment.size()]));
                    //System.out.println("*************************************");
                    if(checkDuplicate(segment.get(0),segment.get(segment.size()-1))==false) {
                        //Collections.sort(segment);
                        //System.out.println(segment.toString());
                        lineSeg.put(segment.get(0),segment.get(segment.size()-1));
                        lS = new LineSegment(segment.get(0),segment.get(segment.size()-1));
                        lineSegments.add(lS);
                    }
                }
                start = end;
                end++;
            }
            }
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

    public int numberOfSegments(){
        /* Returns the total number of Segments */
        return lineSegments.size();
    }

    public LineSegment[] segments(){
        /* Returns the list of Line Segments */
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

         //draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        System.out.println("THe total list is: "+collinear.numberOfSegments());
    }
}
