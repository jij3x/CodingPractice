public class Solution {
    public int minPathSum(int[][] grid) {
        int[][] memo = new int[grid.length + 1][grid[0].length + 1];
        for (int y = 0; y < grid.length - 1; y++)
            memo[y][memo[0].length - 1] = Integer.MAX_VALUE;
        Arrays.fill(memo[memo.length - 1], Integer.MAX_VALUE);

        for (int y = grid.length - 1; y >= 0; y--) {
            for (int x = grid[0].length - 1; x >= 0; x--)
                memo[y][x] = grid[y][x] + Math.min(memo[y][x + 1], memo[y + 1][x]);
        }
        return memo[0][0];
    }
}


class Solution2 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int[] row : memo)
            Arrays.fill(row, -1);
        memo[m - 1][n - 1] = grid[m - 1][n - 1];

        return dpMinPathSum(grid, 0, 0, memo);
    }

    private int dpMinPathSum(int[][] grid, int y, int x, int[][] memo) {
        if (memo[y][x] != -1)
            return memo[y][x];

        int m = grid.length;
        int n = grid[0].length;
        if (y == m - 1)
            memo[y][x] = grid[y][x] + dpMinPathSum(grid, y, x + 1, memo);
        else if (x == n - 1)
            memo[y][x] = grid[y][x] + dpMinPathSum(grid, y + 1, x, memo);
        else
            memo[y][x] = grid[y][x] + Math.min(dpMinPathSum(grid, y, x + 1, memo), dpMinPathSum(grid, y + 1, x, memo));

        return memo[y][x];
    }
}
