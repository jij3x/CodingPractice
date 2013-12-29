public class Solution {
	class Result {
		int arrayNo;
		int arrayIdx;

		Result(int no, int idx) {
			arrayNo = no;
			arrayIdx = idx;
		}
	}

	private Result findKthSmallest(int[] A, int aStart, int aEnd, int[] B, int bStart, int bEnd, int k) {
		if (aEnd < aStart)
			return new Result(1, bStart + k - 1);
		else if (bEnd < bStart)
			return new Result(0, aStart + k - 1);
		else if (k == 1)
			return A[aStart] < B[bStart] ? new Result(0, aStart) : new Result(1, bStart);

		int n = aEnd - aStart + 1;
		int m = bEnd - bStart + 1;
		n = (int) ((double) n / (m + n) * k);
		n = n == 0 ? 1 : n;
		m = k - n;
		int i = aStart + n - 1;
		int j = bStart + m - 1;

		if (A[i] >= B[j] && (j == bEnd || A[i] <= B[j + 1]))
			return new Result(0, i);
		else if (B[j] >= A[i] && (i == aEnd || B[j] <= A[i + 1]))
			return new Result(1, j);

		if (A[i] < B[j])
			return findKthSmallest(A, i + 1, aEnd, B, bStart, j - 1, k - n);

		return findKthSmallest(A, aStart, i - 1, B, j + 1, bEnd, k - m);
	}

	/*
	 * Most efficient O(log(m+n)), but not straightforward - many edge cases, tricky implementation.
	 */
	public double findMedianSortedArrays(int A[], int B[]) {
		Result result = findKthSmallest(A, 0, A.length - 1, B, 0, B.length - 1,
				Math.min(A.length, B.length) + Math.abs(A.length - B.length) / 2 + 1);
		int[] A1 = result.arrayNo == 0 ? A : B;
		int[] B1 = result.arrayNo == 0 ? B : A;

		if ((A1.length + B1.length) % 2 == 1)
			return A1[result.arrayIdx];

		int i = result.arrayIdx;
		int j = (A1.length + B1.length) / 2 - (i + 1);
		double a = A1[i], b = 0;
		if (j < 0) {
			b = A1[i - 1];
		} else if (i == 0) {
			b = B1[j];
		} else {
			b = Math.max(A1[i - 1], B1[j]);
		}

		return (a + b) / 2.0;
	}

	/*
	 * Second efficient O(2log(m+n)), simpler implementation. findKthSmallest() is still tricky to implement. 
	 */
	public double findMedianSortedArrays2(int A[], int B[]) {
		Result result = findKthSmallest(A, 0, A.length - 1, B, 0, B.length - 1,
				Math.min(A.length, B.length) + Math.abs(A.length - B.length) / 2 + 1);
		double x = result.arrayNo == 0 ? A[result.arrayIdx] : B[result.arrayIdx];
		if ((A.length + B.length) % 2 == 1)
			return x;

		result = findKthSmallest(A, 0, A.length - 1, B, 0, B.length - 1, (A.length + B.length) / 2);
		double y = result.arrayNo == 0 ? A[result.arrayIdx] : B[result.arrayIdx];
		return (x + y) / 2.0;
	}

	/*
	 * Sounds little easier for implementation (since we only need to maintain two pointers here),
	 * but very hard to make sense out of it at first place! And also, not very efficient - O(log(n)+log(m))
	 * 
	 * Note, below code is not actually working. It's here just for concept demonstration. And don't feel
	 * want to spend more time on tricky cases, such as, one empty array.
	 * 
	 * Overall, should avoid using this approach in special event.
	 */
	double findMedianSortedArrays3(int A[], int B[]) {
		int[] A1 = A.length > B.length ? A : B;
		int[] B1 = A.length > B.length ? B : A;
		return findMedian(A1, B1, Math.max(1, (A1.length + B1.length) / 2 - B1.length),
				Math.min(A1.length, (A1.length + B1.length) / 2));
	}

	private double findMedian(int[] A, int[] B, int aLeft, int aRight) {
		/*
		 * Work on one array (A) first. If the median was not there (aLeft will exceed aRight),
		 * move to the next array (B).
		 * So, we do binary serach on A first, then on B. That gives O(log(n)+log(m)).
		 */
		if (aLeft > aRight) {
			/* 
			 * 'left' and 'right' 1-based number, like the array length.
			 * 
			 * Here is the example why their initial value should like below logic:
			 *     Two arrays - A[30], and, B[20]. We'd like to know the median's location (range) in A. 
			 *     The first element in A is not possible be median, since in the virtual merged
			 *     array C[50], the first element of A's latest position is 21st (after entire B). Same
			 *     for A[2...4]. Likewise, A[26...30] can't be median.  
			 */
			int bLeft = Math.max(1, (A.length + B.length) / 2 - A.length);
			int bRight = Math.min(B.length, (A.length + B.length) / 2);
			return findMedian(B, A, bLeft, bRight);
		}

		/*
		 * Get proposed index for two array. It's very subtle that 'i' will be that larger middle element -
		 * If two arrays' total length is odd, i could be the median. If total length is even, 'i' will point
		 * to the larger middle element. Why? Because 'i' is taken from 1-based 'left' and 'right'.
		 */
		int i = (aLeft + aRight) / 2;
		int j = (A.length + B.length) / 2 - i - 1;

		if (j >= 0 && A[i] < B[j])
			return findMedian(A, B, i + 1, aRight);
		if (j < B.length - 1 && A[i] > B[j + 1])
			return findMedian(A, B, aLeft, i - 1);

		if ((A.length + B.length) % 2 == 1)
			return A[i];
		if (i <= 0)
			return (double) (A[i] + B[j]) / 2.0;

		return (double) (A[i] + Math.max(B[j], A[i - 1])) / 2.0;
	}

	/*
	 * Simplest way to implement and understand, but most inefficient O((m+n)/2).
	 */
	public double findMedianSortedArrays4(int A[], int B[]) {
		int aIndex = 0;
		int bIndex = 0;
		int count = 0;
		int previous = 0;
		int current = 0;

		while (count++ <= Math.min(A.length, B.length) + Math.abs(A.length - B.length) / 2) {
			previous = current;
			if (aIndex < A.length && bIndex < B.length) {
				if (A[aIndex] < B[bIndex])
					current = A[aIndex++];
				else
					current = B[bIndex++];
			} else if (aIndex == A.length) {
				current = B[bIndex++];
			} else {
				current = A[aIndex++];
			}
		}

		if ((A.length + B.length) % 2 == 0)
			return ((double) previous + (double) current) / 2;

		return current;
	}
}