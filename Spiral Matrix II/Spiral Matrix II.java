public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int start = 0, end = n - 1, num = 1; start <= end; start++, end--) {
            for (int i = start; i <= end; i++)
                matrix[start][i] = num++;
            for (int i = start + 1; i <= end; i++)
                matrix[i][end] = num++;
            for (int i = end - 1; i >= start; i--)
                matrix[end][i] = num++;
            for (int i = end - 1; i >= start + 1; i--)
                matrix[i][start] = num++;
        }
        return matrix;
    }
}
