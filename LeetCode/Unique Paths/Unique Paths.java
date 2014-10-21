public class Solution {
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m + 1][n + 1];
        memo[m][n - 1] = 1;

        for (int y = m - 1; y >= 0; y--) {
            for (int x = n - 1; x >= 0; x--)
                memo[y][x] = memo[y][x + 1] + memo[y + 1][x];
        }
        return memo[0][0];
    }
}

class Solution2 {
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m + 1][n + 1];
        return dpUniquePaths(m, n, 1, 1, memo);
    }

    private int dpUniquePaths(int m, int n, int y, int x, int[][] memo) {
        if (y == m && x == n)
            return 1;
        if (y > m || x > n)
            return 0;
        if (memo[y][x] != 0)
            return memo[y][x];

        memo[y][x] = dpUniquePaths(m, n, y, x + 1, memo) + dpUniquePaths(m, n, y + 1, x, memo);
        return memo[y][x];
    }
}
