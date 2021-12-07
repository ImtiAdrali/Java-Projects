package Assignment7;
import java.util.Comparator;
import java.util.Iterator;


public class SortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
	PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();

	public SortedPriorityQueue() {super();}
	public SortedPriorityQueue(Comparator<K> comp) {super(comp);}

	/* Insert an entry with key k and value v in the proper position in the priority queue */
	public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K,V> newest = new PQEntry<>(key, value);
		Position<Entry<K,V>> cursor = list.last(); 
		while (cursor != null && compare(newest, cursor.getElement()) < 0) { // move cursor backward to find for smaller key and compare keys of two Entry<K,V> objects; cursor.getElement() returns Entry<K,V>
			cursor = list.before(cursor);
		}
		if (cursor == null) list.addFirst(newest); // newest is the smallest key; no entry in the list
		else cursor = list.addAfter(cursor, newest); // newest goes after smaller key the cursor is referring 
		return newest;
	}
	
	/* Return but does not remove a PQ entry having minimum key or null of the PQ is empty */
	public Entry<K,V> min() {
		if (list.isEmpty()) return null;
		else return list.first().getElement();
	}
	
	/* Remove and return a PQ entry having minimum key; or return  null of the PQ is empty */
	public Entry<K,V> removeMin() {
		if (list.isEmpty()) return null;
		else {
			Position<Entry<K,V>> cursor = list.first();
			return list.remove(cursor); 
		}	
	}
	
	public int size() {
		return list.size();
	}
	
	public void print() {
	      if (isEmpty()) System.out.println("{}");
	        else {
	        	Position<Entry<K,V>> cursor = list.first();
	            String returnValue = "{" + cursor.getElement().getValue();
	            while (list.after(cursor) != null) { 
	                cursor = list.after(cursor);
	                returnValue += "," + cursor.getElement().getValue();
	            }
	            returnValue += "}";
	            System.out.println(returnValue);
	        } 
	}
    public void printEntry() {
    	Iterator<Entry<K,V>> entries  = list.iterator();
    	String s = "{";
    	while (entries.hasNext()) {
    		Entry<K,V> e = entries.next();
    		s += "(" + e.getKey() +"," + e.getValue() + ")"; 
    	}
    	s += "}";
    	System.out.println(s);
    }
	
	/* Replace a key  of an existing value */
	public void replaceKey(Entry<K, V> input,  K newKey) {
		Iterator<Position<Entry<K,V>>> entries  = list.positions().iterator(); // list.positions returns Iterable; Iterable object using iterator() returns Iterator
		while (entries.hasNext()) { // find the input entry whose key must be updated 
			Position<Entry<K,V>> pos = entries.next(); // get position of the entry
			Entry<K,V> entry = pos.getElement(); // get the entry
			if (entry.getValue() == input.getValue()) { // is it input entry whose key must be updated?
				list.remove(pos); // remove existing entry with old key
				this.insert(newKey, input.getValue());	 // add an entry with new key 
				break; 
			}
		}	
	}
}
