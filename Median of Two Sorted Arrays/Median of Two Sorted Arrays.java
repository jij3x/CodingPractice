/*
 * O(2*(log(m)+log(n)))
 */
public class Solution {
	public double findMedianSortedArrays(int A[], int B[]) {
		double higherMedian = findKthSmallest(A, 0, A.length, B, 0, B.length, (A.length + B.length) / 2 + 1);
		if ((A.length + B.length) % 2 == 1)
			return higherMedian;

		double lowerMedian = findKthSmallest(A, 0, A.length, B, 0, B.length, (A.length + B.length) / 2);
		return (lowerMedian + higherMedian) / 2.0;
	}

	private int findKthSmallest(int[] A, int as, int al, int[] B, int bs, int bl, int k) {
		if (al <= 0)
			return B[bs + k - 1];
		if (bl <= 0)
			return A[as + k - 1];
		if (k == 1)
			return Math.min(A[as], B[bs]);

		int m = Math.max(1, (int) ((double) al / (al + bl) * k));
		int n = k - m;
		if (A[as + m - 1] >= B[bs + n - 1] && (n == bl || A[as + m - 1] <= B[bs + n]))
			return A[as + m - 1];
		else if (B[bs + n - 1] >= A[as + m - 1] && (m == al || B[bs + n - 1] <= A[as + m]))
			return B[bs + n - 1];

		if (A[as + m - 1] < B[bs + n - 1])
			// exclude A[as+m-1] and below portion, also, exclude B[bs+n-1] and above portion
			return findKthSmallest(A, as + m, al - m, B, bs, n - 1, k - m);

		// exclude B[bs+n-1] and below portion, also, exclude A[as+m-1] and above portion
		return findKthSmallest(A, as, m - 1, B, bs + n, bl - n, k - n);
	}
}

/*
 * Most efficient O(log(m)+log(n)), but not straightforward - many edge cases, tricky implementation.
 */
class Solution2 {
	public double findMedianSortedArrays(int A[], int B[]) {
		Result result = findKthSmallest(A, 0, A.length, B, 0, B.length, (A.length + B.length) / 2 + 1);
		int[] A1 = result.arrayNo == 0 ? A : B;
		int[] B1 = result.arrayNo == 0 ? B : A;

		if ((A1.length + B1.length) % 2 == 1)
			return A1[result.arrayIdx];

		int i = result.arrayIdx;
		int j = (A1.length + B1.length) / 2 - (i + 1);
		double a = A1[i], b = 0;
		if (j < 0)
			b = A1[i - 1];
		else
			b = i == 0 ? B1[j] : Math.max(A1[i - 1], B1[j]);

		return (a + b) / 2.0;
	}

	class Result {
		int arrayNo, arrayIdx;

		Result(int no, int idx) {
			arrayNo = no;
			arrayIdx = idx;
		}
	}

	private Result findKthSmallest(int[] A, int as, int al, int[] B, int bs, int bl, int k) {
		if (al <= 0)
			return new Result(1, bs + k - 1);
		if (bl <= 0)
			return new Result(0, as + k - 1);
		if (k == 1)
			return A[as] < B[bs] ? new Result(0, as) : new Result(1, bs);

		int m = Math.max(1, (int) ((double) al / (al + bl) * k));
		int n = k - m;
		if (A[as + m - 1] >= B[bs + n - 1] && (n == bl || A[as + m - 1] <= B[bs + n]))
			return new Result(0, as + m - 1);
		else if (B[bs + n - 1] >= A[as + m - 1] && (m == al || B[bs + n - 1] <= A[as + m]))
			return new Result(1, bs + n - 1);

		if (A[as + m - 1] < B[bs + n - 1])
			return findKthSmallest(A, as + m, al - m, B, bs, n - 1, k - m);

		return findKthSmallest(A, as, m - 1, B, bs + n, bl - n, k - n);
	}
}

/*
 * The alternative way to achieve O(log(m)+log(n)). Idea is from MIT's 6.046J/18.410J hand out -
 *     Should avoid the original pseudo code - it is unnecessarily complicated, and will involve
 *     even more boundary cases.
 */
class Solution3 {
	double findMedianSortedArrays(int A[], int B[]) {
		double[] result = new double[1];

		/*
		 * The initial range here is [0..A.length-1].
		 * 
		 * However, the optimized initial range should be:
		 *     left = max(1, (A.length+B.length)/2-B.length) - 1)
		 *     right = min(A.length, (A.length+B.length)/2) - 1)
		 * 
		 * Here is the example: Two arrays - A[30], and, B[20]. We'd like to know the median's 
		 *     location (range) in A. The 1st element of A is impossible to be the median, since
		 *     in the merged (virtual) array C[50], the 1st element of A's latest position is
		 *     21st (after entire elements from B). Same for A[2...4]. Likewise, A[26...30] can't
		 *     be median. On the other hand, for shorter array, median could be in any position.
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
		if (A.length == 0 || aLeft > aRight) {
			return false;
		}
		if (B.length == 0) {
			int al = A.length;
			result[0] = al % 2 == 1 ? A[al / 2] : (double) (A[al / 2] + A[al / 2 - 1]) / 2.0;
			return true;
		}

		int m = (aLeft + aRight + 1) / 2 + 1;
		int n = ((A.length + B.length) / 2 + 1) - m;
		if (n < 0 || (n < B.length && A[m - 1] > B[n])) {
			return findMedian(A, B, aLeft, m - 2, result);
		}
		if (n > B.length || (n > 0 && A[m - 1] < B[n - 1])) {
			return findMedian(A, B, m, aRight, result);
		}

		// At this point is, A[m-1] is the median. Need to find the lower median in case of odd total number.
		if ((A.length + B.length) % 2 == 1) {
			result[0] = A[m - 1];
		} else {
			if (n == 0)
				result[0] = (double) (A[m - 1] + A[m - 2]) / 2.0;
			else if (m == 1)
				result[0] = (double) (A[m - 1] + B[n - 1]) / 2.0;
			else
				result[0] = (double) (A[m - 1] + Math.max(A[m - 2], B[n - 1])) / 2.0;
		}
		return true;
	}
}

/*
 * Simplest way
 */
class Solution4 {
	public double findMedianSortedArrays(int A[], int B[]) {
		double higherMedian = findKthSmallest(A, 0, A.length, B, 0, B.length, (A.length + B.length) / 2 + 1);
		if ((A.length + B.length) % 2 == 1)
			return higherMedian;

		double lowerMedian = findKthSmallest(A, 0, A.length, B, 0, B.length, (A.length + B.length) / 2);
		return (lowerMedian + higherMedian) / 2.0;
	}

	private int findKthSmallest(int A[], int as, int al, int B[], int bs, int bl, int k) {
		if (al <= 0)
			return B[bs + k - 1];
		if (bl <= 0)
			return A[as + k - 1];
		if (k == 1)
			return Math.min(A[as], B[bs]);

		int n = al < bl ? Math.min(k / 2, al) : k - Math.min(k / 2, bl);
		int m = k - n;
		if (A[as + n - 1] <= B[bs + m - 1])
			return findKthSmallest(A, as + n, al - n, B, bs, bl, k - n);

		return findKthSmallest(A, as, al, B, bs + m, bl - m, k - m);
	}
}
