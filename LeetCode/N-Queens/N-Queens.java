public class Solution {
    public ArrayList<String[]> solveNQueens(int n) {
        ArrayList<String[]> result = new ArrayList<String[]>();
        int[] board = new int[n];
        solve(board, 0, result);
        return result;
    }

    private void solve(int[] board, int row, ArrayList<String[]> result) {
        if (row == board.length) {
            String[] subResult = new String[board.length];
            char[] template = new char[board.length];
            Arrays.fill(template, '.');
            for (int i = 0; i < board.length; i++) {
                template[board[i]] = 'Q';
                subResult[i] = new String(template);
                template[board[i]] = '.';
            }
            result.add(subResult);
        }

        for (int i = 0; i < board.length; i++) {
            boolean valid = true;
            for (int j = row - 1; j >= 0; j--) {
                if (board[j] == i || board[j] == i - (row - j) || board[j] == i + (row - j)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                board[row] = i;
                solve(board, row + 1, result);
            }
        }
    }
}
