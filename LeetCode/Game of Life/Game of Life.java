public class Solution {
    public void gameOfLife(int[][] board) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                int[][] dt = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
                int liveNeighbors = 0;
                for (int[] pt : dt) {
                    if (pt[0] + y >= 0 && pt[0] + y < board.length && pt[1] + x >= 0 && pt[1] + x < board[0].length)
                        liveNeighbors += board[pt[0] + y][pt[1] + x] & 1;
                }
                if ((board[y][x] == 1 && liveNeighbors == 2) || liveNeighbors == 3)
                    board[y][x] |= 2;
            }
        }

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                board[y][x] >>= 1;
            }
        }
    }
}