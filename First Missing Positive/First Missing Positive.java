public class Solution {
	public int firstMissingPositive(int[] A) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] != i + 1) {
				while (A[i] > 0 && A[i] <= A.length) {
					int j = A[i] - 1;
					if (A[i] == A[j])
						break;
					A[i] = A[j];
					A[j] = j + 1;
				}
			}
		}

		for (int i = 0; i < A.length; i++) {
			if (A[i] != i + 1)
				return i + 1;
		}

		return A.length + 1;
	}
}
