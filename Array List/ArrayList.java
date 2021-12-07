package Assignment4;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayList implements List{

    public static final int CAPACITY = 16;
    protected int[] data;
    protected int size = 0;

    public ArrayList() {
        this.data = new int[CAPACITY];
    }

    public ArrayList(int capacity) {
        // your code here
        this.data = new int[capacity];
    }

    public int size() {
        // your code here
        return size; // stub code
    }

    public boolean isEmpty() {
        // your code here
        return size == 0; // stub code
    }

    public int get(int i) throws IndexOutOfBoundsException {
        // your code here
        checkIndex(i, size);
        return data[i]; // stub code
    }

    public int set(int i, int e) throws IndexOutOfBoundsException {
        // your code here
        checkIndex(i, size);
        int answer = data[i];
        data[i] = e;
        return answer; // stub code
    }

    public void add(int i, int e) throws IndexOutOfBoundsException, IllegalStateException {
        // your code here
        checkIndex(i, size + 1);
        if (size == data.length) {
            //throw new IllegalStateException("Array is full");
            increaseCapacity();
        }

        for (int j=size - 1; j >= i; j--) {
            data[j + 1] = data[j];
        }
        data[i] = e;
        size++;
    }

    public int remove(int i) throws IndexOutOfBoundsException {
        // your code here
        checkIndex(i, size);
        int temp = data[i];
        for (int k=i; k< size(); k++) {
            data[k] = data[k + 1];
        }

        data[size - 1] = 0;
        minimize();
        size--;

        return temp; // stub code
    }

    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        // your code here
        if (i < 0 || i >=n) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }
    
    public void increaseCapacity() throws IllegalStateException {
        // your code here
        int[] temValues = null;
        if (size == data.length) {
            temValues = new int[data.length * 2];
            for (int i=0; i<size; i++) {
                temValues[i] = data[i];
            }
            data = temValues;
        }

    }

    public void minimize() throws IllegalStateException {
        // your code here
        int[] tempValue = null;
        if (size > 0) {
            tempValue = new int[size];
            for (int i=0; i<size; i++) {
                tempValue[i] = data[i];
            }
        }
        data = tempValue;
    }

    public int capacity() throws IllegalStateException {
        // your code here

        return data.length;
    }



    public int getFirstIndex() {
        return 0;
    }
    public int getLastindex() {
        return data.length - 1;
    }








    public void quickSort(int low, int high){
        if (low > high)
            return;

        int p = partition(low, high);
        quickSort(low, p - 1);
        quickSort(p + 1, high);

    }


    public int partition(int low, int high) {
        int pivot = data[high];
        int temp;
        int i = low -1;
        for (int j=low; j < high; j++) {
            if (data[j] != 0) {
                if (data[j] <= pivot) {
                    i++;
                    temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }else {
                continue;
            }
        }
        temp = data[i + 1];
        data[i + 1] = data[high];
        data[high] = temp;
        return (i + 1);
    }

}