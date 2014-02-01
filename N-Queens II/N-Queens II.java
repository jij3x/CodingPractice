public class Solution {
	int count, all;

	public int totalNQueens(int n) {
		count = 0;
		all = (1 << n) - 1;
		doTotalNQueens(0, 0, 0);
		return count;
	}

	private void doTotalNQueens(int ld, int row, int rd) {
		int poss, p;
		if (row == all) {
			count++;
		} else {
			poss = all & ~(ld | row | rd);
			while (poss != 0) {
				p = poss & (-poss); // get poss' right most '1'
				poss = poss - p;
				doTotalNQueens((ld | p) << 1, row | p, (rd | p) >> 1);
			}
		}
	}
}

class Solution2 {
	public int totalNQueens(int n) {
		int[] board = new int[n];
		int[] result = new int[1];
		doTotalNQueens(n, 0, board, result);
		return result[0];
	}

	private void doTotalNQueens(int n, int level, int[] board, int[] result) {
		if (level == n) {
			result[0]++;
			return;
		}

		for (int i = 0; i < n; i++) {
			boolean validPosition = true;
			for (int j = level - 1; j >= 0; j--) {
				if (board[j] == i || board[j] == i + (level - j) || board[j] == i - (level - j)) {
					validPosition = false;
					break;
				}
			}

			if (validPosition) {
				board[level] = i;
				doTotalNQueens(n, level + 1, board, result);
			}
		}
	}
}