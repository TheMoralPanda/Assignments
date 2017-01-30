package com.Assignments.week4;

/**
 * Created by Vigneshwar_V on 1/30/2017.
 */
public class Board {
    public Board(int[][] blocks){
        /*Constructor for the Board class. Initialises a board from a n*n array of blocks*/
    }

    public int dimension(){
    /*Returns the board dimension n*/
    }

    public int hamming(){
        /*Returns the number of blocks that are out of place*/
    }

    public int manhattan(){
        /*Return the sum of manhattan distance between blocks and goal board*/
    }

    public boolean isGoal(){
        /*Returns true if Goal Board is reached*/
    }

    public Board twin(){
        /* Returns a board obtained by exchanging any pair of blocks */
    }

    public boolean equals(Object y){
        /* Does this board equals y */
    }

    public Iterable<Board> neighbors(){
        /* All Neigboring boards */
    }

    public String toString(){
        /* Returns the string representation of this board */
    }

    public static void main(String[] args){
        /* Unit tests */
    }
}
