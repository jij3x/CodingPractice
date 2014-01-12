public class Solution {
	public void setZeroes(int[][] matrix) {
		int xLength = matrix[0].length;
		int yLength = matrix.length;

		boolean clearX = false;
		boolean clearY = false;

		for (int i = 0; i < xLength; i++) {
			if (matrix[0][i] == 0)
				clearX = true;
		}

		for (int i = 0; i < yLength; i++) {
			if (matrix[i][0] == 0)
				clearY = true;
		}

		for (int i = 1; i < yLength; i++) {
			for (int j = 1; j < xLength; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		for (int i = 1; i < xLength; i++) {
			if (matrix[0][i] == 0) {
				for (int j = 1; j < yLength; j++) {
					matrix[j][i] = 0;
				}
			}
		}

		for (int i = 1; i < yLength; i++) {
			if (matrix[i][0] == 0) {
				for (int j = 1; j < xLength; j++) {
					matrix[i][j] = 0;
				}
			}
		}

		if (clearX) {
			for (int i = 0; i < xLength; i++) {
				matrix[0][i] = 0;
			}
		}

		if (clearY) {
			for (int i = 0; i < yLength; i++) {
				matrix[i][0] = 0;
			}
		}
	}
}