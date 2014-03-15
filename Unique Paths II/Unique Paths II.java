public class Solution {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int[][] memo = new int[m + 1][n + 1];
		memo[m][n - 1] = 1;
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				memo[i][j] = obstacleGrid[i][j] == 1 ? 0 : memo[i + 1][j] + memo[i][j + 1];
			}
		}
		return memo[0][0];
	}
}