public class Solution {
	public boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length, n = matrix[0].length, start = 0, end = m - 1, mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			if (matrix[mid][0] > target) {
				end = mid - 1;
			} else if (matrix[mid][n - 1] < target) {
				start = mid + 1;
			} else {
				int i = 0, j = n - 1;
				while (i <= j) {
					int k = (i + j) / 2;
					if (matrix[mid][k] > target)
						j = k - 1;
					else if (matrix[mid][k] < target)
						i = k + 1;
					else
						return true;
				}
				return false;
			}
		}
		return false;
	}
}

class Solution2 {
	public boolean searchMatrix(int[][] matrix, int target) {
		for (int rowCnt = matrix[0].length, start = 0, end = matrix.length * rowCnt - 1; start <= end;) {
			int mid = (start + end) / 2;

			if (matrix[mid / rowCnt][mid % rowCnt] > target)
				end = mid - 1;
			else if (matrix[mid / rowCnt][mid % rowCnt] < target)
				start = mid + 1;
			else
				return true;
		}
		return false;
	}
}