import java.lang.Iterable;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
    private Node first,last;//First and Last Nodes
    private int n =0;//Total number of Nodes
    private class Node{
        /* Inner class - for storing a Node*/
        Item item;
        Node next ,previous;
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
        if(item==null)
            throw new java.lang.NullPointerException();
        Node newNode = first;
        first = new Node();
        first.item = item;
        first.next = newNode;
        first.previous = null;
        n++;
        if(n==1)
            last = first;
        else
            newNode.previous = first;
    }

    public void addLast(Item item){
    /* Adds a Node to the end of the Deque*/
        if(item==null)
            throw new java.lang.NullPointerException();
        n++;
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        if(n==1){
            first = last = newNode;
        }            
        else{
            newNode.previous = last;
            last.next = newNode;
            last = newNode;
        }      
    }

    public Item removeFirst(){
    /* Removes a Node from the beginning of the Deque*/
     if(first!=null) {
         Node newNode = first.next;
         Item item = first.item;
         first.next = first.previous = null;
         first = newNode;
         n--;
         return item;
     }else
        throw new java.util.NoSuchElementException();
    }

    public Item removeLast(){
    /* Removes a Node from the end of the Deque*/
     if(last!=null && n>0) {
         Node oldLast = last.previous;
         Item item = last.item;
         last.previous = null;
         last = oldLast;
         last.next = null;
         n--;
         return item;
     }else
         throw new java.util.NoSuchElementException();
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
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next(){
            if(current.next== null && current.previous==null)
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
