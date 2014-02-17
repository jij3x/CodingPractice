public class Solution {
	public int atoi(String str) {
		str = str.trim();
		if (str.isEmpty())
			return 0;

		long result = 0, sign = 1;
		if (str.charAt(0) == '-' || str.charAt(0) == '+') {
			sign = str.charAt(0) == '-' ? -1 : 1;
			str = str.substring(1);
		}

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9')
				break;

			result = result * 10 + (str.charAt(i) - '0');
			if (sign * result > Integer.MAX_VALUE)
				return Integer.MAX_VALUE;
			if (sign * result < Integer.MIN_VALUE)
				return Integer.MIN_VALUE;
		}

		return (int) (sign * result);
	}
}
