package Assignment3;

public class StackUsingDLL implements CharStackable{
    private int size = 0;
    private Node top;
    private Node head;
    private Node tail;

    @Override
    public void push(char element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
            top = newNode;
            size++;
        }
    }

    @Override
    public char pop() {
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
    public char top() {
        if (isEmpty()) {
            throw new IllegalStateException("The stack is empty");
        }
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    static boolean isOperator(char ch){
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            return true;
        }
        return false;
    }

    @Override
    public double postFix(String exp) {
        StackUsingDLL fix = new StackUsingDLL();

        int value1 = 0;
        int value2 = 0;
        for (int i = 0; i<exp.length(); i++) {
            char ch = exp.charAt(i);
            if (Character.isDigit(ch)) {
                fix.push((char) (ch - '0'));
            }else if (!isOperator(ch)) {
                continue;
            } else{
                try {
                    value1 = fix.pop();
                    value2 = fix.pop();
                }catch (IllegalStateException e) {
                    e.getMessage();
                }
                switch (ch) {
                    case '+':
                        fix.push((char) (value2 + value1));
                        break;
                    case '-':
                        fix.push((char) (value2 - value1));
                        break;
                    case '*':
                        fix.push((char) (value2 * value1));
                        break;
                    case '/':
                        try {
                            if (value1 == 0) {
                                System.out.println("Not divisible by 0");
                                continue;
                            }
                            fix.push((char) (value2 / value1));
                        }catch (ArithmeticException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        continue;
                }
            }
        }
        return fix.pop();
    }

    static int Precedence(char ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    String infixToPost(String exp)
    {
        String output = new String("");
        StackUsingDLL stack = new StackUsingDLL();

        for (int i = 0; i<exp.length(); ++i) {
            char c = exp.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                output += c;
            } else if (c == '(') {
                stack.push(c);
            }else if (c == ')') {
                while (!stack.isEmpty() &&
                        stack.top() != '(')
                    output += stack.pop();

                stack.pop();
            }else {
                while (!stack.isEmpty() && Precedence(c) <= Precedence(stack.top())){
                    output += stack.pop();
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()){
            if(stack.top() == '(')
                return "Invalid Expression";
            output += stack.pop();
        }
        return output;
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

    private class Node {
        private char data;
        private Node next;
        private Node prev;

        public Node(char data) {
            this.data = data;
        }
    }
}
