public class Solution {
    public void rotate(int[][] matrix) {
        for (int start = 0, end = matrix.length - 1; start < end; start++, end--) {
            for (int i = start, j = end; i < end; i++, j--) {
                int temp = matrix[start][i];
                matrix[start][i] = matrix[j][start];
                matrix[j][start] = matrix[end][j];
                matrix[end][j] = matrix[i][end];
                matrix[i][end] = temp;
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

        for (int i = level, ci = n - 1 - level, cLevel = ci; i < cLevel; i++, ci--) {
            int temp = matrix[level][i];
            matrix[level][i] = matrix[ci][level];
            matrix[ci][level] = matrix[cLevel][ci];
            matrix[cLevel][ci] = matrix[i][cLevel];
            matrix[i][cLevel] = temp;
        }

        doRotate(matrix, level + 1);
    }
}
