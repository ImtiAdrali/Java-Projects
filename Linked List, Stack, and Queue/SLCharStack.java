package Assignment3;

public class SLCharStack implements CharStackable{

    private int size = 0;
    private Node top;
    private Node head;
    private Node tail;

    @Override
    public void push(char element) {
        Node oldNode = head;
        head = new Node(element);
        head.next = oldNode;
        oldNode = top;
        size++;

    }
    @Override
    public char pop() {
        if (head == null) {
            throw new IllegalStateException("The stack is empty");
        }
        char answer = head.data;
        head = head.next;
        size--;
        return answer;
    }

    @Override
    public char top() {
        if (isEmpty()) {
            throw new IllegalStateException("The stack is empty");
        }
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

    @Override
    public double postFix(String exp) {
        return 0;
    }

    public String toString() {
        String output;
        if (isEmpty()) {
            return "{}";
        }else {
            Node currentNode = head;
            output = "{" + currentNode.data;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
                output += "," + currentNode.data;
            }
            output += "}";
        }
        return output;

    }


    private class Node {
        private char data;
        private Node next;

        public Node(char data) {
            this.data = data;
        }
    }
}
