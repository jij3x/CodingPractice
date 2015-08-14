public class Solution {
    public int numIslands(char[][] grid) {
        int total = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] != '0') {
                    wipeIsland(grid, y, x);
                    total++;
                }
            }
        }
        return total;
    }

    private void wipeIsland(char[][] grid, int y, int x) {
        if (y < 0 || y == grid.length || x < 0 || x == grid[0].length || grid[y][x] == '0')
            return;
        grid[y][x] = '0';
        wipeIsland(grid, y - 1, x);
        wipeIsland(grid, y + 1, x);
        wipeIsland(grid, y, x - 1);
        wipeIsland(grid, y, x + 1);
    }
}
