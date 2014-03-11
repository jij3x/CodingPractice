public class Solution {
	public int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];
		if (n == 0)
			return matrix;

		int start = 0, end = n - 1, x = 1;
		while (start < end) {
			for (int i = start; i <= end; i++)
				matrix[start][i] = x++;
			for (int i = start + 1; i <= end; i++)
				matrix[i][end] = x++;
			for (int i = end - 1; i >= start; i--)
				matrix[end][i] = x++;
			for (int i = end - 1; i >= start + 1; i--)
				matrix[i][start] = x++;
			start++;
			end--;
		}
		matrix[start][start] = start == end ? x : matrix[start][start];
		return matrix;
	}
}