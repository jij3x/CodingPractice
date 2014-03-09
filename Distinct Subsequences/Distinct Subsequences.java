public class Solution {
	public int numDistinct(String S, String T) {
		int[][] memo = new int[2][T.length() + 1];
		int[] prev = memo[0];
		int[] curr = memo[1];
		Arrays.fill(prev, 0);
		prev[prev.length - 1] = 1;
		curr[curr.length - 1] = 1;

		for (int i = S.length() - 1; i >= 0; i--) {
			for (int j = T.length() - 1; j >= 0; j--) {
				curr[j] = prev[j] + (S.charAt(i) == T.charAt(j) ? prev[j + 1] : 0);
			}
			prev = prev == memo[0] ? memo[1] : memo[0];
			curr = curr == memo[0] ? memo[1] : memo[0];
		}
		return prev[0];
	}
}

class Solution2 {
	public int numDistinct(String S, String T) {
		int[][] memo = new int[S.length() + 1][T.length() + 1];
		Arrays.fill(memo[S.length()], 0);
		for (int i = 0; i < memo.length; i++)
			memo[i][T.length()] = 1;

		for (int i = S.length() - 1; i >= 0; i--) {
			for (int j = T.length() - 1; j >= 0; j--) {
				memo[i][j] = memo[i + 1][j] + (S.charAt(i) == T.charAt(j) ? memo[i + 1][j + 1] : 0);
			}
		}
		return memo[0][0];
	}
}

class Solution3 {
	public int numDistinct(String S, String T) {
		int[][] memo = new int[S.length()][T.length()];
		for (int i = 0; i < memo.length; i++)
			Arrays.fill(memo[i], -1);

		return doNumDistinct(S, 0, T, 0, memo);
	}

	private int doNumDistinct(String S, int i, String T, int j, int[][] memo) {
		if (j == T.length())
			return 1;
		if (i == S.length())
			return 0;
		if (memo[i][j] != -1)
			return memo[i][j];

		memo[i][j] = doNumDistinct(S, i + 1, T, j, memo)
				+ (S.charAt(i) == T.charAt(j) ? doNumDistinct(S, i + 1, T, j + 1, memo) : 0);

		return memo[i][j];
	}
}