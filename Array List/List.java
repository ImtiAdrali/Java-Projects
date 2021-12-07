package Assignment4;

import java.util.Comparator;

public interface List {

    /** Returns the number of elements in the list **/
    int size();

    /** Returns whether the list is empty **/
    boolean isEmpty();

    /** Returns (but does not remove) the element at index i. **/ 
    int get(int i) throws IndexOutOfBoundsException;

    /** Replaces the element at index i with e, and returns the replaced element **/
    int set(int i, int e) throws IndexOutOfBoundsException;

    /** Inserts element e to be at index i, shifting all subsequent elements later **/
    void add(int i, int e) throws IndexOutOfBoundsException;

    /** Removes/returns the elememt at index i, shifting subsequent elements earlier **/
    int remove(int i) throws IndexOutOfBoundsException;
    
    /* 
    This method copies the array into a new array with
    a size that is greater than its current size, and 
    replaces the original array with the new one. You 
    may choose to implmement either a constant or 
    doubling algorithm. If the array is uncreated, then 
    throws IllegalStateException.
    */
    void increaseCapacity() throws IllegalStateException; 

    /* 
    This method copies the array into a new array with
    a size that exactly matches the number of elements, 
    and replaces the original array with the new one.
    If array is empty, or hasn't been created yet, then 
    throws IllegalStateException.
    */
    void minimize() throws IllegalStateException; 

    /* 
    This method returns the length of the array (not how
    many elements are in it). If the array has not been 
    created, then throws IllegalStateException. 
    */
    int capacity() throws IllegalStateException;
    
    /// sorts the array list s
    void quickSort(int low, int high);

}