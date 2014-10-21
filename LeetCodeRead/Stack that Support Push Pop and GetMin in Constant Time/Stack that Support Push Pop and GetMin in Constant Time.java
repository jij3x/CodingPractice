public class StackGetMin {
    private Stack<Integer> elements;
    private Stack<Integer> minStack;

    public StackGetMin() {
        elements = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int x) {
        elements.push(x);
        if (minStack.isEmpty() || x <= minStack.peek())
            minStack.push(x);
    }

    public int pop() {
        if (elements.isEmpty())
            throw new EmptyStackException();

        int result = elements.pop();
        if (minStack.peek() == result)
            minStack.pop();
        return result;
    }

    public int getMin() {
        if (minStack.isEmpty())
            throw new EmptyStackException();

        return minStack.peek();
    }
}
