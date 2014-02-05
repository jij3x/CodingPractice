public class Solution {
	public boolean isInterleave(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length())
			return false;

		boolean[][] memo = new boolean[s1.length() + 1][s2.length() + 1];
		memo[s1.length()][s2.length()] = true;

		for (int i = s1.length() - 1; i >= 0; i--)
			if (s1.charAt(i) == s3.charAt(s2.length() + i))
				memo[i][s2.length()] = true;
			else
				break;
		for (int j = s2.length() - 1; j >= 0; j--)
			if (s2.charAt(j) == s3.charAt(s1.length() + j))
				memo[s1.length()][j] = true;
			else
				break;

		for (int i = s1.length() - 1; i >= 0; i--) {
			for (int j = s2.length() - 1; j >= 0; j--) {
				if ((s1.charAt(i) == s3.charAt(i + j) && memo[i + 1][j])
						|| (s2.charAt(j) == s3.charAt(i + j) && memo[i][j + 1]))
					memo[i][j] = true;
			}
		}

		return memo[0][0];
	}
}

class Solution2 {
	public boolean isInterleave(String s1, String s2, String s3) {
		byte[][] memo = new byte[s1.length()][s2.length()];
		return dpIsInterleave(s1, 0, s2, 0, s3, memo);
	}

	private boolean dpIsInterleave(String s1, int ss1, String s2, int ss2, String s3, byte[][] memo) {
		int ss3 = ss1 + ss2;
		if (s1.substring(ss1).isEmpty() || s2.substring(ss2).isEmpty())
			return s3.substring(ss3).equals(s1.substring(ss1).isEmpty() ? s2.substring(ss2) : s1.substring(ss1));

		if (memo[ss1][ss2] != 0)
			return memo[ss1][ss2] == 1 ? true : false;

		if ((s1.charAt(ss1) == s3.charAt(ss3) && dpIsInterleave(s1, ss1 + 1, s2, ss2, s3, memo))
				|| (s2.charAt(ss2) == s3.charAt(ss3) && dpIsInterleave(s1, ss1, s2, ss2 + 1, s3, memo))) {
			memo[ss1][ss2] = 1;
			return true;
		}

		memo[ss1][ss2] = -1;
		return false;
	}
}