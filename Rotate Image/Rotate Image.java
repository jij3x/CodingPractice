public class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0, ci = n - 1 - i; i < n / 2; i++, ci--) {
            for (int j = i, cj = n - 1 - i; j < n - 1 - i; j++, cj--) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[cj][i];
                matrix[cj][i] = matrix[ci][cj];
                matrix[ci][cj] = matrix[j][ci];
                matrix[j][ci] = temp;
            }
        }
    }
}

class Solution2 {
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
