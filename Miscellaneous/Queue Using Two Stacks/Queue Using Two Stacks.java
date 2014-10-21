public class specialQueue<E> {
    private Stack<E> inbox;
    private Stack<E> outbox;

    public specialQueue() {
        inbox = new Stack<E>();
        outbox = new Stack<E>();
    }

    public void enqueue(E e) {
        inbox.push(e);
    }

    public E dequeue() {
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty())
                outbox.push(inbox.pop());
        }

        return outbox.pop();
    }
}
