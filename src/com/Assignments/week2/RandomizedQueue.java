package com.Assignments.week2;

import java.lang.Iterable;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
/**
 * Created by Vigneshwar_V on 1/11/2017.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] s;
	private int n =0;

	//Constructor for the class.
	public RandomizedQueue(){
		s = (Item[]) new Object[1];
	}

	//Function to check if queue is empty
	public boolean isEmpty(){
		return n==0;
	}

	//Function to return the total number of items currently in the queue
	public int size(){
		return n;
	}

	//Function to add an item to the queue
	public void enqueue(Item item){
		if(item ==null)
			throw new java.lang.NullPointerException();
		if(n==s.length)
			resize(2*s.length);
		s[n++] = item;

	}

	//Function to resize the array once full
	private void resize(int capacity){
		Item[] copy = (Item[]) new Object[capacity];
		for(int i=0;i<n;i++)
			copy[i] = s[i];
		s = copy;

	}

	//Function to remove and return a random Item from the queue
	public Item dequeue(){
		if(n==0)
			throw new java.util.NoSuchElementException();
		int val = StdRandom.uniform(0,n);
		Item item = s[val];
		s[val]=null;
		n--;
		s[val] = s[n];
		s[n] = null;
		if(n>0 && n==s.length/4)
			resize(s.length/2);
		return item;

	}


	//Function to return a random Item from the queue, but do not remove it.
	public Item sample(){
		if(n==0)
			throw new java.util.NoSuchElementException();
		int val = StdRandom.uniform(0,n);
		Item item = s[val];	
		return item;
	}

	/*Iterator for the RandomizedQueue API*/
    public Iterator<Item> iterator(){
        /*iterator implementation for Iterable abstract class*/
     return new RQueueIterator();
    }

    private class RQueueIterator implements Iterator<Item>{
        /*iterator implementation for Iterable abstract class*/
        
        private Item[] iteratorArray;
        private int index;

        public RQueueIterator(){
        	iteratorArray = (Item[]) new Object[n];
        	for(int i=0;i<n;i++)
        		iteratorArray[i] = s[i];
        	StdRandom.shuffle(iteratorArray);
        	index = 0;
        }
        public boolean hasNext(){
            if(index<n)
                return iteratorArray[index]!=null;
            return false;
        }

        public void remove(){
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next(){
            if(index >= iteratorArray.length)
                throw new java.util.NoSuchElementException();
            return iteratorArray[index++];
        }
    }

	//Test client to do unit and functional testing of the queue
	public static void main(String[] args){

		RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
		q.enqueue(2);
        System.out.println("The size is :"+q.size());
        System.out.println(q.sample());
		q.enqueue(3);
        System.out.println("The size is :"+q.size());
        System.out.println(q.sample());
		q.enqueue(4);
        q.enqueue(5);
        System.out.println("The size is :"+q.size());
        System.out.println(q.sample());
        System.out.println(q.sample());


        //System.out.println(q.sample());
        //q.enqueue(5);
        //q.enqueue(6);
        //q.enqueue(7);
        //q.enqueue(8);
		//System.out.println(q.sample());

		for(int i : q)
			System.out.println("-"+i);
		//System.out.println(q.size());

	}
}
