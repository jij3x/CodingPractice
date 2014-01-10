public class Solution {
	public boolean isMatch(String s, String p) {
		return doIsMatch(s, 0, p, 0);
	}

	private boolean doIsMatch(String s, int si, String p, int pi) {
		if (si == s.length() && pi == p.length())
			return true;
		else if (pi == p.length())
			return false;

		if (pi == p.length() - 1 || p.charAt(pi + 1) != '*') {
			if (si >= s.length())
				return false;
			return (p.charAt(pi) == '.' || s.charAt(si) == p.charAt(pi)) && doIsMatch(s, si + 1, p, pi + 1);
		} else {
			if (doIsMatch(s, si, p, pi + 2))
				return true;
			while (si < s.length()) {
				if (p.charAt(pi) == '.' || s.charAt(si) == p.charAt(pi)) {
					if (doIsMatch(s, si + 1, p, pi + 2))
						return true;
					si++;
				} else {
					break;
				}
			}
		}

		return false;
	}
}