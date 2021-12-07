package Assignment3;

/**
 * Queue interface for a Character queue
 */
public interface CharQueueable {
    /**
     * Inserts an element at the end of the queue
     * @ param element  The element to be inserted
     */
    public void enqueue(char element);

    /**
     * Removes and returns the first element
     * @return element removed (or IllegalStateException if empty)
     */ 
    public char dequeue(); 

    /**
     * Returns, but does not remove, the first element
     * @return top element (or IllegalStateException if empty)
     */
    public char first ();

    /**
     * Tests if the queue is empty
     * @return true if queue is empty; else false
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in the Queue
     * @return number of elements in the Queue
     */
    public int size(); 
}
