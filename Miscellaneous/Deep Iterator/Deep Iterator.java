public class DeepIteratorTest {
    public static void main(String[] args) {
        ArrayList list3 = new ArrayList();
        list3.add(5);
        list3.add(6);

        ArrayList list2 = new ArrayList();
        list2.add(3);
        list2.add(4);
        list2.add(list3);

        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(list2);
        list1.add(7);
        list1.add(8);

        DeepIterator itr = new DeepIterator(list1);
        while (itr.hasNext())
            System.out.println(itr.next());

    }
}

class DeepIterator implements Iterator {
    private Stack<Iterator> stack;

    public DeepIterator(Iterable in) {
        stack = new Stack<Iterator>();
        stack.push(in.iterator());
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty())
            return false;

        Iterator itr = stack.peek();
        if (!itr.hasNext()) {
            stack.pop();
            return hasNext();
        }
        return true;
    }

    @Override
    public Object next() {
        if (!hasNext())
            return null;

        Iterator itr = stack.peek();
        Object obj = itr.next();
        if (obj instanceof Iterable) {
            stack.push(((Iterable) obj).iterator());
            return next();
        }
        return obj;
    }
}
