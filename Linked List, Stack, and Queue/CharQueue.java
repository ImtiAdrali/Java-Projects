package Assignment3;

public class CharQueue implements CharQueueable{

    public static final int CAPACITY = 100;
    private char[] queueArray;
    private int f = 0;
    private int sz = 0;

    public CharQueue() {
        queueArray = new char[CAPACITY];
    }

    @Override
    public void enqueue(char element) {
        if (sz == queueArray.length) {
            throw new IllegalStateException("Queue is Full");
        }
        int avail = (f + sz) % queueArray.length;
        queueArray[avail] = element;
        ++sz;
    }

    @Override
    public char dequeue() {
        if (isEmpty() || sz == f) {
            throw new IllegalStateException("Queue Empty");
        }
        char answer = queueArray[f];
        queueArray[f] = 0;
        f = (f + 1) % queueArray.length;
        sz--;
        return answer;
    }

    @Override
    public char first() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue Empty");
        }
        return queueArray[f];
    }

    @Override
    public boolean isEmpty() {
        return sz == 0;
    }

    @Override
    public int size() {
        return sz;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        if (isEmpty()) {
            return "{}";
        }else {
            for (int i=0; i<queueArray.length; i++) {
                if (queueArray[i] == 0) {
                    continue;
                }
                builder.append(queueArray[i]);
                builder.append(',');
            }
            builder.append('}');
            builder.deleteCharAt(builder.length()-2);
            return builder.toString();
        }

    }
}
