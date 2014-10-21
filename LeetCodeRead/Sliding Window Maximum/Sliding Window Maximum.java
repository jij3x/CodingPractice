public class Solution {
    void maxSlidingWindow(int A[], int w, int B[]) {
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < w; i++) {
            while (!deque.isEmpty() && A[i] >= A[deque.getLast()])
                deque.removeLast();
            deque.addLast(i);
        }

        for (int i = w; i < A.length; i++) {
            B[i - w] = deque.getFirst();
            while (!deque.isEmpty() && A[i] >= A[deque.getLast()])
                deque.removeLast();
            deque.addLast(i);

            while (deque.getFirst() <= i - w)
                deque.removeFirst();
        }
        B[A.length - w] = A[deque.getFirst()];
    }
}

class Solution2 {
    class Node {
        int val, idx;

        Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    void maxSlidingWindow(int A[], int w, int B[]) {
        PriorityQueue<Node> heap = new PriorityQueue<Node>();
        for (int i = 0; i < w; i++)
            heap.add(new Node(A[i], i));

        for (int i = w; i < A.length; i++) {
            B[w - i] = heap.poll().val;
            heap.add(new Node(A[i], i));
            while (heap.peek().idx < i - w)
                heap.poll();
        }
        B[A.length - w] = heap.peek().val;
    }
}
