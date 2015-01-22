public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int m = matrix.length, n = matrix[0].length, max = 0;
        int[] heightMemo = new int[n], leftMemo = new int[n], rightMemo = new int[n];
        for (int y = 0; y < m; y++) {
            for (int x = 0, onesOnLeft = 0; x < n; x++) {
                heightMemo[x] = matrix[y][x] == '0' ? 0 : heightMemo[x] + 1;
                leftMemo[x] = heightMemo[x] == 1 ? onesOnLeft : Math.min(leftMemo[x], onesOnLeft);
                onesOnLeft = heightMemo[x] == 0 ? 0 : onesOnLeft + 1;
            }

            for (int x = n - 1, onesOnRight = 0; x >= 0; x--) {
                rightMemo[x] = heightMemo[x] == 1 ? onesOnRight : Math.min(rightMemo[x], onesOnRight);
                onesOnRight = heightMemo[x] == 0 ? 0 : onesOnRight + 1;
                max = Math.max(max, heightMemo[x] * (rightMemo[x] + leftMemo[x] + 1));
            }
        }
        return max;
    }
}
