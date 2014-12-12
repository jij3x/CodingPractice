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
        int[][] memo = new int[m][n];
        return dp(0, 0, memo);
    }

    private int dp(int y, int x, int[][] memo) {
        if (y == memo.length - 1 && x == memo[0].length - 1)
            return 1;
        if (y == memo.length || x == memo[0].length)
            return 0;

        if (memo[y][x] == 0)
            memo[y][x] = dp(y + 1, x, memo) + dp(y, x + 1, memo);
        return memo[y][x];
    }
}
