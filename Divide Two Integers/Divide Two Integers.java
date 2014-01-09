public class Solution {
	public int divide(int dividend, int divisor) {
		long a = Math.abs((long) dividend);
		long b = Math.abs((long) divisor);

		int result = 0;
		int factor = 1;
		long bb = b;
		while (a >= b) {
			if (a < bb) {
				bb >>= 1;
				factor >>= 1;
			} else if (a >= bb << 1) {
				bb <<= 1;
				factor <<= 1;
			} else {
				result += factor;
				a -= bb;
			}
		}

		return (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? result : -result;
	}
}