package Assignment3;

/**
 * Linked List interface for a Character list
 */
public interface CharListable {
    /**
     * Inserts an element at the end of the list
     * @ param element  The element to be inserted
     */
    public void addToTail(char element);

    /**
     * Inserts an element at the front of the list
     * @ param element  The element to be inserted
     */
    public void addToHead(char element);

    /**
     * Removes and returns the first (head) element
     * @return element removed (or IllegalStateException if empty)
     */ 
    public char remove(); 

    /**
     * Tests if the list is empty
     * @return true if list is empty; else false
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in the Stack
     * @return number of elements in the Stack
     */
    public int size(); 

    
}
