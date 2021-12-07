package Assignment6;

import java.util.concurrent.TimeUnit;

public class HashTable {
    private ArrayList<Entry> table;
    private static int numCol = 0;
    private Entry DEFUNCT = new Entry(null, null); // sentinel for deleted item

    public HashTable(int capacity){
        table = new ArrayList<Entry>(capacity);

    }

    //Returns true if location is either empty or the ” defunct” sentinel
    public boolean isAvailable(int j) {
        return (table.get(j) == null || table.get(j).equals(DEFUNCT));
    }

    public Entry getDEFUNCT(){
        return this.DEFUNCT;
    }
    public int getNumCol() {
        return numCol;
    }

    // compute and return hash code given the key
    private int computeHashCode(String key){
        int h1 = 0;
        int keyLength = key.length();
        for (int i=0; i<keyLength; i++) {
            int keyAscii = key.charAt(i);
            h1 += keyAscii;
        }

        return h1;
    }

    // compute and return the compressed hash index
    public int compressHashCode(String key){
        int hashCode = computeHashCode(key);
        return hashCode % table.getCapacity();
    }


    // return the index for a key.


    // return the value associated with key K
    public String tableSearch(String K){
        int i = compressHashCode(K);
        while (!table.get(i).getKey().equals(null)) {
            if (table.get(i).getKey().equals(K)) {
                return table.get(i).getValue();
            }
            i = (i + 1) % table.getCapacity();
        }
        return null;
    }

    // inserts the value associated with key K
    public String tableInsert(String K, String value) {
        Entry e = new Entry(K, value);
        int temp = compressHashCode(K);
        int i=temp;
        do {
            if (isAvailable(i)) {
                table.add1(i, e);
                //numElement++;
                return table.get(i).getValue();

            }
            if (table.get(i).getKey().equals(K)) {
                table.get(i).setValue(value);
                return table.get(i).getValue();
            }
            numCol++;
            i = (i + 1) % table.getCapacity();

        }while (i != temp);
        return null;
    }

    //remove the value associated with key K
    public String tableRemove(String K) {
        String answer = "";
        int i = compressHashCode(K);
        while (!table.get(i).getKey().equals(null)) {
            if (table.get(i).getKey().equals(K)) {
                answer += table.get(i).getValue();
                table.add1(i, new Entry("null", "null"));
                return answer;
            }
            i = (i + 1) % table.getCapacity();
        }
        return null;
    }

    //print the content of the table
    public void tablePrint() throws InterruptedException {
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%13s %10s %40s\n", "Indexes", "ID", "Entries");
        System.out.println("-----------------------------------------------------------------------");
        for (int i=0; i<table.getCapacity(); i++) {
            if (table.get(i) == null)
                continue;
//            System.out.println((i) + ": "+ table.get(i));
            System.out.printf("%10s %15s %40s\n", i, table.get(i).getKey(), table.get(i).getValue());
//            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("-----------------------------------------------------------------------");
    }


}
