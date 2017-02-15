package com.Assignments.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

/**
 * Created by Vigneshwar_V on 2/13/2017.
 */
public class KdTree {
    private Node root;
    public int n;

    private static class Node{
        /* Node data type.*/
        private Point2D p;
        private RectHV rect;
        private Node lb,rt;

        public Node(Point2D p, RectHV rect, Node left, Node right){
            this.p = p;
            this.rect = rect;
            this.lb = left;
            this.rt = right;
        }
    }
    public KdTree(){
        /*Constructor for the PointSET class*/
        root = null;
        n=0;
    }
    public boolean isEmpty(){
        /* Check if the Set is empty or not */
        return root==null;
    }

    public int size(){
        /*Return number of elements in the set*/
        return n;
    }

    public void insert(Point2D p){
        /* Add a point to the set if its not already there.*/
        if(p==null)
            throw new java.lang.NullPointerException();
        root = insert(root, p, 0);
        n++;
    }

    private Node insert(Node x, Point2D p, int level){
        if(x == null)
            return new Node(p,null,null, null);
        int cmp;
        if(level==0 || level%2==0)
            cmp = (p.x() < x.p.x())?-1:((p.x()>x.p.x())?+1:0);
        else
            cmp = (p.y() < x.p.y())?-1:((p.y()>x.p.y())?+1:0);
        if(cmp<0)
            x.lb = insert(x.lb, p, level+1);
        else if(cmp>0)
            x.rt = insert(x.rt, p, level+1);
        else{
            x.p = p;
            n--;
        }

        return x;
    }

    public boolean contains(Point2D p){
        /* Does the set contain the point or not*/
        if(p==null)
            throw new java.lang.NullPointerException();
        return get(p)?true:false;
    }

    private boolean get(Point2D p){
        Node x = root;
        while (x != null)
        {
            int cmp = p.compareTo(x.p);
            if      (cmp  < 0) x = x.lb;
            else if (cmp  > 0) x = x.rt;
            else
            if (cmp == 0)
                return true;
        }
        return false;
    }

    public void draw() {
        draw(root, 1, new RectHV(0.0, 0.0, 1.0, 1.0));
    }
    private void draw(Node x, int depth, RectHV rect) {
        if (x != null) {
            if (depth % 2 == 0) {
                // horizontal line
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius(.005);

                StdDraw.line(rect.xmin(), x.p.y(), rect.xmax(), x.p.y());
                StdDraw.setPenColor(StdDraw.BLACK);
                x.p.draw();
                draw(x.lb, depth + 1, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y()));
                draw(x.rt, depth + 1, new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax()));
            }
            else {
                // vertical line
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius(.005);
                StdDraw.line(x.p.x(), rect.ymin(), x.p.x(), rect.ymax());
                StdDraw.setPenColor(StdDraw.BLACK);
                x.p.draw();
                draw(x.lb, depth + 1, new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax()));
                draw(x.rt, depth + 1, new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax()));
            }
        }
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
