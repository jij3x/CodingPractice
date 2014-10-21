public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int start = 0, end = matrix.length - 1; start <= end;) {
            int mid = (start + end) / 2, n = matrix[0].length;
            if (matrix[mid][0] > target) {
                end = mid - 1;
            } else if (matrix[mid][n - 1] < target) {
                start = mid + 1;
            } else {
                for (int l = 0, r = n - 1; l <= r;) {
                    int m = (l + r) / 2;
                    if (matrix[mid][m] > target)
                        r = m - 1;
                    else if (matrix[mid][m] < target)
                        l = m + 1;
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
