package com.Assignments.week4;

import edu.princeton.cs.algs4.*;

import java.lang.Math;
import java.util.Arrays;

/**
 * Created by Vigneshwar_V on 1/30/2017.
 */
public class Board {
    private int[][] board;
    private int n,oi,oj;

    public Board(int[][] blocks){
        /*Constructor for the Board class. Initialises a board from a n*n array of blocks*/
        board = Arrays.copyOf(blocks,blocks.length);
        n = board.length;
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(board[i][j]==0)
                {
                    oi = i; oj=j;
                }
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
        while(true){
            i = StdRandom.uniform(n);
            j = StdRandom.uniform(n);
            System.out.println("i,j is "+i+" ,"+j);
            if ( j!=0 && i < n && j - 1 < n && twin[i][j - 1] != 0 && twin[i][j]!=0) {
                int temp = twin[i][j];
                twin[i][j] = twin[i][j - 1];
                twin[i][j - 1] = temp;
                Board twinBoard = new Board(twin);
                return twinBoard;

            }
            if (j!=n-1 && i < n && j + 1 < n && twin[i][j + 1] != 0 && twin[i][j]!=0) {
                int temp = twin[i][j];
                twin[i][j] = twin[i][j + 1];
                twin[i][j + 1] = temp;
                Board twinBoard = new Board(twin);
                return twinBoard;
            }
        }

    }

    public boolean equals(Object y){
        /* Does this board equals y */
        Board that = (Board)y;
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(board[i][j]!=that.board[i][j])
                    return false;
        return true;
    }

    public Iterable<Board> neighbors(){
        /* All Neigboring boards */
        //(i-1,j); (i+1,j); (i,j-1); (i,j+1)
        Stack<Board> bstack = new Stack<Board>();
        //Board temp;

        int i = this.oi, j=this.oj;
        int tv;
        int [][]t = deepCopy();

        if(isValidneighbor(i-1,j)) {
            tv = t[i][j];
            t[i][j] = t[i-1][j];
            t[i-1][j] = tv;
            bstack.push(new Board(t));

        }

        t = deepCopy();
        if(isValidneighbor(i+1,j)) {
            tv = t[i][j];
            t[i][j] = t[i+1][j];
            t[i+1][j] = tv;
            bstack.push(new Board(t));

        }

        t = deepCopy();
        if(isValidneighbor(i,j-1)) {
            tv = t[i][j];
            t[i][j] = t[i][j-1];
            t[i][j-1] = tv;
            bstack.push(new Board(t));

        }

        t = deepCopy();

        if(isValidneighbor(i,j+1)) {
            tv = t[i][j];
            t[i][j] = t[i][j+1];
            t[i][j+1] = tv;
            bstack.push(new Board(t));

        }

        System.out.println("stack size"+bstack.size());
        return bstack;
    }

    private int[][] deepCopy() {
        if (board == null) {
            return null;
        }

        int[][] result = new int[board.length][];
        for (int i = 0; i < n; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return result;
    }

    private boolean isValidneighbor(int k, int l){
        boolean inBoundsK = (k >= 0) && (k < n);
        boolean inBoundsL = (l >= 0) && (l < n);
        return (inBoundsK && inBoundsL)?true:false;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
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
        //initial.twin().toString();
        System.out.println("The stack contents are as follows");
        for(Board b: initial.neighbors())
            System.out.println(b);
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
