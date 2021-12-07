package Assignment3;

public class QueuUsingDLL implements CharQueueable{
    private Node head;
    private Node tail;
    private int front = 0;
    private int  rear = 0;
    private int size = 0;

    @Override
    public void enqueue(char element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            head =newNode;
            tail =newNode;
            size++;
        }else {
            int avail = (front + size) % size;
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            newNode.next = null;
            size++;
        }
    }

    @Override
    public char dequeue() {
       Node ptr;
        if (isEmpty() || head == null) {
            throw new IllegalStateException("IT is empty");
        }else {
            ptr = head;
            head = ptr.next;
            size--;
        }
        return ptr.data;
    }

    @Override
    public char first() {
        return head.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public String toString() {
        if (isEmpty()) return "{}";
        else {
            Node currentNode = head;
            String returnValue = "{" + currentNode.data;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
                returnValue += "," + currentNode.data;
            }
            returnValue += "}";
            return returnValue;
        }
    }

    private class Node{
        private char data;
        private Node next;
        private Node prev;

        public Node(char data) {
            this.data = data;
        }
    }
}
