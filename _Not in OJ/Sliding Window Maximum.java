/*
 A long array A[] is given to you. There is a sliding window of size w which is moving
 from the very left of the array to the very right. You can only see the w numbers in
 the window. Each time the sliding window moves rightwards by one position.
 Following is an example:
 The array is [1 3 -1 -3 5 3 6 7], and w is 3.

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
  1 [3  -1  -3] 5  3  6  7       3
  1  3 [-1  -3  5] 3  6  7       5
  1  3  -1 [-3  5  3] 6  7       5
  1  3  -1  -3 [5  3  6] 7       6
  1  3  -1  -3  5 [3  6  7]      7

 Input: A long array A[], and a window width w
 Output: An array B[], B[i] is the maximum value of from A[i] to A[i+w-1]
 Requirement: Find a good optimal way to get B[i]
 */
public class Solution {
	class Element {
		int val;
		int idx;

		public Element(int v, int i) {
			val = v;
			idx = i;
		}
	}

	class ElementComparator implements Comparator<Element> {
		@Override
		public int compare(Element e1, Element e2) {
			return e2.val - e1.val;
		}
	}

	/*
	 * O(NlogN)
	 */
	public void maxSlidingWindow(int[] A, int w, int B[]) {
		Comparator<Element> comparator = new ElementComparator();
		PriorityQueue<Element> maxHeap = new PriorityQueue<Element>(A.length, comparator);
		for (int i = 0; i < w; i++) {
			maxHeap.add(new Element(A[i], i));
		}

		for (int i = w; i < A.length; i++) {
			B[i - w] = maxHeap.peek().val;

			/*
			 * We can remove old element by using idx... define equals() and hashCode()
			 *     but, that remove() won't take advantage of heap - running complexity
			 *     will be O(logW). It make this algorithm no difference than the naive
			 *     original way. 
			 * Here, we only pay attention on the largest element. The result is - small
			 *     elements will be accumulated in the heap. And eventually it causes
			 *     the running complexity as O(NlogN)
			 */
			while (maxHeap.peek().idx <= i - w) {
				maxHeap.remove();
			}
			maxHeap.add(new Element(A[i], i));
		}

		B[A.length - w] = maxHeap.peek().val;
	}

	/*
	 * Doubt it's O(N) as claimed.
	 * 
	 * New element will be compared before it's been push into the deque end,
	 *     worst case it will be O(W). The number of comparison before new
	 *     element push into the deque will in [1, w] range, as the deque size
	 *     will fluctuate in this range. And that will give O(W) running time
	 *     complexity.
	 * So, the overall complexity is O(NW). Unfortunately, it's no difference
	 *     than the naive way. It might have some performance gain since it's
	 *     not always compare W elements, but it also introduced some overheads
	 *     like deque operations. Probably, it's a good example of over engineering.  
	 */
	public void maxSlidingWindow2(int[] A, int w, int B[]) {
		Deque<Integer> deque = new LinkedList<Integer>();

		// make elements sorted in deque, largest will be in the first place
		for (int i = 0; i < w; i++) {
			while (!deque.isEmpty() && A[i] >= A[deque.peekLast()]) {
				deque.removeLast();
			}
			deque.addLast(i);
		}

		for (int i = w; i < A.length; i++) {
			B[i - w] = A[deque.peekFirst()];

			// drain old elements
			while (!deque.isEmpty() && deque.peekFirst() <= i - w) {
				deque.removeFirst();
			}

			// drop the small ones before adding new element, to make sure
			// elements are sorted in deque
			while (!deque.isEmpty() && A[i] >= A[deque.peekLast()]) {
				deque.removeLast();
			}
			deque.addLast(i);
		}

		B[A.length - w] = A[deque.peekFirst()];
	}
}
