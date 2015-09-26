public class ZigzagIterator {
    private Iterator<Integer> i1, i2;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i2 = v1.iterator();
        i1 = v2.iterator();
    }

    public int next() {
        if (i2.hasNext()) {
            Iterator<Integer> tmp = i1;
            i1 = i2;
            i2 = tmp;
        }
        return i1.next();
    }

    public boolean hasNext() {
        return i1.hasNext() || i2.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */