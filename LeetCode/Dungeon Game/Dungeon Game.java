public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] memo = new int[m][n];
        memo[m - 1][n - 1] = dungeon[m - 1][n - 1] >= 0 ? 0 : -dungeon[m - 1][n - 1];

        for (int i = n - 2; i >= 0; i--)
            memo[m - 1][i] = Math.max(0, memo[m - 1][i + 1] - dungeon[m - 1][i]);
        for (int i = m - 2; i >= 0; i--)
            memo[i][n - 1] = Math.max(0, memo[i + 1][n - 1] - dungeon[i][n - 1]);

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--)
                memo[i][j] = Math.max(0, Math.min(memo[i + 1][j], memo[i][j + 1]) - dungeon[i][j]);
        }
        return memo[0][0] + 1;
    }
}