// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    private Integer buf;
    private Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        buf = null;
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        buf = buf == null ? iterator.next() : buf;
        return buf;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        buf = buf == null ? iterator.next() : buf;
        int rt = buf;
        buf = null;
        return rt;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext() || buf != null;
    }
}