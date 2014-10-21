public class Solution {
    public void solveSudoku(char[][] board) {
        doSolve(board, 0);
    }

    private boolean doSolve(char[][] board, int idx) {
        if (idx == 9 * 9)
            return true;
        if (board[idx / 9][idx % 9] != '.')
            return doSolve(board, idx + 1);

        for (char c : getValidNumbers(board, idx)) {
            board[idx / 9][idx % 9] = c;
            if (doSolve(board, idx + 1))
                return true;
        }
        board[idx / 9][idx % 9] = '.';
        return false;
    }

    private ArrayList<Character> getValidNumbers(char[][] board, int idx) {
        boolean[] memo = new boolean[256];
        for (int i = 0; i < 9; i++) {
            memo[board[idx / 9][i]] = true;
            memo[board[i][idx % 9]] = true;
            memo[board[idx / 9 / 3 * 3 + i / 3][idx % 9 / 3 * 3 + i % 3]] = true;
        }

        ArrayList<Character> result = new ArrayList<Character>();
        for (int i = '1'; i <= '9'; i++) {
            if (!memo[i])
                result.add((char) i);
        }
        return result;
    }
}

class Solution2 {
    public void solveSudoku(char[][] board) {
        doSolveSudoku(board);
    }

    private boolean doSolveSudoku(char[][] board) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (board[y][x] != '.')
                    continue;

                ArrayList<Character> validNumbers = getValidNumbers(board, x, y);
                for (int i = 0; i < validNumbers.size(); i++) {
                    board[y][x] = validNumbers.get(i);
                    if (doSolveSudoku(board))
                        return true;
                    board[y][x] = '.';
                }
                return false;
            }
        }
        return true;
    }

    private ArrayList<Character> getValidNumbers(char[][] board, int x, int y) {
        ArrayList<Character> result = new ArrayList<Character>();

        boolean[] map = new boolean[256];
        for (int i = 0; i < 9; i++) {
            map[board[i][x]] = true;
            map[board[y][i]] = true;
            map[board[y / 3 * 3 + i / 3][x / 3 * 3 + i % 3]] = true;
        }

        for (int i = (int) '1'; i <= (int) '9'; i++)
            if (map[i] == false)
                result.add((char) i);

        return result;
    }
}
