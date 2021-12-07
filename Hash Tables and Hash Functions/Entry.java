package Assignment6;
public class Entry {
    
    private String k;  // key
    private String v;  // value

    public Entry(String key, String value) {
      k = key;
      v = value;
    }

    // methods of the Entry interface
    public String getKey() { return k; }
    public String getValue() { return v; }

    // utilities not exposed as part of the Entry interface
    protected void setKey(String key) { k = key; }
    protected void setValue(String value) { v = value; }

    public String toString() {
        return getKey() +
                " --> " + getValue();
    }
  
  
}