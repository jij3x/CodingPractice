public class Solution {
	public int divide(int dividend, int divisor) {
		if (dividend == 0 || divisor == 0)
			return 0;

		int result = 0;
		boolean returnNeg = (dividend < 0 && divisor > 0)
				|| (dividend > 0 && divisor < 0) ? true : false;
		dividend = dividend < 0 ? dividend : 0 - dividend;
		divisor = divisor < 0 ? divisor : 0 - divisor;

		int factor = 1;
		long div = divisor;
		while (dividend <= div) {
			if (divisor < dividend && factor > 1) {
				divisor >>= 1;
				factor >>= 1;
				continue;
			}

			while ((dividend <= (divisor << 1))
					&& (Integer.MIN_VALUE >> 2 < divisor)) {
				divisor <<= 1;
				factor <<= 1;
			}
			result += factor;
			dividend -= divisor;
		}

		return returnNeg ? 0 - result : result;
	}
}
