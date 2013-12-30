public class Solution {
	public int minDistance(String word1, String word2) {
		int[][] memo = new int[word1.length()][word2.length()];
		for (int[] row : memo)
			Arrays.fill(row, -1);

		return minDistanceDP(word1, 0, word2, 0, memo);
	}

	private int minDistanceDP(String word1, int i, String word2, int j, int[][] memo) {
		if (i == word1.length() && j == word2.length())
			return 0;
		else if (i == word1.length())
			return word2.length() - j;
		else if (j == word2.length())
			return word1.length() - i;

		if (memo[i][j] != -1)
			return memo[i][j];

		if (word1.charAt(i) == word2.charAt(j)) {
			memo[i][j] = minDistanceDP(word1, i + 1, word2, j + 1, memo);
		} else {
			int replaceDist = minDistanceDP(word1, i + 1, word2, j + 1, memo);
			int deleteDist = minDistanceDP(word1, i + 1, word2, j, memo);
			int insertDist = minDistanceDP(word1, i, word2, j + 1, memo);
			memo[i][j] = 1 + Math.min(replaceDist, Math.min(deleteDist, insertDist));
		}
		return memo[i][j];
	}
}