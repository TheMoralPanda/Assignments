package com.Assignments.week3;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class BruteCollinearPoints {
    /* Data members */
    private ArrayList<ArrayList<Point>> lineSegments ;

    public BruteCollinearPoints(Point[] points) {
        /* Constructor for the class
        *  Also executes and finds all the line segments containing 4 points*/
        if (points == null)
            throw new java.lang.NullPointerException();
        for (int i = 0; i < points.length; i++)
            if (points[i] == null)
                throw new java.lang.NullPointerException();
        if(checkDistinct(points)!=true)
            throw new java.lang.IllegalArgumentException();
        lineSegments = new ArrayList<ArrayList<Point>>();

        LineSegment ls;
        for (int i = 0; i < points.length; i++)
            for (int j = i+1; j < points.length; j++){
                if(j==i)
                    continue;
                for (int k = j+1; k < points.length; k++){
                    if(k==i || k==j)
                        continue;
                    for (int l = k+1; l < points.length; l++){
                        if(l==i || l==j || l==k)
                            continue;
                        Point[] p = {points[i], points[j], points[k], points[l]};
                        if (checkDistinct(p)) {
                            if (checkCollinear(points[i], points[j], points[k], points[l])) {

                                Arrays.sort(p);
                                //if(checkDuplicate(p[0],p[3])==false) {
                                    ArrayList<Point> pList = new ArrayList<Point>(Arrays.asList(p));
                                    //ls = new LineSegment(p[0], p[p.length - 1]);
                                if(lineSegments.contains(pList)!=true)
                                    lineSegments.add(pList);
                                //}
                            }
                        }
           }   }   }

    }


    private boolean checkCollinear(Point p, Point q, Point r, Point s){
        double first = p.slopeTo(q), second = p.slopeTo(r), third = p.slopeTo(s);
        if(first == second)
            if(second == third){
                return true;
            }
        return false;
    }

    private boolean checkDistinct(Point[] p){
        for(int i=0;i<p.length;i++)
            for(int j=i+1;j<p.length;j++)
                if(p[i].compareTo(p[j])==0)
                    return false;
        return true;
        }

        /*if(p.compareTo(q)!=0 && p.compareTo(r)!=0 && p.compareTo(s)!=0 && q.compareTo(r)!=0 && q.compareTo(s)!=0 && r.compareTo(s)!=0)
            return true;
        else
            return false;*/



    public LineSegment[] segments(){
        /* To return the list of line segments found in the given points */
        //return lineSegments.toArray(new LineSegment[lineSegments.size()]);
        LineSegment[] lS = new LineSegment[lineSegments.size()];
        for(int i=0;i<lineSegments.size();i++){
            lS[i] = new LineSegment(lineSegments.get(i).get(0),lineSegments.get(i).get(lineSegments.get(i).size()-1));
        }
        return lS;

    }

    public int numberOfSegments(){
        /* Returns the total number of segments that is currently available
        * in the given set of points*/

        return lineSegments.size();
    }




}
