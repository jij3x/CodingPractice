public class Solution {
	public int numDecodings(String s) {
		if (s.isEmpty())
			return 0;

		int[] dp = new int[s.length() + 1];
		dp[s.length()] = 1;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '0')
				dp[i] = 0;
			else
				dp[i] = i < s.length() - 1 && Integer.parseInt(s.substring(i, i + 2)) <= 26 ? dp[i + 1] + dp[i + 2]
						: dp[i + 1];
		}

		return dp[0];
	}
}

class Solution2 {
	public int numDecodings(String s) {
		if (s.isEmpty())
			return 0;

		int[] memo = new int[s.length()];
		Arrays.fill(memo, -1);
		return dpNumDecodings(s, 0, memo);

	}

	private int dpNumDecodings(String s, int si, int[] memo) {
		if (si == s.length())
			return 1;
		else if (s.charAt(si) == '0')
			return 0;

		if (memo[si] != -1)
			return memo[si];

		int result = dpNumDecodings(s, si + 1, memo);
		if (si < s.length() - 1 && Integer.parseInt(s.substring(si, si + 2)) <= 26)
			result += dpNumDecodings(s, si + 2, memo);

		memo[si] = result;
		return result;
	}
}