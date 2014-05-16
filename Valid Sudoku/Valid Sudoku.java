public class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowMark = new boolean[9][9];
        boolean[][] colMark = new boolean[9][9];
        boolean[][] blkMark = new boolean[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (board[y][x] == '.')
                    continue;

                int num = board[y][x] - '1';
                if (rowMark[y][num] || colMark[x][num] || blkMark[y / 3 * 3 + x / 3][num])
                    return false;

                rowMark[y][num] = colMark[x][num] = blkMark[y / 3 * 3 + x / 3][num] = true;
            }
        }
        return true;
    }
}

class Solution2 {
    public boolean isValidSudoku(char[][] board) {
        for (int y = 0; y < 9; y++) {
            boolean[] rowMark = new boolean[9];
            for (int x = 0; x < 9; x++) {
                if (board[y][x] != '.') {
                    int num = board[y][x] - '1';
                    if (rowMark[num])
                        return false;
                    rowMark[num] = true;
                }
            }
        }

        for (int x = 0; x < 9; x++) {
            boolean[] colMark = new boolean[9];
            for (int y = 0; y < 9; y++) {
                if (board[y][x] != '.') {
                    int num = board[y][x] - '1';
                    if (colMark[num])
                        return false;
                    colMark[num] = true;
                }
            }
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                boolean[] blkMark = new boolean[9];
                for (int yy = y * 3; yy < y * 3 + 3; yy++) {
                    for (int xx = x * 3; xx < x * 3 + 3; xx++) {
                        if (board[yy][xx] != '.') {
                            int num = board[yy][xx] - '1';
                            if (blkMark[num])
                                return false;
                            blkMark[num] = true;
                        }
                    }
                }
            }
        }

        return true;
    }
}
