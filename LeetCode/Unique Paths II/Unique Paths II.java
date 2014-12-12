public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] memo = new int[obstacleGrid.length + 1][obstacleGrid[0].length + 1];
        memo[memo.length - 1][memo[0].length - 2] = 1;
        for (int y = memo.length - 2; y >= 0; y--) {
            for (int x = memo[0].length - 2; x >= 0; x--)
                memo[y][x] = obstacleGrid[y][x] == 1 ? 0 : memo[y + 1][x] + memo[y][x + 1];
        }
        return memo[0][0];
    }
}
