package com.Assignments.week2;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import com.Assignments.week2.RandomizedQueue;

/**

 * Created by Vigneshwar_V on 1/12/2017.
 */
public class Permutation {
    public static void main(String args[]){
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = StdIn.readInt();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if(k>0) {
                q.enqueue(item);
                k--;
            }
        }
        for(String item:q)
            System.out.println(item);
    }
}
