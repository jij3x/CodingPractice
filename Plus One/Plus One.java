public class Solution {
	public int[] plusOne(int[] digits) {
		int[] result = new int[digits.length];

		int carry = 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			result[i] = digits[i] + carry;
			if (result[i] == 10) {
				result[i] = 0;
				carry = 1;
			} else {
				carry = 0;
			}
		}

		if (carry == 0)
			return result;

		int[] r = new int[result.length + 1];
		System.arraycopy(result, 0, r, 1, result.length);
		r[0] = 1;
		return r;
	}
}
