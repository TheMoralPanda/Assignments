import java.lang.Iterable;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
    private Node first,last;//First and Last Nodes
    private int n =0;//Total number of Nodes
    private class Node{
        /* Inner class - for storing a Node*/
        Item item;
        Node next;
    }
    public Deque(){
    /* Constructor for the Deque class*/
        last = null;
        first = last;

    }

    public boolean isEmpty(){
        return first == last;
    }

    public int size(){
        /*Returns the total number of items in the Data structure*/
        return n;
    }

    public void addFirst(Item item){
    /* Adds a Node to the beginning of the Deque*/
        
        Node newNode = first;
        first = new Node();
        first.item = item;
        first.next = newNode;
        n++;
        if(n==1){
            last = new Node();
            last.next = null;
            first.next = last;

        }
            
        
    }

    public void addLast(Item item){
    /* Adds a Node to the end of the Deque*/

        Node newNode = last;
        last = new Node();
        last.item = item;
        last.next = null;
        n++;
    }

    public void removeFirst(){
    /* Removes a Node from the beginning of the Deque*/
    }

    public void removeLast(){
    /* Removes a Node from the end of the Deque*/
    }

    /*Iterator for the Deque API*/
    public Iterator<Item> iterator(){
        /*iterator implementation for Iterable abstract class*/
     return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        /*iterator implementation for Iterable abstract class*/
        private Node current = first;

        public boolean hasNext(){
            return current!=null;
        }

        public void remove(){

        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String args[]){
        System.out.println("begin5");
        Deque<String> d = new Deque<String>();
        d.addFirst("b");
        d.addFirst("a");
        d.addLast("c");
        d.addLast("d");
        for(String s : d)
            System.out.println(s);      
    }

}