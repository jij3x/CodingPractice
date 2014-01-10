public class Solution {
	public void merge(int A[], int m, int B[], int n) {
		int cursor = m + n - 1;
		while (cursor >= 0) {
			if (m > 0 && n > 0)
				A[cursor--] = A[m - 1] > B[n - 1] ? A[--m] : B[--n];
			else if (m > 0)
				A[cursor--] = A[--m];
			else
				A[cursor--] = B[--n];
		}
	}
}

class Solution2 {
	public void merge(int A[], int m, int B[], int n) {
		while (n > 0) {
			if (m <= 0 || A[m - 1] < B[n - 1])
				A[n + m - 1] = B[--n];
			else
				A[n + m - 1] = A[--m];
		}
	}
}