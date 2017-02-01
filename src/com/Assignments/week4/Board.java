package com.Assignments.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.lang.Math;
import java.util.Arrays;

/**
 * Created by Vigneshwar_V on 1/30/2017.
 */
public class Board {
    private int[][] board;
    private int n;

    public Board(int[][] blocks){
        /*Constructor for the Board class. Initialises a board from a n*n array of blocks*/
        this.board = blocks;
        n = board.length;
    }

    public int dimension(){
    /*Returns the board dimension n*/
        if(board!=null)
            return n;
        else
            return 0;
    }

    public int hamming(){
        /*Returns the number of blocks that are out of place*/
        int count = 0, k=0;
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
                k++;
                if(board[i][j]==0)
                    continue;
                if(board[i][j]!=k)
                    count++;
            }
        return count;
    }

    public int manhattan(){
        /*Return the sum of manhattan distance between blocks and goal board*/
        int count = 0, k=0;
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
                k++;
                if(board[i][j]==k || board[i][j]==0)
                    continue;
                else{
                    int a[]= truePosition(board[i][j]);
                    count += (Math.abs(a[0]-i)+Math.abs(a[1]-j));
                }
            }
        return count;
    }

    private int[] truePosition(int k){
        int a[] = new int[2];
        if(k%n==0){
            a[0]=k/n;
            a[1]=n;
        }else{
            a[0]=(k/n+1);
            a[1]=k%n;
        }
        a[0]-=1;
        a[1]-=1;
        return a;
    }

    public boolean isGoal(){
        /*Returns true if Goal Board is reached*/
        return hamming()==0;
    }

    public Board twin(){
        /* Returns a board obtained by exchanging any pair of blocks */
        //return new Board();
        int[][] twin = Arrays.copyOf(board,board.length);
        //for(int i=0;i<n;i++)
        //    for(int j=0;j<n;j++)
        int i=0 , j=0;
        while(twin[i][j]!=0) {
            i = StdRandom.uniform(n);
            j = StdRandom.uniform(n);

            //(i-1,j); (i+1,j); (i,j-1); (i,j+1)
            if ( i!=0 && i < n && j - 1 < n && twin[i][j - 1] != 0) {
                int temp = twin[i][j];
                twin[i][j] = twin[i][j - 1];
                twin[i][j - 1] = temp;
                Board twinBoard = new Board(twin);
                return twinBoard;
            }
            if (j!=n-1 && i < n && j + 1 < n && twin[i][j + 1] != 0) {
                int temp = twin[i][j];
                twin[i][j] = twin[i][j + 1];
                twin[i][j + 1] = temp;
                Board twinBoard = new Board(twin);
                return twinBoard;
            }
        }
        return null;
    }

    public boolean equals(Object y){
        /* Does this board equals y */
        return true;
    }

    //public Iterable<Board> neighbors(){
        /* All Neigboring boards */
    //}

    public String toString(){
        /* Returns the string representation of this board */
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++)
                System.out.print(board[i][j]+" ");
            System.out.println();
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
        initial.twin().toString();
        //System.out.println("Is Goal value - "+ initial.isGoal());

        // solve the puzzle
       // Solver solver = new Solver(initial);

        // print solution to standard output
       // if (!solver.isSolvable())
            //StdOut.println("No solution possible");
        //else {
            //StdOut.println("Minimum number of moves = " + solver.moves());
            //for (Board board : solver.solution())
              //  StdOut.println(board);
    }

}
