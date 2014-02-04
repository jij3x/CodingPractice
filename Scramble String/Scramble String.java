public class Solution {
	public boolean isScramble(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;

		boolean[][][] scramble = new boolean[s1.length()][s2.length()][s1.length() + 1];
		for (int i = 0; i < s1.length(); i++)
			for (int j = 0; j < s2.length(); j++)
				scramble[i][j][1] = (s1.charAt(i) == s2.charAt(j));

		for (int i = s1.length() - 1; i >= 0; i--)
			for (int j = s2.length() - 1; j >= 0; j--)
				for (int l = 2; l <= Math.min(s1.length() - i, s2.length() - j); l++)
					for (int k = 1; k < l; k++)
						if ((scramble[i][j][k] && scramble[i + k][j + k][l - k])
								|| (scramble[i][j + l - k][k] && scramble[i + k][j][l - k])) {
							scramble[i][j][l] = true;
							break;
						}

		return scramble[0][0][s1.length()];
	}
}

/*
 * backtracking
 */
class Solution2 {
	public boolean isScramble(String s1, String s2) {
		if (s1.equals(s2))
			return true;
		if (!containsSameChars(s1, s2))
			return false;

		for (int i = 1; i < s1.length(); i++) {
			String s1p1 = s1.substring(0, i), s1p2 = s1.substring(i);
			String s2p1 = s2.substring(0, i), s2p2 = s2.substring(i);
			String s2rp1 = s2.substring(0, s2.length() - i), s2rp2 = s2.substring(s2.length() - i);

			if ((isScramble(s1p1, s2p1) && isScramble(s1p2, s2p2))
					|| (isScramble(s1p1, s2rp2) && isScramble(s1p2, s2rp1)))
				return true;
		}
		return false;
	}

	private boolean containsSameChars(String s1, String s2) {
		char[] chars1 = s1.toCharArray(), chars2 = s2.toCharArray();
		Arrays.sort(chars1);
		Arrays.sort(chars2);
		s1 = new String(chars1);
		s2 = new String(chars2);

		return s1.equals(s2);
	}
}