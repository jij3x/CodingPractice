public class Solution {
	public int singleNumber(int[] A) {
		int[] digits = new int[32];
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < A.length; j++) {
				digits[i] += (A[j] >> i) & 1;
			}
		}

		int result = 0;
		for (int i = 31; i >= 0; i--) {
			if (digits[i] % 3 != 0)
				result += 1;

			if (i != 0)
				result <<= 1;
		}
		return result;
	}
}
