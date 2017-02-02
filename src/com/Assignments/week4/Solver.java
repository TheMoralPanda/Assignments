package com.Assignments.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Vigneshwar_V on 1/30/2017.
 */
public class Solver {

    public Solver(Board initial){
        /* Finds the solution to intial board using A* algorithm */
        int moves =0;
        ArrayList<Board> solution = new ArrayList<>();
        Board previous,current;
        current = initial;
        MinPQ<Board> pq1 = new MinPQ<Board>(new boardComparator());
        pq1.insert(initial);

        previous = current = pq1.delMin();
        while(current.isGoal()==false){
            for(Board b: current.neighbors()){
                if(b.equals(previous)!=true)
                    pq1.insert(b);
            }
            previous = current;
            solution.add(previous);
            current = pq1.delMin();
            moves++;
        }

        for(Board b: solution){
            b.toString();
        }
    }

    private static class boardComparator implements Comparator<Board>{
        public int compare(Board a, Board b){
           if(a.manhattan()+moves <
        }
    }

    public boolean isSolvable(){
        /* Returns if the initial board is solvable or not */
        return true;
    }

    public int moves(){
        /* Returns the minimum number of moves required to solve the board.
        Returns -1 if unsolvable.

         */
        return 0;
    }

    public Iterable<Board> solution(){
        /* Returns the sequence of boards in a shortest solution; Null if unsolvable*/
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

        // print solution to standard output
        // if (!solver.isSolvable())
        //StdOut.println("No solution possible");
        //else {
        //StdOut.println("Minimum number of moves = " + solver.moves());
        //for (Board board : solver.solution())
        //  StdOut.println(board);
    }
}

