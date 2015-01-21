public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int m = matrix.length, n = matrix[0].length, max = 0;
        int[] heightMemo = new int[n], leftMemo = new int[n], rightMemo = new int[n];
        for (int y = 0; y < m; y++) {
            int[] left = new int[n], right = new int[n];
            for (int x = 0, rx = n - 1; x < n; x++, rx--) {
                left[x] = matrix[y][x] == '0' ? 0 : (x == 0 ? 1 : left[x - 1] + 1);
                right[rx] = matrix[y][rx] == '0' ? 0 : (rx == n - 1 ? 1 : right[rx + 1] + 1);
            }

            for (int x = 0; x < n; x++) {
                leftMemo[x] = leftMemo[x] == 0 ? left[x] : Math.min(leftMemo[x], left[x]);
                rightMemo[x] = rightMemo[x] == 0 ? right[x] : Math.min(rightMemo[x], right[x]);
                heightMemo[x] = matrix[y][x] == '0' ? 0 : heightMemo[x] + 1;
                max = Math.max(max, heightMemo[x] * (leftMemo[x] + rightMemo[x] - 1));
            }
        }
        return max;
    }
}
