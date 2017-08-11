import java.util.NoSuchElementException;

public class QueueLList<T> implements Queue<T> {

    private int total;  // number of nodes

    // variables pointing to the first and the last node
    private Node first, last;

    private class Node {
        private T Datum;     // value in the node
        private Node next;   // variable points to the next node
    }

    public QueueLList() { }

    
    // add Datum to the queue
    // the new node will be added to the end the list
    // the return data type is QueueLList
    // before adding, the queue can be empty
    public QueueLList<T> enqueue(T Datum)
    {
		if(first == null){
			first = new Node();
			first.Datum = Datum;
			last = first;
		} else {
			Node temp = new Node();
			temp.Datum = Datum;
			last.next = temp;
			last = temp;
		}
		total++;
		
        return this;
    }

    // get the value from the first node
    // the first node will be removed from the list
    // if the number of nodes is 0, throw java.util.NoSuchElementException()
    public T dequeue()
    {
		if(first == null){
			throw new NoSuchElementException();
		} else {
			T temp = first.Datum;
			first = first.next;
			total--;
			return temp;
		}
    }
    
    // check whether the queue is empty
    // return true if it is empty; otherwise, return false
    public boolean isEmpty(){
		if(first == null)
			return true;
		else
			return false;
    }

}