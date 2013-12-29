package interview;

/*
 * Second efficient O(2log(m+n)), simpler implementation. findKthSmallest() is still difficult to implement.
 */
public class Solution {
	public double findMedianSortedArrays(int A[], int B[]) {
		int half = Math.min(A.length, B.length) + Math.abs(A.length - B.length) / 2 + 1;
		double a = findKthSmallest(A, 0, A.length - 1, B, 0, B.length - 1, half);

		if ((A.length + B.length) % 2 == 1)
			return a;

		double b = findKthSmallest(A, 0, A.length - 1, B, 0, B.length - 1, half - 1);
		return (a + b) / 2.0;
	}

	private int findKthSmallest(int[] A, int aStart, int aEnd, int[] B, int bStart, int bEnd, int k) {
		if (aEnd < aStart)
			return B[bStart + k - 1];
		else if (bEnd < bStart)
			return A[aStart + k - 1];
		else if (k == 1)
			return A[aStart] < B[bStart] ? A[aStart] : B[bStart];

		int n = aEnd - aStart + 1;
		int m = bEnd - bStart + 1;
		n = (int) ((double) n / (m + n) * k);
		n = n == 0 ? 1 : n;
		m = k - n;
		int i = aStart + n - 1;
		int j = bStart + m - 1;

		if (A[i] >= B[j] && (j == bEnd || A[i] <= B[j + 1]))
			return A[i];
		else if (B[j] >= A[i] && (i == aEnd || B[j] <= A[i + 1]))
			return B[j];

		if (A[i] < B[j])
			return findKthSmallest(A, i + 1, aEnd, B, bStart, j - 1, k - n);

		return findKthSmallest(A, aStart, i - 1, B, j + 1, bEnd, k - m);
	}
}

/*
 * Most efficient O(log(m+n)), but not straightforward - many edge cases, tricky implementation.
 */
class Solution2 {
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
		if (j < 0)
			b = A1[i - 1];
		else if (i == 0)
			b = B1[j];
		else
			b = Math.max(A1[i - 1], B1[j]);

		return (a + b) / 2.0;
	}

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
}

/*
 * The alternative way to achieve O(log(m+n)). Idea is from MIT's 6.046J/18.410J hand out -
 *     Should avoid the original pseudo code - it is unnecessarily complicated, and will involve
 *     even more edge cases.
 *     
 * This algorithm is actually O(log(m)+log(n)), not the fastest. But might be a relatively simple
 *     way to implement binary search for this problem, since only two pointers are involved here.
 */
class Solution3 {
	double findMedianSortedArrays(int A[], int B[]) {
		double[] result = new double[1];

		/*
		 * The range here is [0..A.length-1].
		 * 
		 * However, the optimized range should be:
		 *     left = max(1, (A.length+B.length)/2-B.length) - 1
		 *     right = min(A.length, (A.length+B.length)/2) - 1
		 *
		 * Here is the example:
		 *     Two arrays - A[30], and, B[20]. We'd like to know the median's location (range) in A. 
		 *     The 1st element of A is impossible to be the median, since in the merged (virtual) 
		 *     array C[50], the 1st element of A's latest position is 21st (after entire elements
		 *     from B). Same for A[2...4]. Likewise, A[26...30] can't be median.
		 *     On the other hand, for shorter array, median could be in any position.
		 */
		if (findMedian(A, B, 0, A.length - 1, result))
			return result[0];

		findMedian(B, A, 0, B.length - 1, result);
		return result[0];
	}

	/*
	 * If the median is in A, return 'true'. And set the result. Otherwise, return 'false'.
	 */
	private boolean findMedian(int[] A, int[] B, int aLeft, int aRight, double[] result) {
		if (A.length == 0 || aLeft > aRight)
			return false;

		int i = (aLeft + aRight) / 2;
		int j = ((A.length + B.length) / 2 + 1) - (i + 1) - 1;
		if (j == -1) {
			if (B.length == 0) {
				result[0] = A.length % 2 == 1 ? A[i] : (double) (A[i] + A[i - 1]) / 2.0;
				return true;
			} else if (A[i] <= B[0]) {
				result[0] = (A.length + B.length) % 2 == 1 ? A[i] : (double) (A[i] + A[i - 1]) / 2.0;
				return true;
			} else {
				return findMedian(A, B, aLeft, i - 1, result);
			}
		} else if (j < -1) {
			return findMedian(A, B, aLeft, i - 1, result);
		} else if (j > B.length - 1) {
			return findMedian(A, B, i + 1, aRight, result);
		}

		if (A[i] < B[j]) {
			return findMedian(A, B, i + 1, aRight, result);
		} else if (j < B.length - 1 && A[i] > B[j + 1]) {
			return findMedian(A, B, aLeft, i - 1, result);
		}

		// At this point is, A[i] is the median.
		if ((A.length + B.length) % 2 == 1) {
			result[0] = A[i];
		} else {
			result[0] = (double) (A[i] + (i == 0 ? B[j] : Math.max(A[i - 1], B[j]))) / 2.0;
		}
		return true;
	}
}

/*
 * Simplest way to implement and understand, but most inefficient O((m+n)/2).
 */
class Solution4 {
	public double findMedianSortedArrays(int A[], int B[]) {
		int aIndex = 0;
		int bIndex = 0;
		int previous = 0;
		int current = 0;

		int half = Math.min(A.length, B.length) + Math.abs(A.length - B.length) / 2 + 1;
		for (int count = 1; count <= half; count++) {
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
			return (double) (previous + current) / 2.0;

		return current;
	}
}