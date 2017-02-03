package com.Assignments.week4;


import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Vigneshwar_V on 1/30/2017.
 */
public class Solver {
    private int moves;
    boolean isSolvable;
    private ArrayList<searchNode> solution;
    private class searchNode{
        /*Defining searchnode as an inner class*/
        Board b;
        int sMoves;
        searchNode previous;

        public searchNode(Board initial){
            this.b = initial;
            sMoves = 0;
            previous = null;
        }
        public searchNode(Board b,int moves, searchNode previous){
            this.b = b;
            this.sMoves = moves;
            this.previous = previous;
        }
        public String toString(){
            return b.toString();
        }

    }
    public Solver(Board initial){
        /* Finds the solution to intial board using A* algorithm */
        moves =0;
        solution = new ArrayList<>();
        searchNode previous,current,twin;
        current = new searchNode(initial);
        previous = current;
        twin = new searchNode(initial.twin(),0, null);
        MinPQ<searchNode> pq1 = new MinPQ<searchNode>(new boardComparator());
        MinPQ<searchNode> pq2 = new MinPQ<searchNode>(new boardComparator());
        pq1.insert(current);
        //pq2.insert(twin);
        current = pq1.delMin();
        //twin = pq2.delMin();
        //System.out.pron
       while(current.b.isGoal()==false){
            for(searchNode s : findNeighbors(current)){
                pq1.insert(s);
            }

            solution.add(current);
            current = pq1.delMin();
            //twin = pq2.delMin();
            moves++;
        }
        //System.out.println(pq2.size());
        //if(twin.b.isGoal()==true)
         //   isSolvable = false;
        //else
          //  isSolvable = true;
        solution.add(current);
    }

    private searchNode[] findNeighbors(searchNode s){
        ArrayList<searchNode> neighbors = new ArrayList<>();
        for(Board b: s.b.neighbors()){
            //System.out.println(b.toString());
            if(s.previous==null || b.equals(s.previous.b)!=true)
                neighbors.add(new searchNode(b,s.sMoves+1,s));
        }
        //System.out.println(neighbors.size());
        return neighbors.toArray(new searchNode[neighbors.size()]);
    }

    private static class boardComparator implements Comparator<searchNode>{
        public int compare(searchNode a, searchNode b){
           int val = (a.b.manhattan()+a.sMoves)-(b.b.manhattan()+b.sMoves);
           if(val>0)
               return +1;
           else if(val<0)
               return -1;
           else
               return 0;
        }
    }

    public boolean isSolvable(){
        /* Returns if the initial board is solvable or not */
        return isSolvable;
    }

    public int moves(){
        /* Returns the minimum number of moves required to solve the board.
        Returns -1 if unsolvable.

         */
        return moves ;
    }

    public Iterable<Board> solution(){
        /* Returns the sequence of boards in a shortest solution; Null if unsolvable*/
        Queue<Board> soln = new Queue<Board>();
        if(solution.size()!=0) {
            for (searchNode s : solution)
                soln.enqueue(s.b);
        return soln;
        }
        return null;
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        initial.toString();
        System.out.println("****************************");

        //initial.twin().toString();
        //System.out.println("The stack contents are as follows");
        //for(Board b: initial.neighbors())
          //  System.out.println(b);
        //System.out.println("Is Goal value - "+ initial.isGoal());

        // solve the puzzle
         Solver solver = new Solver(initial);
        System.out.println("Total no of moves"+solver.moves());
        // print solution to standard output
        // if (!solver.isSolvable())
        //StdOut.println("No solution possible");
        //else {
        //StdOut.println("Minimum number of moves = " + solver.moves());
        //for (Board board : solver.solution())
         // StdOut.println(board);
    }
}

