public class Solution {
	public void rotate(int[][] matrix) {
		doRotate(matrix, 0);
	}

	private void doRotate(int[][] matrix, int level) {
		int n = matrix.length;
		if (level == n / 2)
			return;

		for (int i = level; i < n - 1 - level; i++) {
			int temp = matrix[level][i];
			matrix[level][i] = matrix[n - 1 - i][level];
			matrix[n - 1 - i][level] = matrix[n - 1 - level][n - 1 - i];
			matrix[n - 1 - level][n - 1 - i] = matrix[i][n - 1 - level];
			matrix[i][n - 1 - level] = temp;
		}

		doRotate(matrix, level + 1);
	}
}

class Solution2 {
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int level = 0; level < n / 2; level++) {
			for (int x = level, y = x, xc = n - 1 - level, yc = xc; x < n - 1 - level; x++, y++, xc--, yc--) {
				int temp = matrix[level][x];
				matrix[level][x] = matrix[yc][level];
				matrix[yc][level] = matrix[n - 1 - level][xc];
				matrix[n - 1 - level][xc] = matrix[y][n - 1 - level];
				matrix[y][n - 1 - level] = temp;
			}
		}
	}
}
