import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Vigneshwar_V on 1/30/2017.
 */
public class Solver {
    private int moves;
    private boolean isSolvable;
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
        if(initial == null)
            throw new java.lang.NullPointerException();
        moves =0;
        solution = new ArrayList<>();
        searchNode previous,current,twin;
        current = new searchNode(initial);
        previous = current;
        MinPQ<searchNode> pq1 = new MinPQ<searchNode>(new boardComparator());
        MinPQ<searchNode> pq2 = new MinPQ<searchNode>(new boardComparator());
        pq1.insert(current);
        twin = new searchNode(current.b.twin(),0,null);
        current = pq1.delMin();
        //solution.add(current);
        pq2.insert(twin);
        twin = pq2.delMin();
       while(current.b.isGoal()==false){
           if(twin.b.isGoal()==true)
           {
               this.isSolvable = false;
               break;
           }
            for(searchNode s : findNeighbors(current)){
                pq1.insert(s);
            }
           for(searchNode s : findNeighbors(twin)){
               pq2.insert(s);
           }

            solution.add(current);
            current = pq1.delMin();
            twin = pq2.delMin();
            moves++;

        }
        if(current.b.isGoal()==true)
            this.isSolvable=true;
        solution.add(current);
    }

    private searchNode[] findNeighbors(searchNode s){
        ArrayList<searchNode> neighbors = new ArrayList<>();
        for(Board b: s.b.neighbors()){
            if(s.previous==null || b.equals(s.previous.b)!=true)
                neighbors.add(new searchNode(b,s.sMoves+1,s));
        }
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
        if(isSolvable) {
            Queue<Board> soln = new Queue<Board>();
            if (solution.size() != 0) {
                for (searchNode s : solution)
                    soln.enqueue(s.b);
                return soln;
            }
        }
        return null;

    }

}

