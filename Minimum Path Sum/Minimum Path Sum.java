public class Solution {
	public int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] memo = new int[m][n];
		memo[m - 1][n - 1] = grid[m - 1][n - 1];
		for (int i = n - 2; i >= 0; i--)
			memo[m - 1][i] = memo[m - 1][i + 1] + grid[m - 1][i];
		for (int i = m - 2; i >= 0; i--)
			memo[i][n - 1] = memo[i + 1][n - 1] + grid[i][n - 1];

		for (int i = m - 2; i >= 0; i--) {
			for (int j = n - 2; j >= 0; j--) {
				memo[i][j] = grid[i][j] + Math.min(memo[i][j + 1], memo[i + 1][j]);
			}
		}
		return memo[0][0];
	}
}

class Solution2 {
	public int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] memo = new int[m][n];
		for (int[] row : memo)
			Arrays.fill(row, -1);
		memo[m - 1][n - 1] = grid[m - 1][n - 1];

		return dpMinPathSum(grid, 0, 0, memo);
	}

	private int dpMinPathSum(int[][] grid, int y, int x, int[][] memo) {
		if (memo[y][x] != -1)
			return memo[y][x];

		int m = grid.length;
		int n = grid[0].length;
		if (y == m - 1)
			memo[y][x] = grid[y][x] + dpMinPathSum(grid, y, x + 1, memo);
		else if (x == n - 1)
			memo[y][x] = grid[y][x] + dpMinPathSum(grid, y + 1, x, memo);
		else
			memo[y][x] = grid[y][x] + Math.min(dpMinPathSum(grid, y, x + 1, memo), dpMinPathSum(grid, y + 1, x, memo));

		return memo[y][x];
	}
}
