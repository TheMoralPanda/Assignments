package com.Assignments.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;

/**
 * Created by Vigneshwar_V on 2/13/2017.
 */
public class KdTree {


    public KdTree(){
        /*Constructor for the PointSET class*/

    }
    public boolean isEmpty(){
        /* Check if the Set is empty or not */
        return true;
    }

    public int size(){
        /*Return number of elements in the set*/
        return 0;
    }

    public void insert(Point2D p){
        /* Add a point to the set if its not already there.*/
        if(p==null)
            throw new java.lang.NullPointerException();

    }

    public boolean contains(Point2D p){
        /* Does the set contain the point or not*/
        if(p==null)
            throw new java.lang.NullPointerException();
        return true;
    }

    public void draw(){
        /* Draw all the points on the set, using STDDRAW*/
        //if(points.size()!=0 && points!=null){
        //for(Point2D p: points){
        //    p.draw();
        //}
        //}

    }

    public Iterable<Point2D> range(RectHV rect){
        /* Returns an Iterator of all the points that are inside the given rectangle */
        if(rect==null)
            throw new java.lang.NullPointerException();
        ArrayList<Point2D> list = new ArrayList<Point2D>();
        /*for(Point2D p: points){
            if(rect.contains(p))
                list.add(p);
        }
        return list;*/
        return null;
    }

    public Point2D nearest(Point2D p){
        /* Returns a nearest neighbour in the set to the given point P */
        if(p==null)
            throw new java.lang.NullPointerException();
        /*Point2D champ = points.min();
        double dist = champ.distanceTo(p);

        for(Point2D q : points){
            if(q.distanceTo(p)<dist){
                champ = q;
                dist = q.distanceTo(p);
            }
        }
        return champ;*/
        return null;
    }

    public static void main(String args[]){
        //Unit testing for the PointSET class.
    }
}
