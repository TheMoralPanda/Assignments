import java.lang.Iterable;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
    public Node first,last;//First and Last Nodes
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
        return (n>0)?false:true;
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
        if(n==1)
          last = first;
            
        
    }

    public void addLast(Item item){
    /* Adds a Node to the end of the Deque*/
         n++;
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        if(n==1){
            first = last = newNode;
        }            
        else{
            last.next = newNode;
            last = newNode;
        }      
    }

    public void removeFirst(){
    /* Removes a Node from the beginning of the Deque*/
     System.out.println(first.item);
    }

    public void removeLast(){
    /* Removes a Node from the end of the Deque*/
     System.out.println(last.item);
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
        System.out.println("begin6");
        Deque<String> d = new Deque<String>();
        
        //Unit test for Deque
        System.out.println(d.size());
        System.out.println(d.isEmpty());
        d.removeFirst();
        d.removeLast();
        d.addLast("300");
        d.removeFirst();
        d.removeLast();
        System.out.println(d.size());
        System.out.println(d.isEmpty());
        d.addFirst("200");

        d.removeFirst();
        d.removeLast();
        System.out.println(d.size());
        System.out.println(d.isEmpty());


        for(int i=1;i<10;i++){
            d.addFirst(String.valueOf(i));
            d.addLast(String.valueOf(i));
        }
        System.out.println(d.size());
        d.removeFirst();
        d.removeLast();

        for(String s : d)
            System.out.println(s);      
    }

}