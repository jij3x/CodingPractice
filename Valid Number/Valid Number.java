public class Solution {
	public boolean isNumber(String s) {
		s = s.trim();
		if (isInteger(s))
			return true;
		else if (isFloat(s))
			return true;
		else if (isENotion(s))
			return true;

		return false;
	}

	private boolean isInteger(String s) {
		if (!s.isEmpty() && (s.charAt(0) == '+' || s.charAt(0) == '-'))
			s = s.substring(1);

		if (s.isEmpty() || !allDigits(s))
			return false;

		return true;
	}

	private boolean isFloat(String s) {
		if (!s.isEmpty() && (s.charAt(0) == '+' || s.charAt(0) == '-'))
			s = s.substring(1);

		int dotPos = findFirstDot(s);
		if (dotPos == -1)
			return false;

		return s.length() > 1 && allDigits(s.substring(0, dotPos)) && allDigits(s.substring(dotPos + 1));
	}

	private boolean isENotion(String s) {
		int ePos = findFirstE(s);
		if (ePos == -1)
			return false;

		return (isInteger(s.substring(0, ePos)) || isFloat(s.substring(0, ePos))) && isInteger(s.substring(ePos + 1));
	}

	private boolean allDigits(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) < '0' || s.charAt(i) > '9')
				return false;
		}
		return true;
	}

	private int findFirstDot(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '.')
				return i;
		}
		return -1;
	}

	private int findFirstE(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'e' || s.charAt(i) == 'E')
				return i;
		}
		return -1;
	}
}

/*
 * DFA
 */
class Solution2 {
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
