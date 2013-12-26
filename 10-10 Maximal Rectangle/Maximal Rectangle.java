public class Solution {
	public int maximalRectangle(char[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0)
			return 0;

		int[] height = new int[matrix[0].length];
		int[] left = new int[matrix[0].length];
		int[] right = new int[matrix[0].length];
		Arrays.fill(left, left.length);
		Arrays.fill(right, right.length);
		int maxArea = 0;

		for (int y = 0; y < matrix.length; y++) {
			int[] countLeft = new int[left.length];
			countLeft[0] = 0;
			for (int x = 1; x < countLeft.length; x++) {
				if (matrix[y][x - 1] == '1')
					countLeft[x] = countLeft[x - 1] + 1;
				else
					countLeft[x] = 0;
			}

			int[] countRight = new int[right.length];
			countRight[countRight.length - 1] = 0;
			for (int x = countRight.length - 2; x >= 0; x--) {
				if (matrix[y][x + 1] == '1')
					countRight[x] = countRight[x + 1] + 1;
				else
					countRight[x] = 0;
			}

			for (int x = 0; x < matrix[0].length; x++) {
				if (matrix[y][x] == '1') {
					height[x]++;
					left[x] = Math.min(left[x], countLeft[x]);
					right[x] = Math.min(right[x], countRight[x]);
					maxArea = Math.max(maxArea, height[x] * (right[x] + left[x] + 1));
				} else {
					height[x] = 0;
					left[x] = left.length;
					right[x] = right.length;
				}
			}
		}

		return maxArea;
	}
}