public class Solution {
	public int divide(int dividend, int divisor) {
		if (dividend == 0 || divisor == 0)
			return 0;

		int result = 0;
		long a = dividend, b = divisor;
		boolean returnNeg = (a < 0 && b > 0) || (a > 0 && b < 0) ? true : false;
		a = a < 0 ? 0 - a : a;
		b = b < 0 ? 0 - b : b;

		int factor = 1;
		long div = b;
		while (a >= div) {
			if (a >= (b << 1)) {
				b <<= 1;
				factor <<= 1;
			} else if (b > a && factor > 1) {
				b >>= 1;
				factor >>= 1;
				continue;
			}

			if (a - b >= 0)
				result += factor;
			a -= b;
		}

		return (int) (returnNeg ? 0 - result : result);
	}
}
