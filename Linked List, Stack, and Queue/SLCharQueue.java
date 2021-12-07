package Assignment3;

public class SLCharQueue implements CharQueueable {
    private int size = 0;
    private Node front;
    private Node rear;
    private Node head;
    private Node tail;

    @Override
    public void enqueue(char element) {
        Node newNode = new Node(element);
        newNode.next = null;

        if (head == null) {
            head = newNode;
            size++;
        }else {
            Node lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
            size++;
        }
    }

    @Override
    public char dequeue() {
       if (isEmpty()) {
           throw new IllegalStateException("The queue is Empty");
       }
       Node answer = head;
       head = head.next;
       size--;
       return answer.data;
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
        String output = "";
        if (isEmpty()) {
            return "{}";
        }else {
            Node currentNode = head;
            output += "{" + currentNode.data;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
                output += "," + currentNode.data;
            }
            output += "}";
        }
        return output.toString();
    }

    class Node{
        public Node next;
        private char data;

        public Node(char data) {
            this.data = data;
        }
    }

}



