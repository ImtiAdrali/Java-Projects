package Assignment7;
import java.util.Comparator;

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueueable<K,V>{
	//----Begin nested PQEntry class----
	protected static class PQEntry<K,V> implements Entry<K,V> {
		private K k;
		private V v;
		
		public PQEntry (K key, V value) {
			k = key;
			v = value;
		}
		// methods of Entry interface
		public K getKey() { return k; }
		public V getValue() { return v; }
		// utilities not exposed as part of the Entry interface
		protected void setK(K k) { this.k = k;}
		protected void setV(V v) { this.v = v;}
	
	}
	//----End nested PQEntry class----
	
	//----Instance variables for an AbstractPriorityQueue ----
	/* The comparator defines the order of keys in the PQ */
	private Comparator<K> comp;
	
	/* Create an empty PQ using the given comparator order */
	protected AbstractPriorityQueue(Comparator<K> c) {
		comp = c;
	}
	
	/* Create an empty PQ based on the natural order of its key */
	protected AbstractPriorityQueue() {
		this(new DefaultComparator<K>());
	}
	
	/* Compare two entry according to key */
	protected int compare (Entry<K,V> a, Entry<K,V> b) {
		return comp.compare(a.getKey(), b.getKey());
	}
	
	//----End instance variables for an AbstractPriorityQueue ----
	
	/* Determine whether a key is valid or comparable*/ 
	protected boolean checkKey(K key) throws IllegalArgumentException {
		try {
			return (comp.compare(key, key) == 0); // test whether the key can compare itself
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Incomparable key!");
		}
	}
	
	public boolean isEmpty() { return size() == 0; }
}
