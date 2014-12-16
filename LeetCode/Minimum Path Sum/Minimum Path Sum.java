public class Solution {
    public int minPathSum(int[][] grid) {
        int[][] memo = new int[grid.length + 1][grid[0].length + 1];
        for (int i = 0; i < grid.length - 1; i++)
            memo[i][grid[0].length] = Integer.MAX_VALUE;
        Arrays.fill(memo[grid.length], Integer.MAX_VALUE);

        for (int y = grid.length - 1; y >= 0; y--) {
            for (int x = grid[0].length - 1; x >= 0; x--)
                memo[y][x] = Math.min(memo[y + 1][x], memo[y][x + 1]) + grid[y][x];
        }
        return memo[0][0];
    }
}

class Solution2 {
    public int minPathSum(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        for (int[] row : memo)
            Arrays.fill(row, -1);
        return dp(0, 0, grid, memo);
    }

    private int dp(int y, int x, int[][] grid, int[][] memo) {
        if (y == grid.length - 1 && x == grid[0].length - 1)
            return grid[grid.length - 1][grid[0].length - 1];
        else if (y == grid.length || x == grid[0].length)
            return Integer.MAX_VALUE;

        if (memo[y][x] == -1)
            memo[y][x] = Math.min(dp(y, x + 1, grid, memo), dp(y + 1, x, grid, memo)) + grid[y][x];
        return memo[y][x];
    }
}
