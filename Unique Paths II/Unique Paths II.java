public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] memo = new int[m + 1][n + 1];
        memo[m][n - 1] = 1;
        for (int y = m - 1; y >= 0; y--) {
            for (int x = n - 1; x >= 0; x--)
                memo[y][x] = obstacleGrid[y][x] == 1 ? 0 : memo[y][x + 1] + memo[y + 1][x];
        }
        return memo[0][0];
    }
}
