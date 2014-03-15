public class Solution {
	public int removeDuplicates(int[] A) {
		if (A.length <= 0)
			return A.length;

		int newLength = 1;
		for (int i = 1; i < A.length; i++) {
			if (A[i] != A[i - 1])
				A[newLength++] = A[i];
		}
		return newLength;
	}
}