//package com.Assignments.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

/**
 * Created by Vigneshwar_V on 2/13/2017.
 */
public class KdTree {
    private Node root;
    private int n;

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
        root = insert(root, p, 0, 0.0,0.0,1.0,1.0);

    }

    private Node insert(Node x, Point2D p, int level,double xmin,double ymin,double xmax,double ymax){
        if(x == null){
            n++;
            return new Node(p,new RectHV(xmin,ymin,xmax,ymax),null, null);
        }
        if(p.equals(x.p)){
            x.p = p;
            return x;
        }
        int cmp;
        if(level%2==0) {
           // cmp = (p.x() < x.p.x()) ? -1 : ((p.x() > x.p.x()) ? +1 : 0);
            if(p.x()<x.p.x()){
                xmax = x.p.x();
                x.lb = insert(x.lb, p, level+1,xmin, ymin, xmax, ymax);
            }
            else{
                xmin = x.p.x();
                x.rt = insert(x.rt, p, level+1,xmin, ymin, xmax, ymax);
            }

        }else {
            //cmp = (p.y() < x.p.y()) ? -1 : ((p.y() > x.p.y()) ? +1 : 0);
            if(p.y()<x.p.y()){
                ymax = x.p.y();
                x.lb = insert(x.lb, p, level+1,xmin, ymin, xmax, ymax);
            }else{ 
                ymin = x.p.y();
                x.rt = insert(x.rt, p, level+1,xmin, ymin, xmax, ymax);
            }
        }
       // if(cmp<0)
       //     x.lb = insert(x.lb, p, level+1,xmin, ymin, xmax, ymax);
       // else if(cmp>0)
        //    x.rt = insert(x.rt, p, level+1,xmin, ymin, xmax, ymax);
       // else{
        //    if(p.x()==x.p.x() && p.y()==x.p.y())
        //        x.p = p;
        //    else if(p.y()==x.p.y())
        //        x.lb = insert(x.lb, p, level+1,xmin, ymin, xmax, ymax);
        //    else if(p.x()==x.p.x())
        //        x.rt = insert(x.rt, p, level+1,xmin, ymin, xmax, ymax);

       // }
        return x;
    }

  public boolean contains(Point2D p) {
        return contains(root, p, 0);
    }
    private boolean contains(Node x, Point2D p, int depth) {
        if (x == null) {
            return false;
        }
        //System.out.println("X.p value is"+x.p.x()+", "+x.p.y());
        if (x.p.equals(p)) {

            return true;
        }
        if (depth % 2 == 0) {
            //the upper level is horizontal, so this level should be vertical, check y
            if (p.x() < x.p.x()) {
                // left
                return contains(x.lb, p, depth + 1);
            }
            else {
                //right
                return contains(x.rt, p, depth + 1);
            }

        }
        else {
            // the upper level is vertical, so this level should be horizontal, check x
            if (p.y() < x.p.y()) {
                // bottom
                return contains(x.lb, p, depth + 1);
            }
            else {
                // top
                return contains(x.rt, p, depth + 1);
            }
        }

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
        range(root,rect,list);
        return list;
    }

    private void range(Node x, RectHV query, ArrayList<Point2D> list){
    /*    if(x != null && x.rect.intersects(query))
        {
            if(query.contains(x.p))
                list.add(x.p);
            range(x.lb,query,list);//left subtree
            range(x.rt,query,list);//right subtree
        }
        return;
*/
        if (x == null) {
            return;
        }
        if (query.contains(x.p)) {
            list.add(x.p);
        }
        if (x.rt != null && query.intersects(x.rt.rect)) {
            range(x.rt, query, list);
        }
        if (x.lb != null && query.intersects(x.lb.rect)) {
            range(x.lb, query, list);
        }
       
        
    }


    public Point2D nearest(Point2D p){
        /* Returns a nearest neighbour in the set to the given point P */
        if(p==null)
            return null;
        return nearest(root,p,null);
    }
    private Point2D nearest(Node x, Point2D query, Point2D champ){
        if(x==root) {
            champ = x.p;
            return champ;
        }else if(x.p.distanceTo(query)<champ.distanceTo(query))
            champ = x.p;
        if(x.lb.rect.contains(query)) {
            if (champ.distanceTo(query) > x.lb.rect.distanceTo(query))
                champ = nearest(x.lb, query, champ);
        }else{
            if (champ.distanceTo(query) > x.rt.rect.distanceTo(query))
                champ = nearest(x.rt, query, champ);
        }
        return champ;
    }


   /* public Iterable<Point2D> keys()
    {
        Queue<Point2D> q = new Queue<Point2D>();
        inorder(root, q);
        return q;
    }
    private void inorder(Node x, Queue<Point2D> q)
    {
        if (x == null) return;
        inorder(x.lb, q);
        q.enqueue(x.p);
        System.out.println(x.p.toString()+"   -   "+x.rect.xmin()+","+x.rect.ymin()+" * "+x.rect.xmax()+" ,"+x.rect.ymax());
        inorder(x.rt, q);
    }*/

    public static void main(String args[]){
        //Unit testing for the PointSET class.
        KdTree kt = new KdTree();
        kt.insert(new Point2D(0.7,0.2));  

        System.out.println(kt.size());
        System.out.println("0.7,0.2");
        System.out.println(kt.contains(new Point2D(0.7,0.2)));
        //System.out.println(kt.contains(new Point2D(0.5,0.4)));
        kt.insert(new Point2D(0.5,0.4));

        System.out.println(kt.size());
        System.out.println("0.5,0.4");
        System.out.println(kt.contains(new Point2D(0.5,0.4)));
        kt.insert(new Point2D(0.1,0.3));
        System.out.println(kt.size());
        System.out.println("0.1,0.3");
        System.out.println(kt.contains(new Point2D(0.1,0.3)));
        kt.insert(new Point2D(0.8,0.73));
        System.out.println(kt.size());
        System.out.println("0.8,0.73");
        System.out.println(kt.contains(new Point2D(0.8,0.73)));
        kt.insert(new Point2D(0.9,0.6));
        System.out.println(kt.size());
        System.out.println("0.9,0.6");
        System.out.println(kt.contains(new Point2D(0.9,0.6)));
        kt.insert(new Point2D(0.19,0.22));
        System.out.println(kt.size());
        System.out.println("0.19,0.22");
        System.out.println(kt.contains(new Point2D(0.19,0.22)));
        kt.insert(new Point2D(0.49,0.18));
        System.out.println(kt.size());
        System.out.println("0.49,0.18");
        System.out.println(kt.contains(new Point2D(0.49,0.18)));
        kt.insert(new Point2D(0.59,0.97));
        System.out.println(kt.size());
        System.out.println("0.59,0.97");
        System.out.println(kt.contains(new Point2D(0.59,0.97)));

//
       // for(Point2D p : kt.keys())
       //     System.out.println(p.toString());

    }
}
