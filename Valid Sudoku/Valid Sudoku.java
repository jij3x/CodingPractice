public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int y = 0; y < 9; y++) {
            int[] rowCounter = new int[9];
            for (int x = 0; x < 9; x++) {
                if (board[y][x] != '.') {
                    int num = (int) board[y][x] - (int) '0';
                    if (rowCounter[num - 1] == 1)
                        return false;
                    rowCounter[num - 1] = 1;
                }
            }
        }

        for (int x = 0; x < 9; x++) {
            int[] colCounter = new int[9];
            for (int y = 0; y < 9; y++) {
                if (board[y][x] != '.') {
                    int num = (int) board[y][x] - (int) '0';
                    if (colCounter[num - 1] == 1)
                        return false;
                    colCounter[num - 1] = 1;
                }
            }
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                int[] gridCounter = new int[9];
                for (int yy = y * 3; yy < y * 3 + 3; yy++) {
                    for (int xx = x * 3; xx < x * 3 + 3; xx++) {
                        if (board[yy][xx] != '.') {
                            int num = (int) board[yy][xx] - (int) '0';
                            if (gridCounter[num - 1] == 1)
                                return false;
                            gridCounter[num - 1] = 1;
                        }
                    }
                }
            }
        }

        return true;
    }
}

class Solution2 {
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
