package Assignment3;

public class CharStack implements CharStackable{
    public static final int CAPACITY = 100;
    private char[] charArray;
    private int top;
    private int size;


    public CharStack() {
        this.charArray = new char[CAPACITY];

    }

    @Override
    public void push(char element) throws IllegalStateException {
        if(top == CAPACITY -1) {
            throw new IllegalStateException("Stack is full");
        }
        charArray[top++] = element;
        size++;
    }

    @Override
    public char pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        char e = charArray[--top];
        charArray[top] = 0;
        size--;
        return e;
    }

    @Override
    public char top() {
        if (isEmpty()) {
            throw new IllegalStateException("TStack is empty");
        }
        return charArray[top - 1];
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
        CharStack fix = new CharStack();

        int value1 = 0;
        int value2 = 0;
        for (int i = 0; i<exp.length(); i++) {
            char ch = exp.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
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

    String inToPost(String exp)
    {
        String output = new String("");
        CharStack stack = new CharStack();

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
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        if (isEmpty()) {
            return "{}";
        }else {
            for (int i=charArray.length - 1; i>= 0; i--) {
                if (charArray[i] == 0) {
                    continue;
                }
                builder.append(charArray[i]);
                if (i<top) {
                    builder.append(',');
                }
            }
            builder.append('}');
            builder.deleteCharAt(builder.length() - 2);
            return builder.toString();
        }

    }

}
