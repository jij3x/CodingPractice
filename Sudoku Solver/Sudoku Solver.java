public class Solution {
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

class Solution2 {
	public void solveSudoku(char[][] board) {
		doSolveSudoku(board, 0);
	}

	private boolean doSolveSudoku(char[][] board, int n) {
		if (n == 9 * 9)
			return true;

		int y = n / 9, x = n % 9;
		if (board[y][x] != '.')
			return doSolveSudoku(board, n + 1);

		ArrayList<Character> validNumbers = getValidNumbers(board, x, y);
		for (int i = 0; i < validNumbers.size(); i++) {
			board[y][x] = validNumbers.get(i);
			if (doSolveSudoku(board, n + 1))
				return true;
			board[y][x] = '.';
		}
		return false;
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