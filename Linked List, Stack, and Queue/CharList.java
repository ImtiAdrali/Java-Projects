 package Assignment3;

public class CharList implements CharListable {

    private Node head;
    private Node tail;
    private int size = 0;



    public void addToHead(char data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
            size++;
        }
    }

    public void addToTail(char data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            size++;
        }else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            newNode.next = null;
            size++;
        }
    }

    public char remove() {
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

    public boolean isEmpty() {
        return head == null;
    }

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

    /** Node class definition (inner class) **/

    private class Node {

        private Node prev;
        private Node next;
        private char data;


        private Node(char data) {

            this.data = data;
        }
    }
}
