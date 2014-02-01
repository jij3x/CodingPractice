public class Solution {
	public ArrayList<String[]> solveNQueens(int n) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		int[] board = new int[n];
		doSolveNQueens(n, 0, board, result);
		return result;
	}

	private void doSolveNQueens(int n, int level, int[] board, ArrayList<String[]> result) {
		if (level == n) {
			String[] subResult = new String[n];
			for (int i = 0; i < n; i++) {
				String line = "";
				for (int j = 0; j < n; j++)
					line += j == board[i] ? 'Q' : '.';
				subResult[i] = line;
			}
			result.add(subResult);
			return;
		}

		for (int i = 0; i < n; i++) {
			boolean validPosition = true;
			for (int j = level - 1; j >= 0; j--) {
				if (board[j] == i || board[j] == i - (level - j) || board[j] == i + (level - j)) {
					validPosition = false;
					break;
				}
			}

			if (validPosition) {
				board[level] = i;
				doSolveNQueens(n, level + 1, board, result);
			}
		}
	}
}
