public class Solution {
	public int divide(int dividend, int divisor) {
		if (dividend == 0 || divisor == 0)
			return 0;

		int result = 0;

		/*
		 * Flip both dividend and divisor to negative to avoid overflow, as
		 * 'int' range is [-2,147,483,648 to 2,147,483,647].
		 */
		boolean returnNeg = (dividend < 0 && divisor > 0)
				|| (dividend > 0 && divisor < 0) ? true : false;
		dividend = dividend < 0 ? dividend : 0 - dividend;
		divisor = divisor < 0 ? divisor : 0 - divisor;

		int factor = 1;
		/*
		 * 'divisor' will be increasing exponentially, we need a backup divisor
		 * to determine when to stop. Also, since both dividend and div are
		 * negative here, stop when dividend 'greater than' div.
		 */
		long div = divisor;
		while (dividend <= div) {
			if (divisor < dividend && factor > 1) {
				divisor >>= 1;
				factor >>= 1;
				continue;
			}

			/*
			 * Increase divisor by factor 2. Stop increasing when it reaches to
			 * half of MIN_VALUE, otherwise it will overflow later when it
			 * doubles.
			 */
			while ((dividend <= (divisor << 1))
					&& (Integer.MIN_VALUE >> 1 < divisor)) {
				divisor <<= 1;
				factor <<= 1;
			}
			result += factor;
			dividend -= divisor;
		}

		return returnNeg ? 0 - result : result;
	}
}
