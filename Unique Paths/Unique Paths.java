public class Solution {
	public int uniquePaths(int m, int n) {
		int[][] memo = new int[m + 1][n + 1];
		memo[m][n - 1] = 1;
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				memo[i][j] = memo[i + 1][j] + memo[i][j + 1];
			}
		}
		return memo[0][0];
	}
}