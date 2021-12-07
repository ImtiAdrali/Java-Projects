package Assignment7;
public interface PriorityQueueable<K, V> {
	int size();
	boolean isEmpty();
	
	/* Create an entry with key k and value v in the priority queue */
	Entry<K,V> insert(K key, V value) throws IllegalArgumentException;
	
	/* Return but does not remove a PQ entry having minimum key or null of the PQ is empty */
	Entry<K,V> min();
	
	/* Remove and return a PQ entry having minimum key; or return  null of the PQ is empty */
	Entry<K,V> removeMin();

}
