public class Solution {
    public List<String[]> solveNQueens(int n) {
        ArrayList<String[]> result = new ArrayList<String[]>();
        doSolveQueens(0, new int[n], result);
        return result;
    }

    private void doSolveQueens(int level, int[] board, ArrayList<String[]> result) {
        if (level == board.length) {
            String[] subResult = new String[board.length];
            for (int i = 0; i < board.length; i++) {
                char[] row = new char[board.length];
                Arrays.fill(row, '.');
                row[board[i]] = 'Q';
                subResult[i] = new String(row);
            }
            result.add(subResult);
            return;
        }

        for (int i = 0; i < board.length; i++) {
            boolean valid = true;
            for (int j = level - 1; j >= 0; j--) {
                if (board[j] == i || board[j] == i - (level - j) || board[j] == i + (level - j))
                    valid = false;
            }

            if (valid) {
                board[level] = i;
                doSolveQueens(level + 1, board, result);
            }
        }
    }
}
