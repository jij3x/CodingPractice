public class MedianFinder {

    private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(),
            maxHeap = new PriorityQueue<Integer>(1, Collections.reverseOrder());

    // Adds a number into the data structure.
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (minHeap.size() > maxHeap.size())
            maxHeap.offer(minHeap.poll());
    }

    // Returns the median of current data stream
    public double findMedian() {
        return (maxHeap.size() > minHeap.size()) ? maxHeap.peek()
                : (maxHeap.size() < minHeap.size()) ? minHeap.peek() : (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
};
