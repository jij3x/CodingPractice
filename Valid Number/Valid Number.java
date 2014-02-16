public class Solution {
	public boolean isNumber(String s) {
		s = s.trim();

		/*
		 *     | +/- | 0-9 | e/E | .
		 *  ---+-----+-----+-----+----
		 *   0 |  1  |  2  | -1  |  3
		 *   1 | -1  |  2  | -1  |  3
		 *   2 | -1  |  2  |  5  |  4
		 *   3 | -1  |  4  | -1  | -1
		 *   4 | -1  |  4  |  5  | -1
		 *   5 |  6  |  7  | -1  | -1
		 *   6 | -1  |  7  | -1  | -1
		 *   7 | -1  |  7  | -1  | -1
		 */
		int[][] delta = { { 1, 2, -1, 3 }, { -1, 2, -1, 3 }, { -1, 2, 5, 4 }, { -1, 4, -1, -1 }, { -1, 4, 5, -1 },
				{ 6, 7, -1, -1 }, { -1, 7, -1, -1 }, { -1, 7, -1, -1 } };

		int state = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+' || s.charAt(i) == '-') {
				state = delta[state][0];
			} else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				state = delta[state][1];
			} else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
				state = delta[state][2];
			} else if (s.charAt(i) == '.') {
				state = delta[state][3];
			} else {
				return false;
			}

			if (state == -1)
				return false;
		}

		if (state == 2 || state == 4 || state == 7)
			return true;

		return false;
	}
}
