/*
   Given two sorted arrays A, B of size m and n respectively. Find the k-th smallest
   element in the union of A and B. You can assume that there are no duplicate
   elements.
 */
public class Solution {
	public int findKthSmallest(int[] A, int[] B, int k) {
		assert (k > 0);
		assert (k <= A.length + B.length);

		return findKthSmallestRecurrsive(A, 0, A.length - 1, B, 0, B.length - 1, k);
	}

	private int findKthSmallestRecurrsive(int[] A, int aStart, int aEnd, int[] B, int bStart, int bEnd, int k) {
		if (k == 1)
			/*
			 * In case of k is '1' we don't want it fall to the logic at the end, which will 
			 * favor the larger number. We need smaller number when k is '1'. 
			 */
			return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
		else if (aEnd < aStart)
			return B[bStart + k - 1];
		else if (bEnd < bStart)
			return A[aStart + k - 1];

		int n = aEnd - aStart + 1;
		int m = bEnd - bStart + 1;
		/*
		 * k is greater than '1' at this point, we need to make sure to not cut a '0'.
		 * In case of (n=1, m=2, k=2) we will cut a '0'.
		 */
		n = (k * n) / (m + n);
		n = n == 0 ? 1 : n;
		m = k - n;
		int i = aStart + n - 1;
		int j = bStart + m - 1;

		if (A[i] > B[j] && (j == bEnd || A[i] < B[j + 1]))
			return A[i];
		else if (B[j] > A[i] && (i == aEnd || B[j] < A[i + 1]))
			return B[j];

		if (A[i] < B[j])
			/*
			 * Take the example as we just started. We have A[0..i], and B[0..j] (i+1 + j+1 = k).
			 * We'll merge A[0..i] and B[0..j] first. In case A[i] < B[i], A[i] and A[0..i-1] won't
			 * possibly be k-th element - they are too small. B[j] can not be a candidate, provide 
			 * A[0..i] is not whole A[] (case "A[0..i] is whole A[]" is already cleared by previous
			 * logic)
			 */
			return findKthSmallestRecurrsive(A, i + 1, aEnd, B, bStart, j - 1, k - n);

		return findKthSmallestRecurrsive(A, aStart, i - 1, B, j + 1, bEnd, k - m);
	}
}
