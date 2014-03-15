public class Solution {
	public int removeDuplicates(int[] A) {
		if (A.length <= 2)
			return A.length;

		int newLength = 2;
		for (int i = 2; i < A.length; i++) {
			if (A[i] != A[newLength - 2])
				A[newLength++] = A[i];
		}
		return newLength;
	}
}