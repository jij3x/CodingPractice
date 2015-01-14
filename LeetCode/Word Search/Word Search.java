public class Solution {
    public boolean exist(char[][] board, String word) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (dfs(board, y, x, word, 0, new boolean[board.length][board[0].length]))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int y, int x, String word, int s, boolean[][] visited) {
        if (s == word.length())
            return true;

        if (y < 0 || y == board.length || x < 0 || x == board[0].length || visited[y][x]
                || word.charAt(s) != board[y][x])
            return false;

        visited[y][x] = true;
        if (dfs(board, y - 1, x, word, s + 1, visited) || dfs(board, y + 1, x, word, s + 1, visited)
                || dfs(board, y, x - 1, word, s + 1, visited) || dfs(board, y, x + 1, word, s + 1, visited))
            return true;

        visited[y][x] = false;
        return false;
    }
}
