public class Solution {
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (matrix.length == 0 || matrix[0].length == 0)
			return result;

		int left = 0, top = 0, bottom = matrix.length - 1, right = matrix[0].length - 1;
		while (true) {
			for (int i = left; i <= right; i++)
				result.add(matrix[top][i]);
			if (++top > bottom)
				break;

			for (int i = top; i <= bottom; i++)
				result.add(matrix[i][right]);
			if (--right < left)
				break;

			for (int i = right; i >= left; i--)
				result.add(matrix[bottom][i]);
			if (--bottom < top)
				break;

			for (int i = bottom; i >= top; i--)
				result.add(matrix[i][left]);
			if (++left > right)
				break;
		}
		return result;
	}
}